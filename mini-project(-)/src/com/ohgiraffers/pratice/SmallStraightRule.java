package com.ohgiraffers.pratice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// 5. Small Straight (순서대로 4개)
public class SmallStraightRule extends ScoreRule {
    public SmallStraightRule() {
        super("Small Straight");
    }

    @Override
    public int calculateScore(int[] dice) {
        // 중복을 제거하고 주사위 눈을 저장
        Set<Integer> set = new HashSet<>();
        for (int d : dice) set.add(d);

        // 1-2-3-4, 2-3-4-5, 3-4-5-6 중 하나라도 포함하는지 확인
        if (set.containsAll(Arrays.asList(1,2,3,4)) ||
                set.containsAll(Arrays.asList(2,3,4,5)) ||
                set.containsAll(Arrays.asList(3,4,5,6))) {
            return 30;
        }
        return 0;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return calculateScore(dice) > 0;
    }
}