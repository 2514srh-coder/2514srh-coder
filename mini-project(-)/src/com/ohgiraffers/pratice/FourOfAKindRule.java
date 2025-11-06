package com.ohgiraffers.pratice;
import java.util.Arrays;
import java.util.Map;

import static com.ohgiraffers.pratice.YachtUtils.countDice;

// 3. Four of a Kind (같은 숫자 4개)
public class FourOfAKindRule extends ScoreRule {
    public FourOfAKindRule() {
        super("Four of a Kind");
    }

    @Override
    public int calculateScore(int[] dice) {
        // countDice()를 사용하여 각 주사위 눈의 개수를 셉니다.
        Map<Integer, Integer> count = countDice(dice);

        for (int key : count.keySet()) {
            if (count.get(key) >= 4) { // 4개 이상인지 확인
                return Arrays.stream(dice).sum(); // 주사위 총합 반환
            }
        }
        return 0; // 해당 규칙에 맞지 않으면 0점
    }

    @Override
    public boolean isApplicable(int[] dice) {
        // 점수가 0점보다 크면 (조건을 충족하면) 선택 가능
        return calculateScore(dice) > 0;
    }
}