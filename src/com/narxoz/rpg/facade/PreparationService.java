package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class PreparationService {
    public String prepare(HeroProfile hero, BossEnemy boss, AttackAction action) {
        if (hero == null ||  boss == null || action ==null) {
            return "prep failed:something is missing";
        }

        if (!hero.isAlive()) {
            return "prep failed:hero is already down";
        }

        if (!boss.isAlive()) {
            return "prep failed:boss is already done";
        }

        return "prep done: " + hero.getName()
                + " enters the dungeon with "
                + action.getActionName()
                + " [" + action.getEffectSummary() +"] against"
                + boss.getName();
    }}