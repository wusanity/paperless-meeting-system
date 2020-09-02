package com.szsm.meeting.modules.meeting.service;


import com.szsm.meeting.BaseTest;
import com.szsm.meeting.modules.meeting.model.dto.MeetingAgendaDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.meeting.modules.meeting.model.dto.MeetingPersonDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description: MeetingBaseService单元测试类
 * @author: LiuJun
 * @date: 2020/9/2 15:47
 */
public class MeetingBaseServiceTest extends BaseTest {
    @Autowired
    private MeetingBaseService meetingBaseService;

    @Test
    @Rollback
    public void testAddMeeting() {
        MeetingInfoDTO meetingInfoDTO = MeetingInfoDTO
                .builder()
                .topic("会议主题5")
                .location("武汉")
                .createBy(0L)
                .updateBy(0L)
                .beginTime(new Date())
                .endTime(new Date())
                .build();

        List<MeetingAgendaDTO> agendaDTOList = new ArrayList<>();
        MeetingAgendaDTO meetingAgendaDTO = MeetingAgendaDTO
                .builder()
                .detail("会议议程细节5")
                .url("www.baidu.com")
                .createBy(0L)
                .updateBy(0L)
                .build();
        MeetingAgendaDTO meetingAgendaDTO1 = MeetingAgendaDTO
                .builder()
                .detail("会议议程细节6")
                .url("www.baidu.com1")
                .createBy(0L)
                .updateBy(0L)
                .build();
        agendaDTOList.add(meetingAgendaDTO);
        agendaDTOList.add(meetingAgendaDTO1);

        List<MeetingPersonDTO> personDTOList = new ArrayList<>();
        MeetingPersonDTO meetingPersonDTO = MeetingPersonDTO
                .builder()
                .equipment("设备5")
                .name("姓名1")
                .position("位置1")
                .createBy(0L)
                .updateBy(0L)
                .build();
        MeetingPersonDTO meetingPersonDTO1 = MeetingPersonDTO
                .builder()
                .equipment("设备6")
                .name("姓名2")
                .position("位置2")
                .createBy(0L)
                .updateBy(0L)
                .build();
        personDTOList.add(meetingPersonDTO);
        personDTOList.add(meetingPersonDTO1);

        meetingBaseService.addMeeting(meetingInfoDTO,agendaDTOList,personDTOList);
    }
}
