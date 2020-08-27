package com.szsm.meeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:37
 */
public class ArmEquip implements IEquip {
    @Override
    public int calculateAttack() {
        return 20;
    }

    @Override
    public String description() {
        return "屠龙刀";
    }
}
