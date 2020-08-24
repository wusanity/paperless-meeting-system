package com.szsm.videomeeting.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.szsm.videomeeting.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 17:12
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(description = "参会人信息表")
@TableName("meeting_person")
public class MeetingPerson extends BaseEntity {

    private static final long serialVersionUID = 1485939997999398182L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 会议号
     */
    @ApiModelProperty(value = "会议号")
    @TableField("meeting_no")
    private String meetingNo;
    /**
     * 参会人姓名
     */
    @ApiModelProperty(value = "参会人姓名")
    @TableField("name")
    private String name;
    /**
     * 参会人职位
     */
    @ApiModelProperty(value = "参会人职位")
    @TableField("position")
    private String position;
    /**
     * 参会人设备
     */
    @ApiModelProperty(value = "设备")
    @TableField("equipment")
    private String equipment;

    /**
     * 设备状态 ：0离线，1在线
     */
    @ApiModelProperty(value = "设备状态")
    @TableField("state")
    private Integer state;


}
