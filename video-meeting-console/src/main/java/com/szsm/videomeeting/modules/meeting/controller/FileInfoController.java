package com.szsm.videomeeting.modules.meeting.controller;


import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.FileUploadDTO;
import com.szsm.videomeeting.modules.meeting.service.FileInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult upload(@RequestParam("file") MultipartFile file, FileUploadDTO fileUploadDTO) throws MyException {
        return fileInfoService.upload(file, fileUploadDTO);
    }

    /**
     * 文件删除
     * @param id 文件主键id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult delete(@RequestParam("id")Long id) {
        return fileInfoService.delete(id);
    }

    /**
     * 文件下载
     * @param modelFileName 模板文件名称
     * @param response 响应
     * @param id 文件主键
     * @return
     */
    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    @ResponseBody
    public ApiResult fileDownload(String modelFileName, HttpServletResponse response, Long id) {
        return fileInfoService.fileDownload(modelFileName, response, id);
    }

    /**
     * excel导入
     * @param file 上传的文件
     * @return
     */
    @RequestMapping(value = "/excelImport", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult excelImport(@RequestParam("file") MultipartFile file) {
        return fileInfoService.excelImport(file);
    }
}
