package com.szsm.videomeeting.modules.meeting.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.szsm.videomeeting.base.BaseEntity;
import com.szsm.videomeeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.model.dto.MeetingDetailsDTO;
import com.szsm.videomeeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.model.entity.MeetingAgenda;
import com.szsm.videomeeting.model.entity.MeetingInfo;
import com.szsm.videomeeting.model.entity.MeetingPerson;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingAgendaMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingInfoMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingPersonMapper;
import com.szsm.videomeeting.modules.meeting.service.MeetingInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeetingInfoServiceImpl extends ServiceImpl<MeetingInfoMapper, MeetingInfo> implements MeetingInfoService {

    @Autowired
    private MeetingInfoMapper meetingInfoMapper;

    @Autowired
    private MeetingPersonMapper meetingPersonMapper;

    @Autowired
    private MeetingAgendaMapper meetingAgendaMapper;


    @Override
    public Page<MeetingInfo> getList(Page<MeetingInfo> page, MeetingInfoDTO meetingInfoDTO) {
        log.error(meetingInfoDTO.toString());
        return page.setRecords(meetingInfoMapper.getList(page, meetingInfoDTO));
    }

    /**
     * 通过会议号获取会议详细信息，包括会议基本信息、参会人信息和会议议程信息
     *
     * @param meetingNo
     * @return
     */
    @Override
    public MeetingDetailsDTO getMeetingDetails(String meetingNo) {
        // 获取会议基本信息
        MeetingInfo meetingInfo = meetingInfoMapper.selectOne(new QueryWrapper<MeetingInfo>().lambda()
                .eq(MeetingInfo::getMeetingNo, meetingNo));

        if (meetingInfo == null) {
            return null;
        }
        MeetingInfoDTO meetingInfoDTO = new MeetingInfoDTO();
        BeanUtils.copyProperties(meetingInfo, meetingInfoDTO);

        // 获取参会人信息
        List<MeetingPerson> meetingPeople = meetingPersonMapper.selectList(new QueryWrapper<MeetingPerson>().lambda()
                .eq(MeetingPerson::getMeetingNo, meetingNo));

        String jsonString = JSONArray.toJSONString(meetingPeople);
        List<MeetingPersonDTO> meetingPersonDTOList = JSON.parseArray(jsonString, MeetingPersonDTO.class);


        // 获取会议议程信息
        List<MeetingAgenda> meetingAgendas = meetingAgendaMapper.selectList(new QueryWrapper<MeetingAgenda>().lambda()
                .eq(MeetingAgenda::getMeetingNo, meetingNo));

        String jsonString1 = JSONArray.toJSONString(meetingAgendas);
        List<MeetingAgendaDTO> meetingAgendaDTOList = JSON.parseArray(jsonString1, MeetingAgendaDTO.class);


        return MeetingDetailsDTO
                .builder()
                .meetingInfoDTO(meetingInfoDTO)
                .meetingPersonDTOList(meetingPersonDTOList)
                .meetingAgendaDTOList(meetingAgendaDTOList)
                .build();
    }

    /**
     * 通过会议号获取会议基本信息
     *
     * @param meetingNo
     * @return
     */
    @Override
    public MeetingInfoDTO getByMeetingNo(String meetingNo) {
        MeetingInfo meetingInfo = meetingInfoMapper.selectOne(new QueryWrapper<MeetingInfo>().lambda()
                .eq(MeetingInfo::getMeetingNo, meetingNo));

        if (meetingInfo == null) {
            return null;
        }
        MeetingInfoDTO meetingInfoDTO = new MeetingInfoDTO();
        BeanUtils.copyProperties(meetingInfo, meetingInfoDTO);
        return meetingInfoDTO;
    }
}
