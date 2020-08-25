package com.szsm.videomeeting.modules.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.szsm.videomeeting.base.exception.MyException;
import com.szsm.videomeeting.base.util.BeanUtil;
import com.szsm.videomeeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.model.entity.MeetingAgenda;
import com.szsm.videomeeting.model.entity.MeetingInfo;
import com.szsm.videomeeting.model.entity.MeetingPerson;
import com.szsm.videomeeting.modules.meeting.service.MeetingAgendaService;
import com.szsm.videomeeting.modules.meeting.service.MeetingBaseService;
import com.szsm.videomeeting.modules.meeting.service.MeetingInfoService;
import com.szsm.videomeeting.modules.meeting.service.MeetingPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class MeetingBaseServiceImpl implements MeetingBaseService {
    @Autowired
    private MeetingInfoService meetingInfoService;
    @Autowired
    private MeetingAgendaService meetingAgendaService;
    @Autowired
    private MeetingPersonService meetingPersonService;

    /**
     * 添加会议
     * @param meetingInfoDTO
     * @param agendaDTOList
     * @param personDTOList
     */
    @Override
    public void addMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList) {
        String meetingNo = UUID.randomUUID().toString();
        MeetingInfo meetingInfo = BeanUtil.copyPropertiesByFastJson(meetingInfoDTO, MeetingInfo.class);
        meetingInfo.setMeetingNo(meetingNo);
        boolean meetingInfoSave = meetingInfoService.save(meetingInfo);
        if(!meetingInfoSave) {
            throw new MyException("会议基本信息保存失败");
        }
        List<MeetingAgenda> meetingAgendaList = BeanUtil.copyPropertiesByFastJson(agendaDTOList, MeetingAgenda.class);
        for (MeetingAgenda meetingAgenda:meetingAgendaList) {
            meetingAgenda.setMeetingNo(meetingNo);
        }
        boolean agendaSave = meetingAgendaService.saveBatch(meetingAgendaList);
        if(!agendaSave) {
            throw new MyException("会议议程保存失败");
        }
        List<MeetingPerson> meetingPersonList = BeanUtil.copyPropertiesByFastJson(personDTOList, MeetingPerson.class);
        for (MeetingPerson meetingPerson:meetingPersonList) {
            meetingPerson.setMeetingNo(meetingNo);
        }
        boolean pessonSave = meetingPersonService.saveBatch(meetingPersonList);
        if(!pessonSave) {
            throw new MyException("参会人员保存失败");
        }
    }

    /**
     * 通过会议编号删除会议
     * @param meetingInfoDTO
     */
    @Override
    public void removeMeeting(MeetingInfoDTO meetingInfoDTO) {
        Map<String,Object> map = new HashMap<>();
        map.put("meeting_no",meetingInfoDTO.getMeetingNo());
        boolean removeInfo = meetingInfoService.removeByMap(map);
        if(!removeInfo) {
            throw new MyException("删除会议基本信息失败");
        }
        boolean removeAgenda = meetingAgendaService.removeByMap(map);
        if (!removeAgenda) {
            throw new MyException("删除会议议程失败");
        }
        boolean removePerson = meetingPersonService.removeByMap(map);
        if(!removePerson) {
            throw new MyException("删除与会人员失败");
        }


    }

    /**
     * 通过会议编号 开启，关闭会议
     * @param meetingInfoDTO
     */
    @Override
    public void updateStatus(MeetingInfoDTO meetingInfoDTO) {
        UpdateWrapper<MeetingInfo> wrapper = new UpdateWrapper<>();
        wrapper.set("on_off",meetingInfoDTO.getOnOff()).eq("meeting_no",meetingInfoDTO.getMeetingNo());
        boolean update = meetingInfoService.update(null, wrapper);
        if(!update) {
            throw new MyException("修改会议状态失败");
        }

    }

    /**
     * 编辑会议
     * @param meetingInfoDTO
     * @param agendaDTOList
     * @param personDTOList
     */
    @Override
    public void updateMeeting(MeetingInfoDTO meetingInfoDTO, List<MeetingAgendaDTO> agendaDTOList, List<MeetingPersonDTO> personDTOList) {

        this.removeMeeting(meetingInfoDTO);
        this.addMeeting(meetingInfoDTO,agendaDTOList,personDTOList);
    }
}
