package com.szsm.videomeeting.modules.meeting.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szsm.videomeeting.base.context.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/20 17:13
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("meeting")
@ApiModel(description = "会议表基本信息表")
public class MeetingInfo extends BaseEntity<MeetingInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 会议号，全局唯一
     */
    @ApiModelProperty(value = "会议号")
    @TableField(value = "meeting_no")
    private String meetingNo;

    /**
     * 会议主题
     */
    @ApiModelProperty(value = "会议主题")
    @TableField(value = "topic")
    private String topic;

    /**
     * 会议开始时间
     */
    @TableField(value = "begin_time")
    private Date beginTime;

    /**
     * 会议结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 会议地点
     */
    @TableField(value = "location")
    private String location;

    /**
     * 会议当前状态：0未开始，1启动，2结束
     */
    @TableField(value = "on_off")
    private Integer onOff;



}
