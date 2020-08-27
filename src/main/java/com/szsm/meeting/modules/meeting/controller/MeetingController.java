package com.szsm.meeting.modules.meeting.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.szsm.meeting.base.constant.ApiConstant;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.exception.MyException;
import com.szsm.meeting.modules.meeting.model.dto.MeetingDetailsDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.service.MeetingInfoService;
import com.szsm.meeting.base.constant.ApiConstant;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.exception.MyException;
import com.szsm.meeting.modules.meeting.model.dto.*;
import com.szsm.meeting.modules.meeting.model.entity.MeetingInfo;
import com.szsm.meeting.modules.meeting.service.MeetingBaseService;
import com.szsm.meeting.modules.meeting.service.MeetingInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/meeting")
@Slf4j
public class MeetingController {

    @Autowired
    private MeetingInfoService meetingInfoService;
    @Autowired
    private MeetingBaseService meetingBaseService;

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

    /**
     * 添加会议
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/addMeeting",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult addMeeting(@RequestBody @Validated MeetingDTO meetingDTO) {
        meetingBaseService.addMeeting(meetingDTO.getMeetingInfoDTO(),meetingDTO.getAgendaDTOList(),meetingDTO.getPersonDTOList());
        return ApiResult.ok("添加会议成功！");
    }

    /**
     * 通过会议编号 删除会议
     * @param meetingInfoDTO
     * @return
     */
    @RequestMapping(value = "/removeMeeting" , method = RequestMethod.POST)
    @ResponseBody
    public ApiResult removeMeetingByMeetingNo(MeetingInfoDTO meetingInfoDTO) {
        if (StringUtils.isEmpty(meetingInfoDTO.getMeetingNo())){
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }
        meetingBaseService.removeMeeting(meetingInfoDTO);
        return ApiResult.ok("会议删除成功！");
    }

    /**
     * 通过会议编号开启/关闭会议
     * @param meetingInfoDTO
     * @return
     */
    @RequestMapping(value = "/onOffMeeting" ,method = RequestMethod.POST)
    @ResponseBody
    public ApiResult onOffMeeting(MeetingInfoDTO meetingInfoDTO) {
        log.info("canshu"+meetingInfoDTO.getOnOff());
        String message = "";
        if(StringUtils.isEmpty(meetingInfoDTO.getMeetingNo())) {
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }
        if(meetingInfoDTO.getOnOff() == null) {
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议状态不能为空！");
        }
        if (meetingInfoDTO.getOnOff() != 1 && meetingInfoDTO.getOnOff() != 2) {
            throw new MyException(ApiConstant.Code.PARAM_ERROR,"会议状态参数错误");
        }
        meetingBaseService.updateStatus(meetingInfoDTO);
        if( meetingInfoDTO.getOnOff() == 1 ) {
            message = "会议开启成功！";
        }
        if (meetingInfoDTO.getOnOff() == 2) {
            message = "会议关闭成功！";
        }
        return ApiResult.ok(message);
    }

    /**
     * 编辑会议
     * @param meetingDTO
     * @return
     */
    @RequestMapping(value = "/editMeeting",method = RequestMethod.POST)
    @ResponseBody
    public ApiResult editMeeting(@RequestBody @Validated({MeetingInfoDTO.class,MeetingAgendaDTO.class,MeetingPersonDTO.class})MeetingDTO meetingDTO) {
        meetingBaseService.updateMeeting(meetingDTO.getMeetingInfoDTO(),meetingDTO.getAgendaDTOList(),meetingDTO.getPersonDTOList());
        return ApiResult.ok("会议更新成功！");
    }



}
