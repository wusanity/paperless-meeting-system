package com.szsm.videomeeting.pattern.command;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 17:28
 */
public class CommondController {
    private static final int COMMOND_SIZE = 8;
    private Commond[] commonds;

    public CommondController() {
        commonds = new Commond[COMMOND_SIZE];
        //默认初始化空命令
        for (int i = 0; i < commonds.length; i++) {
            commonds[i] = new NoCommond();
        }
    }

    /**
     * 设置按键命令
     * @param index
     * @param commond
     */
    public void setCommond(int index, Commond commond) {
        commonds[index] = commond;
    }

    /**
     * 按下按键
     * @param index
     */
     public void pressKey(int index) {
        commonds[index].excute();
     }
}
