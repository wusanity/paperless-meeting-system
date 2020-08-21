package com.szsm.videomeeting.modules.meeting.service;

import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.model.entity.FileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
     * @param fileUploadDTO 会议议程id
     * @return 结果
     * @throws IOException
     */
    ApiResult upload(MultipartFile file, FileUploadDTO fileUploadDTO) throws IOException;

    /**
     * 文件删除
     * @param id 会议议程id
     */
    ApiResult delete(Long id);
}
