package com.szsm.videomeeting.base.config.netty.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeetingSchedule {


    /**
     * 每分钟执行一次
     */
    @Scheduled(cron = "0 */1 * * * ?")
    public void scheduleBaseMessage(){



    }


    /**
     * 每5分钟执行一次
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void scheduleFileMessage(){



    }

}
