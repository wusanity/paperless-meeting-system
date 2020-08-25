package com.szsm.videomeeting.pattern.observer;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 14:50
 */
public interface Subject {
    /**
     * 注册观察者
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 移除观察者
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知
     */
    void notifyObserver();
}
