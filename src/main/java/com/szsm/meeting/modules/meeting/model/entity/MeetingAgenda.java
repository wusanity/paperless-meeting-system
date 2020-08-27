package com.szsm.meeting.modules.meeting.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szsm.meeting.base.context.BaseEntity;
import com.szsm.meeting.base.context.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 17:20
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "会议议程信息表")
@TableName("meeting_agenda")
public class MeetingAgenda extends BaseEntity {

    private static final long serialVersionUID = 7326633112665342094L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 会议号
     */
    @ApiModelProperty(value = "会议号")
    @TableField("meeting_no")
    private String meetingNo;
    /**
     * 议程开始时间
     */
    @ApiModelProperty(value = "议程开始时间")
    @TableField("begin_time")
    private Date beginTime;
    /**
     * 议程结束时间
     */
    @ApiModelProperty(value = "议程结束时间")
    @TableField("end_time")
    private Date endTime;

    /**
     * 议程内容
     */
    @ApiModelProperty(value = "议程内容")
    @TableField("detail")
    private String detail;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    @TableField("url")
    private String url;
}
