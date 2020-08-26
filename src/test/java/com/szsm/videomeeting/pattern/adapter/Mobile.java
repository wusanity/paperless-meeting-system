package com.szsm.videomeeting.pattern.adapter;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 16:30
 */
public class Mobile {
    public void inputPower(V5Power v5Power){
        int provideV5Power = v5Power.provideV5Power();
        System.out.println("手机（客户端）：我需要5V电压充电，现在是-->" + provideV5Power + "V");
    }
}
