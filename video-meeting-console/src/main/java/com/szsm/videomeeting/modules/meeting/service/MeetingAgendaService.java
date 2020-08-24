package com.szsm.videomeeting.modules.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szsm.videomeeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.model.entity.MeetingAgenda;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:29
 * @Description:
 */
public interface MeetingAgendaService extends IService<MeetingAgenda> {

    /**
     * 根据会议号获取会议议程
     * @param meetingNo
     * @return
     */
    List<MeetingAgendaDTO> getByMeetingNo(String meetingNo);
}
