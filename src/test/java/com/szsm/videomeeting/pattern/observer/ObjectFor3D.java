package com.szsm.videomeeting.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 14:55
 */
public class ObjectFor3D implements Subject{
    private List<Observer> observerList = new ArrayList();
    /**
     * 消息
     */
    private String msg;

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observerList.indexOf(observer);
        if (index >= 0) {
            observerList.remove(index);
        }
    }

    @Override
    public void notifyObserver() {
       for(Observer observer:observerList) {
           observer.udate(msg);
       }
    }

    public void setMsg(String msg) {
        this.msg = msg;
        notifyObserver();
    }
}
