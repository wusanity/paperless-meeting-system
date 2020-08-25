package com.szsm.videomeeting;


import com.szsm.videomeeting.modules.meeting.mapper.MeetingAgendaMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingInfoMapper;
import com.szsm.videomeeting.modules.meeting.mapper.MeetingPersonMapper;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingAgendaDTO;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.modules.meeting.service.MeetingAgendaService;
import com.szsm.videomeeting.modules.meeting.service.MeetingBaseService;
import com.szsm.videomeeting.modules.meeting.service.MeetingInfoService;
import com.szsm.videomeeting.modules.meeting.service.MeetingPersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest(classes = VideoMeetingApplication.class)
@RunWith(SpringRunner.class)
public class MyTest {
    @Autowired
    private MeetingBaseService meetingBaseService;
    @Autowired
    private MeetingInfoService meetingInfoService;
    @Autowired
    private MeetingPersonService meetingPersonService;
    @Autowired
    private MeetingAgendaService meetingAgendaService;
    @Autowired
    private MeetingInfoMapper meetingInfoMapper;
    @Autowired
    private MeetingPersonMapper meetingPersonMapper;
    @Autowired
    private MeetingAgendaMapper meetingAgendaMapper;
    @Test
    public void testAddMeeting(){
        MeetingInfoDTO meetingInfoDTO = MeetingInfoDTO.builder()
                .topic("test")
                .location("location")
                .beginTime(new Date())
                .endTime(new Date())
                .createBy(0L)
                .updateBy(0L)
                .build();
        MeetingAgendaDTO meetingAgendaDTO1 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai1").createBy(0L).updateBy(0L).build();
        MeetingAgendaDTO meetingAgendaDTO2 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai2").createBy(0L).updateBy(0L).build();
        MeetingAgendaDTO meetingAgendaDTO3 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai3").createBy(0L).updateBy(0L).build();
        List<MeetingAgendaDTO> agendaDTOS = new ArrayList<>();
        agendaDTOS.add(meetingAgendaDTO1);
        agendaDTOS.add(meetingAgendaDTO2);
        agendaDTOS.add(meetingAgendaDTO3);

        MeetingPersonDTO personDTO1 = MeetingPersonDTO.builder().name("one").equipment("nicai1").position("unknow1").createBy(0l).updateBy(0l).build();
        MeetingPersonDTO personDTO2 = MeetingPersonDTO.builder().name("two").equipment("nicai2").position("unknow2").createBy(0l).updateBy(0l).build();
        MeetingPersonDTO personDTO3 = MeetingPersonDTO.builder().name("three").equipment("nicai3").position("unknow3").createBy(0l).updateBy(0l).build();
         List<MeetingPersonDTO> personDTOS = new ArrayList<>();
         personDTOS.add(personDTO1);
         personDTOS.add(personDTO2);
         personDTOS.add(personDTO3);

        meetingBaseService.addMeeting(meetingInfoDTO,agendaDTOS,personDTOS);

    }

    @Test
    public void testUpdateStatus() {
        MeetingInfoDTO meetingInfoDTO = MeetingInfoDTO.builder().meetingNo("2f7a286d-974d-4397-af7f-eea2f946df98").onOff(1).build();
        meetingBaseService.updateStatus(meetingInfoDTO);
    }

    @Test
    public  void testEditMeeting() {
        MeetingInfoDTO meetingInfoDTO = MeetingInfoDTO.builder()
                .meetingNo("2f7a286d-974d-4397-af7f-eea2f946df98")
                .topic("test")
                .location("location")
                .beginTime(new Date())
                .endTime(new Date())
                .createBy(0L)
                .updateBy(0L)
                .build();
        MeetingAgendaDTO meetingAgendaDTO1 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai11").createBy(0L).updateBy(0L).build();
        MeetingAgendaDTO meetingAgendaDTO2 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai21").createBy(0L).updateBy(0L).build();
        MeetingAgendaDTO meetingAgendaDTO3 =  MeetingAgendaDTO.builder().beginTime(new Date()).endTime(new Date()).detail("destai31").createBy(0L).updateBy(0L).build();
        List<MeetingAgendaDTO> agendaDTOS = new ArrayList<>();
        agendaDTOS.add(meetingAgendaDTO1);
        agendaDTOS.add(meetingAgendaDTO2);
        agendaDTOS.add(meetingAgendaDTO3);

        MeetingPersonDTO personDTO1 = MeetingPersonDTO.builder().name("one").equipment("nica").position("unkno").createBy(0L).updateBy(0L).build();
        MeetingPersonDTO personDTO2 = MeetingPersonDTO.builder().name("two").equipment("nica").position("unkno").createBy(0L).updateBy(0L).build();
        MeetingPersonDTO personDTO3 = MeetingPersonDTO.builder().name("three").equipment("nica").position("unkno").createBy(0L).updateBy(0L).build();
        List<MeetingPersonDTO> personDTOS = new ArrayList<>();
        personDTOS.add(personDTO1);
        personDTOS.add(personDTO2);
        personDTOS.add(personDTO3);

        meetingBaseService.updateMeeting(meetingInfoDTO,agendaDTOS,personDTOS);
    }

    @Test
    public void testRemoveMeeting() {

        MeetingInfoDTO meetingInfoDTO = MeetingInfoDTO.builder().meetingNo("835fb642-0501-493c-a7ad-2abac91d82f7").build();
        meetingBaseService.removeMeeting(meetingInfoDTO);

    }
}
