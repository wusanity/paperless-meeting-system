package com.szsm.videomeeting.modules.meeting.controller;

import com.szsm.videomeeting.base.constant.ApiConstant;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.model.entity.Person;
import com.szsm.videomeeting.modules.meeting.service.MeetingPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:14
 * @Description:
 */
@Controller
@RequestMapping("/meetingPerson")
@Slf4j
public class MeetingPersonController {

    @Autowired
    private MeetingPersonService meetingPersonService;

    @RequestMapping("/getByMeetingNo")
    @ResponseBody
    public ApiResult getByMeetingNo(String meetingNo){
        if (StringUtils.isEmpty(meetingNo)){
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }

        List<MeetingPersonDTO> meetingPersonDTOList =  meetingPersonService.getByMeetingNo(meetingNo);

        return ApiResult.ok(meetingPersonDTOList);
    }
}
