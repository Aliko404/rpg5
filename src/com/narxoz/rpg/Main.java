package com.narxoz.rpg;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.decorator.BasicAttack;
import com.narxoz.rpg.decorator.CriticalFocusDecorator;
import com.narxoz.rpg.decorator.FireRuneDecorator;
import com.narxoz.rpg.decorator.PoisonCoatingDecorator;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.facade.AdventureResult;
import com.narxoz.rpg.facade.DungeonFacade;
import com.narxoz.rpg.hero.HeroProfile;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 5 Demo: Decorator + Facade ===\n");

        AttackAction basic = new BasicAttack("Strike", 10);
        AttackAction fireStrike = new FireRuneDecorator(basic);
        AttackAction poisonStrike = new PoisonCoatingDecorator(basic);
        AttackAction megaStrike = new FireRuneDecorator(
                new PoisonCoatingDecorator(
                        new CriticalFocusDecorator(
                                new BasicAttack("Strike", 10)
                        )
                )
        );

        System.out.println("--- Decorator Preview ---");
        printAction(basic);
        System.out.println();
        printAction(fireStrike);
        System.out.println();
        printAction(poisonStrike);
        System.out.println();
        printAction(megaStrike);

        System.out.println("\n--- Facade Preview ---");
        HeroProfile hero = new HeroProfile("Arthas", 95);
        BossEnemy boss = new BossEnemy("Inferno Dragon", 110, 16);

        DungeonFacade facade = new DungeonFacade().setRandomSeed(42L);
        AdventureResult result = facade.runAdventure(hero, boss, megaStrike);

        System.out.println("Winner: " + result.getWinner());
        System.out.println("Rounds: " + result.getRounds());
        System.out.println("Reward: " + result.getReward());
        for (String line : result.getLog()) {
            System.out.println(line);
        }

        System.out.println("\n=== Demo Complete ===");
    }

    private static void printAction(AttackAction action) {
        System.out.println("Action: " + action.getActionName());
        System.out.println("Damage: " + action.getDamage());
        System.out.println("Effects: " + action.getEffectSummary());
    }
}