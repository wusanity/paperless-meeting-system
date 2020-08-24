package com.szsm.videomeeting.modules.meeting.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.szsm.videomeeting.model.dto.MeetingPersonDTO;
import com.szsm.videomeeting.model.entity.MeetingPerson;

import java.util.List;

/**
 * @Author zhanglei
 * @Date Create at 2020/8/21 18:18
 * @Description:
 */
public interface MeetingPersonService extends IService<MeetingPerson> {

    /**
     * 通过会议号获取参会人信息
     * @param meetingNo
     * @return
     */
    List<MeetingPersonDTO> getByMeetingNo(String meetingNo);
}
