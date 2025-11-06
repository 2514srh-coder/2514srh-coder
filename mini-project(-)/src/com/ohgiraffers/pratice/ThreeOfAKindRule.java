package com.ohgiraffers.pratice;
import java.util.Arrays;
import java.util.Map;

import static com.ohgiraffers.pratice.YachtUtils.countDice;

// 2. Three of a Kind
public class ThreeOfAKindRule extends ScoreRule {
    public ThreeOfAKindRule() {
        super("Three of a Kind");
    }

    @Override
    public int calculateScore(int[] dice) {
        Map<Integer, Integer> count = countDice(dice);
        for (int key : count.keySet()) {
            if (count.get(key) >= 3) {
                return Arrays.stream(dice).sum();
            }
        }
        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}