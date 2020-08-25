package com.szsm.videomeeting.modules.meeting.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingDetailsDTO;
import com.szsm.videomeeting.modules.meeting.model.dto.MeetingInfoDTO;
import com.szsm.videomeeting.modules.meeting.model.entity.MeetingInfo;


public interface MeetingInfoService extends IService<MeetingInfo> {


    Page<MeetingInfo> getList(Page<MeetingInfo> page, MeetingInfoDTO meetingInfoDTO);

    MeetingDetailsDTO getMeetingDetails(String meetingNo);

    MeetingInfoDTO getByMeetingNo(String meetingNo);
}
