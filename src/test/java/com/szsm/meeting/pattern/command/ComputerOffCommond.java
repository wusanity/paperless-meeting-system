package com.szsm.meeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:23
 */
public class ComputerOffCommond implements Commond {
    private Computer computer;

    public ComputerOffCommond(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void excute() {
        computer.turnOff();
    }
}
