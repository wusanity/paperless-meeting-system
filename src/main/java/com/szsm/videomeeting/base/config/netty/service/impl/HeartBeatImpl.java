package com.szsm.videomeeting.base.config.netty.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szsm.videomeeting.base.config.netty.core.NettyConfig;
import com.szsm.videomeeting.base.config.netty.dto.*;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.modules.meeting.constant.MeetingConstant;
import com.szsm.videomeeting.modules.meeting.mapper.FileInfoMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingAgendaMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingInfoMapper;
import com.szsm.videomeeting.modules.meeting.model.entity.FileInfo;
import com.szsm.videomeeting.modules.meeting.model.entity.MeetingAgenda;
import com.szsm.videomeeting.modules.meeting.model.entity.MeetingInfo;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("heartBeatImpl")
public class HeartBeatImpl implements BaseFacade {

    @Autowired
    private MeetingInfoMapper meetingInfoMapper;

    @Autowired
    private FileInfoMapper fileInfoMapper;

    @Autowired
    private MeetingAgendaMapper meetingAgendaMapper;

   /* @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {
        Map<String ,Object> map = new HashMap<>();
        map.put("resType",0);
        String rsp = JSON.toJSONString(map);
        ctx.channel().writeAndFlush(Unpooled.copiedBuffer(rsp.getBytes()));
        return ApiResult.SUCCESS;
    }*/

    @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {
        BaseDTO baseDTO = (BaseDTO) dataOutside.getBaseDataBody();
        MeetingInfo meetingInfo = meetingInfoMapper.selectOne(new QueryWrapper<MeetingInfo>()
                .lambda()
                .eq(MeetingInfo::getMeetingNo, baseDTO.getMeetingNo()));
        if (meetingInfo == null){
            return ApiResult.SUCCESS;
        }
        if (!meetingInfo.getVersion().equals(baseDTO.getVersion())){
            BaseRequiredDTO baseRequiredDTO = BaseRequiredDTO.builder().meetingNo(baseDTO.getMeetingNo()).build();
            baseRequiredDTO.setResType(DataContext.REPLY_BASE_MESSAGE);
            NettyConfig.push(ctx.channel(),baseRequiredDTO);
        }
        //查出所有的议程
        List<MeetingAgenda> meetingAgendaList = meetingAgendaMapper.selectList(new QueryWrapper<MeetingAgenda>()
                .lambda()
                .eq(MeetingAgenda::getMeetingNo, baseDTO.getMeetingNo())
                .eq(MeetingAgenda::getDeleted, MeetingConstant.DELETED));
        if (CollectionUtils.isEmpty(meetingAgendaList)){
            return ApiResult.SUCCESS;
        }
        ArrayList<String> urls = new ArrayList<>();
        for (MeetingAgenda meetingAgenda : meetingAgendaList) {
            FileInfo fileInfo = fileInfoMapper.selectOne(new QueryWrapper<FileInfo>()
                    .lambda()
                    .eq(FileInfo::getFilePath, meetingAgenda.getUrl())
                    .eq(FileInfo::getUserId, baseDTO.getUserId())
                    .eq(FileInfo::getDeleted, MeetingConstant.DELETED)
                    .eq(FileInfo::getType,MeetingConstant.FILE_TYPE))
                    ;
            if (fileInfo == null){
                urls.add(meetingAgenda.getUrl());
            }
        }

        if (!CollectionUtils.isEmpty(urls)){
            FileRequiredDTO fileRequiredDTO = FileRequiredDTO.builder().url(urls).build();
            fileRequiredDTO.setResType(DataContext.REPLY_FILE);
            NettyConfig.push(ctx.channel(),fileRequiredDTO);
        }


        return ApiResult.SUCCESS;
    }
}
