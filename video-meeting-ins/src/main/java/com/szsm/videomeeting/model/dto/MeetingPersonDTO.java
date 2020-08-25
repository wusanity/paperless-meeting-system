package com.szsm.videomeeting.model.dto;

import com.szsm.videomeeting.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 16:24
 * @Description: 参会人信息
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeetingPersonDTO  {

    /**
     * 会议号
     */
    private String meetingNo;
    /**
     * 参会人姓名
     */
    @NotBlank
    private String name;
    /**
     * 参会人职位
     */
    @NotBlank
    private String position;
    /**
     * 参会人设备
     */
    @NotBlank
    private String equipment;

    /**
     * 设备状态 ：0离线，1在线
     */
    private Integer state;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 创建人
     */
    private Long createBy;
}
