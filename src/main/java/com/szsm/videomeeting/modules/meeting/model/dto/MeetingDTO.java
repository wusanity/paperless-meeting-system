package com.szsm.videomeeting.modules.meeting.model.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MeetingDTO {

    @NotNull
    private MeetingInfoDTO meetingInfoDTO;

    @NotEmpty
    private List<MeetingAgendaDTO> agendaDTOList;

    @NotEmpty
    private List<MeetingPersonDTO> personDTOList;
}
