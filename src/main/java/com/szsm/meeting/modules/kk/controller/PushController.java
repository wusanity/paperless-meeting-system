package com.szsm.meeting.modules.kk.controller;

import com.szsm.meeting.modules.kk.service.PushService;
import com.szsm.meeting.modules.kk.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class PushController {

    @Autowired
    private PushService pushService;

    /**
     * 推送给所有用户
     * @param meetingNo
     */
    @PostMapping("/pushAll")
    public void pushToAll(@RequestParam("meetingNo") String meetingNo){

        pushService.pushMsgToAll(meetingNo);
    }
    /**
     * 推送给指定用户
     * @param userId
     * @param meetingNo
     */
    @PostMapping("/pushOne")
    public void pushMsgToOne(@RequestParam("userId") Integer userId,@RequestParam("meetingNo") String meetingNo){
        pushService.pushMsgToOne(userId,meetingNo);
    }
}
