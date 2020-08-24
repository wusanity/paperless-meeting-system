package com.szsm.videomeeting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 16:04
 * @Description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MeetingDetailsDTO  {

    /**
     * 会议基本信息
     */
    private MeetingInfoDTO meetingInfoDTO;

    /**
     * 参会人员信息
     */
    private List<MeetingPersonDTO> meetingPersonDTOList;

    /**
     * 会议议程信息
     */
    private List<MeetingAgendaDTO> meetingAgendaDTOList;



}
