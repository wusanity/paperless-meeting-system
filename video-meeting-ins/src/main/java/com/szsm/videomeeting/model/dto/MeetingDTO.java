package com.szsm.videomeeting.model.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MeetingDTO {

    private MeetingInfoDTO meetingInfoDTO;

    private List<MeetingAgendaDTO> agendaDTOList;

    private List<MeetingPersonDTO> personDTOList;
}
