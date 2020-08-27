package com.szsm.meeting.modules.meeting.service;


import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingAgendaDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingPersonDTO;

import java.util.List;

public interface MeetingBaseService {
    void addMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList);
    void removeMeeting(MeetingInfoDTO meetingInfoDTO);
    void updateStatus(MeetingInfoDTO meetingInfoDTO);
    void updateMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList);
}
