package com.szsm.meeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:48
 */
public class Test {
    public static void main(String[] args) {
        ArmEquip armEquip = new ArmEquip();
        YellowGemDecorator yellowGemDecorator = new YellowGemDecorator(armEquip);
        BlueGemDecorator blueGemDecorator = new BlueGemDecorator(yellowGemDecorator);
        System.out.println(blueGemDecorator.calculateAttack());
        System.out.println(blueGemDecorator.description());
    }
}
