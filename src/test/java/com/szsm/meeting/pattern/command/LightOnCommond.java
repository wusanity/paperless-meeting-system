package com.szsm.meeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:45
 */
public class LightOnCommond implements Commond {
    private Light light;

    public LightOnCommond(Light light) {
        this.light = light;
    }

    @Override
    public void excute() {
        light.turnOn();
    }
}
