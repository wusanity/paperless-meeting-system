package com.szsm.videomeeting.modules.meeting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.videomeeting.base.constant.ApiConstant;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.MeetingDetailsDTO;
import com.szsm.videomeeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.model.entity.MeetingInfo;
import com.szsm.videomeeting.modules.meeting.service.MeetingInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/meeting")
@Slf4j
public class MeetingController {

    @Autowired
    private MeetingInfoService meetingInfoService;

    @RequestMapping("/getList")
    @ResponseBody
    public ApiResult getList(MeetingInfoDTO meetingInfoDTO){
        log.info("获取会议信息");
        Page<MeetingInfo> page = new Page<>(meetingInfoDTO.getPage(), meetingInfoDTO.getLimit());
        meetingInfoService.getList(page, meetingInfoDTO);
        return ApiResult.successTable(page);
    }


    /**
     * 一个会议号获取所有信息，包含基本信息、参会人员和会议议程
     * @param meetingNo
     * @return
     */
    @RequestMapping("/getMeetingDetails")
    @ResponseBody
    public ApiResult getMeetingDetails(String meetingNo){
        if (StringUtils.isEmpty(meetingNo)){
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }
        MeetingDetailsDTO meetingDetailsDTO = meetingInfoService.getMeetingDetails(meetingNo);

        return ApiResult.ok(meetingDetailsDTO);
    }

    /**
     * 通过会议号获取会议基本信息
     * @param meetingNo
     * @return
     */
    @RequestMapping("/getByMeetingNo")
    @ResponseBody
    public ApiResult getByMeetingNo(String meetingNo){
        if (StringUtils.isEmpty(meetingNo)){
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }

        MeetingInfoDTO meetingInfoDTO = meetingInfoService.getByMeetingNo(meetingNo);

        return ApiResult.ok(meetingInfoDTO);
    }



}
