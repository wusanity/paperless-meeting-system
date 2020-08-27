package com.szsm.meeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:48
 */
public class LightOffCommond implements Commond {
    private Light light;

    public LightOffCommond(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.turnOff();
    }
}
