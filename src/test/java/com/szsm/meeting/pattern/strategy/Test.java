package com.szsm.meeting.pattern.strategy;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 16:10
 */
public class Test {
    public static void main(String[] args) {
        Role roleA = new RoleA("roleA");

        roleA.setAttackBehavior(new AttackJY()).setDefendBehavior(new DefendTBS());
        System.out.println(roleA.name + ":");

        roleA.attack();
        roleA.defend();
    }
}
