package com.szsm.videomeeting.modules.meeting.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingAgendaMapper;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.modules.meeting.model.entity.MeetingAgenda;
import com.szsm.videomeeting.modules.meeting.service.MeetingAgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:30
 * @Description:
 */
@Service
public class MeetingAgendaServiceImpl extends ServiceImpl<MeetingAgendaMapper, MeetingAgenda> implements MeetingAgendaService {

    @Autowired
    private MeetingAgendaMapper meetingAgendaMapper;


    /**
     * 通过会议号获取会议议程信息（未删除的）
     * @param meetingNo
     * @return
     */
    @Override
    public List<MeetingAgendaDTO> getByMeetingNo(String meetingNo) {
        List<MeetingAgenda> meetingAgendas =
                meetingAgendaMapper.selectList(new QueryWrapper<MeetingAgenda>().lambda().eq(MeetingAgenda::getMeetingNo,
                meetingNo));

        if(CollectionUtils.isEmpty(meetingAgendas)){
            return null;
        }
        String jsonString = JSONArray.toJSONString(meetingAgendas);

        return JSON.parseArray(jsonString,MeetingAgendaDTO.class);
    }
}
