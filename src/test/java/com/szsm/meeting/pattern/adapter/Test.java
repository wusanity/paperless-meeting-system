package com.szsm.meeting.pattern.adapter;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 16:42
 */
public class Test {
    public static void main(String[] args) {
        Mobile mobile = new Mobile();

        //220v转化为5v
        V5Power v5Power = new V5PowerAdapter(new V220power());
        mobile.inputPower(v5Power);
    }
}
