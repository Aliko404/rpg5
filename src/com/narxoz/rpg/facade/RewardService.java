package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "no loot";
        }

        if ("Hero".equals(battleResult.getWinner())) {
            if (battleResult.getRounds() <= 3) {
                return "legendary chest";
            }
            if (battleResult.getRounds() <= 6) {
                return "epic chest";
            }
            return "gold and potion";
        }

        if ("Boss".equals(battleResult.getWinner())) {
            return "respawn and sadness";
        }

        return "small consolation loot";
    }
}