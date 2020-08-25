package com.szsm.videomeeting.model.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.szsm.videomeeting.base.BasePageQuery;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/20 18:25
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MeetingInfoDTO extends BasePageQuery {

    /**
     * id
     */
    private Long id;
    /**
     * 会议主题
     */
    @NotBlank
    private String topic;
    /**
     * 会议是否开启，状态：0未开始，1启动，2结束
     */
    private Integer onOff;
    /**
     * 会议开始时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;
    /**
     * 会议结束时间
     */
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 会议号
     */
    private String meetingNo;
    /**
     * 会议地址
     */
    @NotBlank
    private String location;

    /**
     * 查询：开始时间
     */
    private Date searchTimeStart;

    /**
     * 查询：结束时间
     */
    private Date searchTimeEnd;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 创建人
     */
    private Long createBy;
}
