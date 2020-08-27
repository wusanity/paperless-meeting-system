package com.szsm.meeting.pattern.strategy;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 15:55
 */
public class AttackJY implements IAttackBehavior{
    @Override
    public void attack() {
        System.out.println("看我九阳神功！");
    }
}
