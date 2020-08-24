package com.szsm.videomeeting.model.dto;

import com.szsm.videomeeting.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String name;
    /**
     * 参会人职位
     */
    private String position;
    /**
     * 参会人设备
     */
    private String equipment;

    /**
     * 设备状态 ：0离线，1在线
     */
    private Integer state;
}
