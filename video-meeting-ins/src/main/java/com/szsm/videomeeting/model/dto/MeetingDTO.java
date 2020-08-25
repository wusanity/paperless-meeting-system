package com.szsm.videomeeting.model.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

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
