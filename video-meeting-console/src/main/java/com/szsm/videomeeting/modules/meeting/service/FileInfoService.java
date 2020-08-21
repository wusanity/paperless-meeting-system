package com.szsm.videomeeting.modules.meeting.service;

import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.model.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *文件表service
 * @author LiuJun
 * @date 2020-08-20
 */
public interface FileInfoService extends IService<FileInfo> {
    /**
     * 文件上传
     *
     * @param file            文件
     * @param fileUploadDTO 参数
     * @return 结果
     * @throws MyException
     */
    ApiResult upload(MultipartFile file, FileUploadDTO fileUploadDTO) throws MyException;

    /**
     * 文件删除
     * @param id 主键id
     */
    ApiResult delete(Long id);

    /**
     * 文件下载
     * @param modelFileName 模板文件名称
     * @param response 响应
     * @param fileId 文件id
     * @return
     */
    ApiResult fileDownload(String modelFileName, HttpServletResponse response, Long fileId);

    /**
     * excel导入
     * @param file 上传的excel文件
     * @return
     */
    ApiResult excelImport(MultipartFile file);
}
