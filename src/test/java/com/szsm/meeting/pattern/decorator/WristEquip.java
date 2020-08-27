package com.szsm.meeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:39
 */
public class WristEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战护腕";
    }
}
