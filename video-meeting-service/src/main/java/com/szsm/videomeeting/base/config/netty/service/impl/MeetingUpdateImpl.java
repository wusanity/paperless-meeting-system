package com.szsm.videomeeting.base.config.netty.service.impl;

import com.szsm.videomeeting.base.config.netty.dto.BaseDTO;
import com.szsm.videomeeting.base.config.netty.dto.BaseDataBody;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("meetingUpdateImpl")
public class MeetingUpdateImpl implements BaseFacade {
    @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {
        BaseDataBody baseDataBody = dataOutside.getBaseDataBody();
        if (baseDataBody instanceof BaseDTO){
            BaseDTO a  = (BaseDTO) baseDataBody;
        }
        Map<String ,Object> map = new HashMap<>();
        map.put("opType",dataOutside.getHeader().getOpType());
        return ApiResult.ok(map);
    }
}
