package com.szsm.videomeeting.modules.meeting.controller;

import com.szsm.videomeeting.base.constant.ApiConstant;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.modules.meeting.service.MeetingAgendaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:27
 * @Description:
 */
@RequestMapping("/agenda")
@Controller
@Slf4j
public class MeetingAgendaController {

    @Autowired
    private MeetingAgendaService meetingAgendaService;

    /**
     * 根据会议号获取会议议程信息
     * @param meetingNo
     * @return
     */
    @RequestMapping("/getByMeetingNo")
    @ResponseBody
    public ApiResult getByMeetingNo(String meetingNo){
        if (StringUtils.isEmpty(meetingNo)){
            throw new MyException(ApiConstant.Code.PARAMS_LACK_CODE,"会议号不能为空！");
        }

        List<MeetingAgendaDTO> meetingAgendaDTOList = meetingAgendaService.getByMeetingNo(meetingNo);

        return ApiResult.ok(meetingAgendaDTOList);
    }
}
