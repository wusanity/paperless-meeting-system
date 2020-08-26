package com.szsm.videomeeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:34
 */
public interface IEquip {
    /**
     * 计算攻击力
     * @return
     */
    int calculateAttack();

    /**
     * 装备描述
     * @return
     */
    String description();
}
