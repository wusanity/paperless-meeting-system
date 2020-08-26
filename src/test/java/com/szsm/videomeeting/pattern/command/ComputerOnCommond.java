package com.szsm.videomeeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:20
 */
public class ComputerOnCommond implements Commond {
    private Computer computer;

    public ComputerOnCommond(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void excute() {
        computer.turnOn();
    }
}
