package com.ohgiraffers.pratice;

import java.util.Map;

import static com.ohgiraffers.pratice.YachtUtils.countDice;

// 4. Full House (3개 + 2개)
public class FullHouseRule extends ScoreRule {
    public FullHouseRule() {
        super("Full House");
    }

    @Override
    public int calculateScore(int[] dice) {
        // countDice()를 사용하여 각 주사위 눈의 개수를 셉니다.
        Map<Integer, Integer> count = countDice(dice);

        // Full House는 오직 두 종류의 주사위 눈만 있어야 합니다.
        if (count.size() != 2) {
            return 0;
        }

        boolean hasThree = false, hasTwo = false;

        for (int c : count.values()) {
            if (c == 3) hasThree = true;
            if (c == 2) hasTwo = true;
        }

        // 3개짜리 조합과 2개짜리 조합이 모두 있어야 25점
        return (hasThree && hasTwo) ? 25 : 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}