package com.szsm.meeting.pattern.observer;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 15:04
 */
public class Observer1 implements Observer {
    private Subject subject;

    public Observer1(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void udate(String msg) {
        System.out.println("observer1收到了消息内容为：" + msg);
    }
}
