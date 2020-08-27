package com.szsm.meeting.base.config.netty.schedule;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.szsm.meeting.base.config.netty.core.NettyConfig;
import com.szsm.meeting.base.config.netty.dto.BaseRequiredDTO;
import com.szsm.meeting.base.config.netty.core.NettyConfig;
import com.szsm.meeting.base.config.netty.dto.BaseRequiredDTO;
import com.szsm.meeting.base.config.netty.dto.FileRequiredDTO;
import com.szsm.meeting.base.constant.Constants;
import com.szsm.meeting.modules.meeting.constant.MeetingConstant;
import com.szsm.meeting.modules.meeting.mapper.MeetingInfoMapper;
import com.szsm.meeting.modules.meeting.model.entity.MeetingInfo;
import com.szsm.meeting.modules.meeting.service.MeetingInfoService;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MeetingSchedule {

    @Autowired
    private MeetingInfoMapper meetingInfoMapper;


    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void scheduleBaseMessage(){
        MeetingInfo meetingInfo = MeetingInfo.builder().onOff(Constants.OFF).build();
        meetingInfo.setDeleted(Constants.DELETED);
        List<MeetingInfo> meetingInfoList = meetingInfoMapper.selectList(new QueryWrapper<MeetingInfo>()
                .lambda()
                .eq(MeetingInfo::getOnOff, meetingInfo.getOnOff())
                .eq(MeetingInfo::getDeleted, meetingInfo.getDeleted()));
        for (MeetingInfo info : meetingInfoList) {
            ConcurrentHashMap<Long, String> userMeetingMap = NettyConfig.getUserMeetingMap();
            for (Long userId : userMeetingMap.keySet()) {
                if (userMeetingMap.get(userId).equals(info.getMeetingNo())) {
                    Channel channel = NettyConfig.getUserChannelMap().get(userId);
                    BaseRequiredDTO baseRequiredDTO = BaseRequiredDTO.builder().build();
                    baseRequiredDTO.setMeetingNo(info.getMeetingNo());
                    NettyConfig.push(channel,baseRequiredDTO);
                }
            }
        }


    }


    /**
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void scheduleFileMessage(){



    }

}
