package com.szsm.videomeeting.base.config.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("heartBeatImpl")
public class HeartBeatImpl implements BaseFacade {

    @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {
        Map<String ,Object> map = new HashMap<>();
        map.put("resType",0);
        String rsp = JSON.toJSONString(map);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
        return ApiResult.SUCCESS;
    }
}
