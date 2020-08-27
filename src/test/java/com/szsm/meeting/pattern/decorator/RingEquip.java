package com.szsm.meeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:38
 */
public class RingEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "魔龙戒";
    }
}
