package com.szsm.videomeeting.pattern.observer;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 14:53
 */
public interface Observer {
    /**
     * 接受消息
     * @param msg 消息
     */
    void udate(String msg);
}
