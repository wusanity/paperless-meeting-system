package com.szsm.videomeeting.base.config.netty.dto;

import lombok.Data;

@Data
public class BaseDTO extends BaseDataBody{

    private Long userId;

    private String meetingNo;

}
