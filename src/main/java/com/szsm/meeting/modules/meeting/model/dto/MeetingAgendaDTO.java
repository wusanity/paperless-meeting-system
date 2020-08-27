package com.szsm.meeting.modules.meeting.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 议程结束时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 议程内容
     */
    @NotBlank
    private String detail;

    /**
     * 文件路径
     */
    private String url;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 创建人
     */
    private Long createBy;


}
