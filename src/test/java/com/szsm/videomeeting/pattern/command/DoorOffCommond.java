package com.szsm.videomeeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:43
 */
public class DoorOffCommond implements Commond {
    private Door door;

    public DoorOffCommond(Door door) {
        this.door = door;
    }

    @Override
    public void excute() {
        door.close();
    }
}
