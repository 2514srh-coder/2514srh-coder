package com.ohgiraffers.pratice;

import java.util.Map;

import static com.ohgiraffers.pratice.YachtUtils.countDice;

// 7. Yacht (같은 숫자 5개)
public class YachtRule extends ScoreRule {
    public YachtRule() {
        super("Yacht");
    }

    @Override
    public int calculateScore(int[] dice) {
        // countDice()를 사용하여 각 주사위 눈의 개수를 셉니다.
        Map<Integer, Integer> count = countDice(dice);

        // Yacht는 오직 한 종류의 주사위 눈만 있어야 합니다.
        if (count.size() != 1) {
            return 0;
        }

        // count.values().iterator().next()는 맵에 남아있는 유일한 값 (개수)을 반환합니다.
        // 또는, 첫 번째 주사위 눈이 5개인지 확인합니다.
        if (count.get(dice[0]) == 5) {
            return 50; // Yacht는 50점 고정
        }

        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}