package com.szsm.videomeeting.modules.kk;

import java.io.Serializable;

public class Aa{

    public static void main(String[] args) {
        String msg = getOne("nihao:{},{}","a","b");
        System.out.println(msg);
    }

    public static String getOne(String msg,String... code){
        for (String s : code) {
            msg = msg.replaceFirst("\\{\\}", s);
            System.out.println(s);
        }
        return msg;
    }
}
