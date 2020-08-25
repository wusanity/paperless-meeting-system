package com.szsm.videomeeting.base.config.netty.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szsm.videomeeting.base.config.netty.core.NettyConfig;
import com.szsm.videomeeting.base.config.netty.dto.BaseRequiredDTO;
import com.szsm.videomeeting.base.config.netty.dto.DataOutside;
import com.szsm.videomeeting.base.config.netty.dto.FileRequiredDTO;
import com.szsm.videomeeting.base.config.netty.service.BaseFacade;
import com.szsm.videomeeting.base.context.ApiResult;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingPersonMapper;
import com.szsm.videomeeting.modules.meeting.model.entity.MeetingPerson;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service("meetingAddImpl")
public class MeetingAddUpdateImpl implements BaseFacade {

    /**
     * 新增/编辑会议的时候，向已连接的设备推送信息
     * @param ctx         netty上下文
     * @param dataOutside 外部数据
     * @return
     */
    @Autowired
    private MeetingPersonMapper meetingPersonMapper;


    @Override
    public ApiResult handle(ChannelHandlerContext ctx, DataOutside dataOutside) {

        String meetingNo = "";
        List<MeetingPerson> meetingPersonList = meetingPersonMapper.selectList(new QueryWrapper<MeetingPerson>()
                .lambda()
                .eq(MeetingPerson::getMeetingNo, meetingNo));
        for (MeetingPerson meetingPerson : meetingPersonList) {
            NettyConfig.getUserMeetingMap().put(meetingPerson.getId(),meetingNo);
        }

        ConcurrentHashMap<Long, String> userMeetingMap = NettyConfig.getUserMeetingMap();

        for (Long userId : userMeetingMap.keySet()) {
            if (userMeetingMap.get(userId).equals(meetingNo)) {
                Channel channel = NettyConfig.getUserChannelMap().get(userId);
                // 发送基本信息
                BaseRequiredDTO baseRequiredDTO = BaseRequiredDTO.builder().build();
                baseRequiredDTO.setMeetingNo("111");
                NettyConfig.push(channel,baseRequiredDTO);
                // 发送下载文件信息
                FileRequiredDTO fileRequiredDTO = FileRequiredDTO.builder().build();
                fileRequiredDTO.setUrl(new ArrayList<>());
                NettyConfig.push(channel,fileRequiredDTO);
                try {
                    // 发送文件请求每个间隔延迟1s
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return ApiResult.SUCCESS;
    }
}
