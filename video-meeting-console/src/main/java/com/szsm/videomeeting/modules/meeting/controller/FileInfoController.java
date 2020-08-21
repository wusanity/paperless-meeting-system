package com.szsm.videomeeting.modules.meeting.controller;


import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.model.entity.FileInfo;
import com.szsm.videomeeting.modules.kk.enums.PersonErrorEnums;
import com.szsm.videomeeting.modules.meeting.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *文件表controller
 * @author LiuJun
 * @date 2020-08-20
 */
@Controller
@RequestMapping("/file/info")
@Slf4j
public class FileInfoController {
    /**
     * 文件服务
     */
    @Autowired
    private FileInfoService fileInfoService;

    /**
     * 文件上传接口
     * @param file 文件流
     * @param fileUploadDTO 文件上传DTO
     * @return
     */
    @PostMapping(value = "/upload")
    @ResponseBody
    public ApiResult upload(@RequestParam("file") MultipartFile file, FileUploadDTO fileUploadDTO) throws IOException {
        return fileInfoService.upload(file, fileUploadDTO);
    }

    /**
     * 文件删除
     * @param id 文件主键id
     * @return
     */
    @GetMapping(value = "/delete")
    @ResponseBody
    public ApiResult delete(@RequestParam("id")Long id) {
        return fileInfoService.delete(id);
    }
}
