package com.szsm.meeting.pattern.observer;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 15:09
 */
public class Test {
    public static void main(String[] args) {
        ObjectFor3D subject = new ObjectFor3D();

        Observer observer1 = new Observer1(subject);
        Observer observer2 = new Observer2(subject);

        //消息更新
        subject.setMsg("马上要下班啦！");
    }
}
