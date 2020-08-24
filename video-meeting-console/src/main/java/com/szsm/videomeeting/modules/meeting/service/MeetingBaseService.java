package com.szsm.videomeeting.modules.meeting.service;

import com.szsm.videomeeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.model.entity.MeetingAgenda;
import com.szsm.videomeeting.model.entity.MeetingInfo;

import java.util.List;

public interface MeetingBaseService {
    void addMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList);
    void removeMeeting(MeetingInfoDTO meetingInfoDTO);
    void updateStatus(MeetingInfoDTO meetingInfoDTO);
    void updateMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList);
}
