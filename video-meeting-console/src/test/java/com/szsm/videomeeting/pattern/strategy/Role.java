package com.szsm.videomeeting.pattern.strategy;

/**
 * @description:
 * @author: LiuJun
 * @date: 2020/8/25 16:01
 */
public abstract class Role {
    protected String name;
    protected IAttackBehavior attackBehavior;
    protected IDefendBehavior defendBehavior;

    public Role setAttackBehavior(IAttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
        return this;
    }

    public Role setDefendBehavior(IDefendBehavior defendBehavior) {
        this.defendBehavior = defendBehavior;
        return this;
    }

    protected void attack() {
        attackBehavior.attack();
    }

    protected void defend() {
        defendBehavior.defend();
    }
}
