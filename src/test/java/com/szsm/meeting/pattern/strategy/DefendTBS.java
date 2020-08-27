package com.szsm.meeting.pattern.strategy;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 15:56
 */
public class DefendTBS implements IDefendBehavior {
    @Override
    public void defend() {
        System.out.println("看我铁布衫！");
    }
}
