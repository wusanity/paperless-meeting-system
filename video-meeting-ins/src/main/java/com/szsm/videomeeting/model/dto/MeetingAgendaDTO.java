package com.szsm.videomeeting.model.dto;

import lombok.*;

import java.util.Date;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 16:15
 * @Description:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class MeetingAgendaDTO {

    /**
     * 会议号
     */
    private String meetingNo;
    /**
     * 议程开始时间
     */
    private Date beginTime;
    /**
     * 议程结束时间
     */
    private Date endTime;

    /**
     * 议程内容
     */
    private String detail;

    /**
     * 文件路径
     */
    private String url;


}
