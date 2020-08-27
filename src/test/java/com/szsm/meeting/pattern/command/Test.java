package com.szsm.meeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:36
 */
public class Test {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();
        Computer computer = new Computer();

        CommondController commondController = new CommondController();
        commondController.setCommond(0, new ComputerOnCommond(computer));
        commondController.setCommond(1, new ComputerOffCommond(computer));
        commondController.setCommond(2, new DoorOnCommond(door));
        commondController.setCommond(3, new DoorOffCommond(door));
        commondController.setCommond(4, new LightOnCommond(light));
        commondController.setCommond(5, new LightOffCommond(light));

        commondController.pressKey(0);
        commondController.pressKey(1);
        commondController.pressKey(2);
        commondController.pressKey(3);
        commondController.pressKey(4);
        commondController.pressKey(5);
        commondController.pressKey(6);
    }
}
