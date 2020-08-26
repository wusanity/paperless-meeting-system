package com.szsm.videomeeting.pattern.decorator;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/26 10:43
 */
public class YellowGemDecorator implements IEquipDecorator {
    private IEquip equip;

    public YellowGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int calculateAttack() {
        return 9 + equip.calculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + "+黄宝石";
    }
}
