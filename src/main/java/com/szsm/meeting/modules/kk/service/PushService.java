package com.szsm.meeting.modules.kk.service;

import com.szsm.meeting.base.context.ApiResult;
import com.szsm.meeting.base.context.ApiResult;

public interface PushService {
    /**
     * 推送给指定用户
     * @param userId
     * @param meetingNo
     */
    ApiResult pushMsgToOne(Integer userId, String meetingNo);

    /**
     * 推送给所有用户
     * @param meetingNo
     */
    ApiResult pushMsgToAll(String meetingNo);
}
