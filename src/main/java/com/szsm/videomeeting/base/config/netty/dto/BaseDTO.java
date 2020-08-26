package com.szsm.videomeeting.base.config.netty.dto;

import lombok.Data;

@Data
public class BaseDTO extends BaseDataBody{

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 会议编号
     */
    private String meetingNo;

    /**
     * 版本号
     */
    private Integer version;

}
