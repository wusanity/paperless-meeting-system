package com.szsm.meeting.base.config.netty.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequiredDTO extends ResponseContext{

    /**
     * 会议号
     */
    String meetingNo;
}
