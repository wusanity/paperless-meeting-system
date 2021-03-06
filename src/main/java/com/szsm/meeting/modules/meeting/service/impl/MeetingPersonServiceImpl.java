package com.szsm.meeting.modules.meeting.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szsm.meeting.modules.meeting.mapper.MeetingPersonMapper;
import com.szsm.meeting.modules.meeting.model.dto.MeetingPersonDTO;
import com.szsm.meeting.modules.meeting.model.entity.MeetingPerson;
import com.szsm.meeting.modules.meeting.mapper.MeetingPersonMapper;
import com.szsm.meeting.modules.meeting.model.dto.MeetingPersonDTO;
import com.szsm.meeting.modules.meeting.model.entity.MeetingPerson;
import com.szsm.meeting.modules.meeting.service.MeetingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:19
 * @Description:
 */
@Service
public class MeetingPersonServiceImpl extends ServiceImpl<MeetingPersonMapper, MeetingPerson> implements MeetingPersonService {

    @Autowired
    private MeetingPersonMapper meetingPersonMapper;

    /**
     * 通过会议号获取参会人信息（未删除）
     * @param meetingNo
     * @return
     */
    @Override
    public List<MeetingPersonDTO> getByMeetingNo(String meetingNo) {
        List<MeetingPerson> meetingPeople = meetingPersonMapper.selectList(new QueryWrapper<MeetingPerson>().lambda().eq(MeetingPerson::getMeetingNo,
                meetingNo));

        if (CollectionUtils.isEmpty(meetingPeople)){
            return null;
        }
        String jsonString = JSONArray.toJSONString(meetingPeople);
        List<MeetingPersonDTO> meetingPersonDTOList = JSON.parseArray(jsonString, MeetingPersonDTO.class);
        return meetingPersonDTOList;
    }
}
