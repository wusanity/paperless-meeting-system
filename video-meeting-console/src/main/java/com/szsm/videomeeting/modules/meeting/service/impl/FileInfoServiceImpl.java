package com.szsm.videomeeting.modules.meeting.service.impl;

import com.szsm.videomeeting.base.BaseEntity;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.model.entity.FileInfo;
import com.szsm.videomeeting.modules.kk.enums.PersonErrorEnums;
import com.szsm.videomeeting.modules.meeting.constant.MeetingConstant;
import com.szsm.videomeeting.modules.meeting.enums.MeetingErrorEnums;
import com.szsm.videomeeting.modules.meeting.mapper.FileInfoMapper;
import com.szsm.videomeeting.modules.meeting.service.FileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 *文件表service实现
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
    public ApiResult upload(MultipartFile file, FileUploadDTO fileUploadDTO) throws IOException {
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
        file.transferTo(new File(filePath));
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
        apiResult.setCode(1);
        apiResult.setMessage("上传成功");
        return apiResult;
    }

    @Override
    public ApiResult delete(Long id) {
        FileInfo fileInfo = this.getById(id);

        /**
         * 先删除服务器文件，再删除数据库记录
         */
        if(fileInfo != null) {
            String filePath = fileInfo.getFilePath();
            if(StringUtils.isNotBlank(filePath)) {
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

    /**
     * 计算文件大小:将字节转换为KB或M
     * @param bytes
     * @return
     */
    private String bytesTrans(long bytes) {
        BigDecimal fileSize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);

        float returnValue = fileSize.divide(megabyte, 2, BigDecimal.ROUND_UP).floatValue();
        if (returnValue > 1){
            return (returnValue + "MB");
        }
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fileSize.divide(kilobyte, 2, BigDecimal.ROUND_UP).floatValue();
        return (returnValue + "KB");
    }
}
