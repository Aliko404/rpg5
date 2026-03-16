package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

import java.util.Random;

public class BattleService {
    private Random random = new Random(1L);

    public BattleService setRandomSeed(long seed) {
        this.random = new Random(seed);
        return this;
    }

    public AdventureResult battle(HeroProfile hero, BossEnemy boss, AttackAction action) {
        AdventureResult result = new AdventureResult();

        if (hero == null || boss == null || action == null) {
            result.setWinner("Nobody");
            result.setRounds(0);
            result.setReward("no loot");
            result.addLine("battle can't start, stuff is missing");
            return result;
        }

        int rounds = 0;
        int maxRounds = 10;

        result.addLine("battle starts");
        result.addLine(hero.getName() + " uses " + action.getActionName());
        result.addLine("effects: " + action.getEffectSummary());

        while (hero.isAlive() && boss.isAlive() && rounds < maxRounds) {
            rounds++;
            result.addLine("round " + rounds);

            int heroDamage = action.getDamage();
            boolean heroCrit = random.nextInt(100) < 20;
            if (heroCrit) {
                heroDamage += 5;
                result.addLine(hero.getName() + " lands a spicy crit");
            }

            boss.takeDamage(heroDamage);
            result.addLine(hero.getName() + " hits " + boss.getName() + " for " + heroDamage);
            result.addLine(boss.getName() + " hp: " + boss.getHealth());

            if (!boss.isAlive()) {
                break;
            }

            int bossDamage = boss.getAttackPower();
            boolean bossCrit = random.nextInt(100) < 15;
            if (bossCrit) {
                bossDamage += 4;
                result.addLine(boss.getName() + " goes wild with a heavy hit");
            }

            hero.takeDamage(bossDamage);
            result.addLine(boss.getName() + " hits " + hero.getName() + " for " + bossDamage);
            result.addLine(hero.getName() + " hp: " + hero.getHealth());
        }

        result.setRounds(rounds);

        if (hero.isAlive() && !boss.isAlive()) {
            result.setWinner("Hero");
        } else if (!hero.isAlive() && boss.isAlive()) {
            result.setWinner("Boss");
        } else {
            result.setWinner("Draw");
        }

        return result;
    }
}