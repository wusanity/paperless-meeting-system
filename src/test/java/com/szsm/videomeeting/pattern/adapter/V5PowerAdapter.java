package com.szsm.videomeeting.pattern.adapter;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 16:36
 */
public class V5PowerAdapter implements V5Power{
    private V220power v220power;

    public V5PowerAdapter(V220power v220power) {
        this.v220power = v220power;
    }

    @Override
    public int provideV5Power() {
        int power = v220power.provideV220Power();
        System.out.println("已将220v交流电转换为5v直流电！");
        return 5;
    }
}
