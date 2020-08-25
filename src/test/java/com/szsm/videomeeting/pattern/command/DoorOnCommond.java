package com.szsm.videomeeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:40
 */
public class DoorOnCommond implements Commond {
    private Door door;

    public DoorOnCommond (Door door) {
        this.door = door;
    }

    @Override
    public void excute() {
        door.close();
    }
}
