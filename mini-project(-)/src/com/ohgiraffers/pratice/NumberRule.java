package com.ohgiraffers.pratice;

// 1. Ones ~ Sixes (같은 숫자 합계)
public class NumberRule extends ScoreRule {
    private int number;

    public NumberRule(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    public int calculateScore(int[] dice) {
        int sum = 0;
        for (int d : dice) {
            if (d == number) sum += number;
        }
        return sum;
    }

    @Override
    public boolean isApplicable(int[] dice) {
        return true; // 언제나 선택 가능
    }
}