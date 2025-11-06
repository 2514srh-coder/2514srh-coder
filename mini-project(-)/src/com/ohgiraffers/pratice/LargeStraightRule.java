package com.ohgiraffers.pratice;

import java.util.Arrays;

// 6. Large Straight (순서대로 5개)
public class LargeStraightRule extends ScoreRule {
    public LargeStraightRule() {
        super("Large Straight");
    }

    @Override
    public int calculateScore(int[] dice) {
        // 정렬: 연속된 숫자인지 확인하기 위해 필요
        int[] sortedDice = Arrays.copyOf(dice, dice.length);
        Arrays.sort(sortedDice);

        // 1-2-3-4-5 또는 2-3-4-5-6인지 확인
        // Large Straight는 중복된 숫자가 있으면 안 되기 때문에, 정렬 후 이전 숫자와 정확히 1 차이 나야 함
        boolean isSeq = true;
        for (int i = 1; i < sortedDice.length; i++) {
            if (sortedDice[i] != sortedDice[i-1] + 1) {
                isSeq = false;
                break;
            }
        }

        // isSeq가 true라면 40점, 아니면 0점
        return isSeq ? 40 : 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}