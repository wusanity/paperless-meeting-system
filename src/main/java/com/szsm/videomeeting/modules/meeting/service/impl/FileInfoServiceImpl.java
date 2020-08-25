package com.szsm.videomeeting.modules.meeting.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szsm.videomeeting.base.constant.ApiConstant;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.base.util.ExcelUtil;
import com.szsm.videomeeting.modules.kk.enums.PersonErrorEnums;
import com.szsm.videomeeting.modules.meeting.constant.MeetingConstant;
import com.szsm.videomeeting.modules.meeting.mapper.FileInfoMapper;
import com.szsm.videomeeting.modules.meeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.modules.meeting.model.entity.FileInfo;
import com.szsm.videomeeting.modules.meeting.model.excel.MeetingMembersExcel;
import com.szsm.videomeeting.modules.meeting.service.FileInfoService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 文件表service实现
 *
 * @author LiuJun
 * @date 2020-08-20
 */
@Service
public class FileInfoServiceImpl extends ServiceImpl<FileInfoMapper, FileInfo> implements FileInfoService {
    /**
     * 文件表mapper
     */
    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Override
    public ApiResult upload(MultipartFile file, FileUploadDTO fileUploadDTO) throws MyException {
        if (fileUploadDTO == null || file == null) {
            return ApiResult.fail(PersonErrorEnums.PARAM_MISS);
        }
        ApiResult apiResult = new ApiResult();

        /**
         * 1、拼接文件保存路径
         * 格式：FILE_PATH_PREFIX+YYYY-MM+xxxx.xx
         */
        String fileName = file.getOriginalFilename();
        //文件大小转换
        String fileSize = bytesTrans(file.getSize());
        String filePathPrefix = MeetingConstant.FILE_PATH_PREFIX;
        String date = DateFormatUtils.format(new Date(), "yyyy-MM");
        //文件后缀
        String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        //存放至服务器的文件名，取系统时间
        String saveFileName = String.valueOf(System.currentTimeMillis());
        String filePath = filePathPrefix + date + "/";
        File tempDir = new File(filePath);
        if (!tempDir.isDirectory()) {
            // 如果目录不存在则创建
            tempDir.mkdirs();
        }
        //全路径
        filePath += "/" + saveFileName + "." + fileSuffix;

        /**
         * 2、将文件写入服务器对应目录，并将记录保存至数据库
         */
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("文件上传异常！");
        }
        FileInfo fileInfo = new FileInfo();
        fileInfo.setFileName(fileName);
        fileInfo.setFilePath(filePath);
        fileInfo.setFileSize(fileSize);
        fileInfo.setSaveFileName(saveFileName);
        fileInfo.setCreateBy(fileUploadDTO.getCreateBy());
        fileInfo.setUpdateBy(fileUploadDTO.getUpdateBy());
        save(fileInfo);

        /**
         * 3、返回文件数据给前台
         */
        fileInfo.setFilePath("");
        apiResult.setData(fileInfo);
        apiResult.setCode(ApiConstant.Code.SUCCESS_CODE);
        apiResult.setMessage("上传成功");
        return apiResult;
    }

    @Override
    public ApiResult delete(Long id) {
        FileInfo fileInfo = this.getById(id);

        /**
         * 先删除服务器文件，再删除数据库记录
         */
        if (fileInfo != null) {
            String filePath = fileInfo.getFilePath();
            if (StringUtils.isNotBlank(filePath)) {
                File file = new File(filePath);
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists()) {
                    file.delete();
                }
            }
        }
        this.removeById(id);
        return ApiResult.SUCCESS;
    }

    @Override
    public ApiResult fileDownload(String modelFileName, HttpServletResponse response, Long fileId) {
        InputStream inputStream = null;
        BufferedInputStream bufferedInputStream = null;
        String fileName = "";
        if (StringUtils.isBlank(modelFileName) && fileId == null) {
            return ApiResult.fail(PersonErrorEnums.PARAM_MISS);
        }

        try {
            /**
             * 模板文件下载
             */
            if (StringUtils.isNotBlank(modelFileName)) {
                fileName = URLDecoder.decode(modelFileName, "UTF-8");
                String path = MeetingConstant.TEMPLATE_FILE_PATH + fileName;
                inputStream = this.getClass().getResourceAsStream(path);
            } else {
                /**
                 * 普通文件下载
                 */
                FileInfo fileInfo = this.getById(fileId);
                if (fileInfo != null) {
                    String path = fileInfo.getFilePath();
                    fileName = fileInfo.getFileName();
                    if(StringUtils.isNotBlank(path)) {
                        inputStream = new FileInputStream(path);
                    }
                }
            }

            /**
             * 输出流文件
             */
            if (inputStream != null) {
                bufferedInputStream = new BufferedInputStream(inputStream);
                // 设置输出的格式
                HttpHeaders headers = new HttpHeaders();
                headers.setContentDispositionFormData("attachment", fileName);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/x-download");
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

                // 循环取出流中的数据
                byte[] b = new byte[1024];
                int len;
                while ((len = bufferedInputStream.read(b)) > 0) {
                    response.getOutputStream().write(b, 0, len);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new MyException("文件名URL编码异常！");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new MyException("文件路径下未找到相应文件！");
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("文件下载失败！");
        }finally {
            //释放资源
            if (inputStream != null && bufferedInputStream != null) {
                try {
                    inputStream.close();
                    bufferedInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ApiResult.SUCCESS;
    }

    @Override
    public ApiResult excelImport(MultipartFile file) {
        ApiResult apiResult = new ApiResult();
        if(file == null) {
            return ApiResult.fail(PersonErrorEnums.PARAM_MISS);
        }

        try {
            InputStream inputStream = new BufferedInputStream(file.getInputStream());
            if (inputStream != null) {
                //调用excel解析工具类，对文件进行解析并返回list
                List<Object> excelList = ExcelUtil.readLessThan1000Row("", inputStream);
                List<MeetingMembersExcel> resultList = new ArrayList<>();
                //去除表头
                excelList.remove(0);
                //将解析返回的数据转成实体bean
                if(CollectionUtils.isNotEmpty(excelList)) {
                    for(Object obj:excelList) {
                        MeetingMembersExcel meetingMembersExcel = new MeetingMembersExcel();
                        List<Object> oneRow = (List<Object>) obj;
                        for(int i = 0;i<oneRow.size();i++) {
                            String field = (String) oneRow.get(i);
                            switch (i) {
                                case 0:
                                    meetingMembersExcel.setIndex(Integer.parseInt(field));
                                    break;
                                case 1:
                                    meetingMembersExcel.setName(field);
                                    break;
                                case 2:
                                    meetingMembersExcel.setTittle(field);
                                    break;
                                case 3:
                                    meetingMembersExcel.setEquipment(field);
                                    break;
                                case 4:
                                    meetingMembersExcel.setEquipmentStatus(field);
                                    break;
                                case 5:
                                    meetingMembersExcel.setAttachedEquipment(field);
                                    break;
                                    default :
                            }
                        }
                        resultList.add(meetingMembersExcel);
                    }
                    apiResult.setData(resultList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new MyException("获取excel流文件IO异常,文件名：" + file.getName());
        }
        apiResult.setCode(ApiConstant.Code.SUCCESS_CODE);
        return apiResult;
    }

    /**
     * 计算文件大小:将字节转换为KB或M
     *
     * @param bytes 字节
     * @return
     */
    private String bytesTrans(long bytes) {
        BigDecimal fileSize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);

        float returnValue = fileSize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1) {
            return (returnValue + "MB");
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fileSize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }
}
