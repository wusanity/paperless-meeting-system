package com.szsm.videomeeting.modules.meeting.controller;


import com.szsm.videomeeting.modules.meeting.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meeting")
@Slf4j
public class MeetingController {

    @Autowired
    private MeetingService meetingService;



}
