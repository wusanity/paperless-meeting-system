package com.szsm.meeting.modules.meeting.controller;

import com.szsm.meeting.base.constant.ApiConstant;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.exception.MyException;
import com.szsm.meeting.base.constant.ApiConstant;
import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.exception.MyException;
import com.szsm.meeting.modules.meeting.model.dto.MeetingPersonDTO;
import com.szsm.meeting.modules.meeting.service.MeetingPersonService;
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

    /**
     * 根据会议号获取参会人员信息
     * @param meetingNo
     * @return
     */
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
