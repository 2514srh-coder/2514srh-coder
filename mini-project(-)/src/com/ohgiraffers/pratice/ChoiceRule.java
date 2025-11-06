package com.ohgiraffers.pratice;

import java.util.Arrays;

// 8. Choice (총합)
public class ChoiceRule extends ScoreRule {
    public ChoiceRule() {
        super("Choice");
    }

    @Override
    public int calculateScore(int[] dice) {
        // 스트림을 사용하여 모든 주사위 눈의 합을 반환
        return Arrays.stream(dice).sum();
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return true; // Choice는 언제나 선택 가능
    }
}