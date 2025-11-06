package com.ohgiraffers.pratice;

// 추상 클래스: 모든 점수 규칙의 부모
public abstract class ScoreRule {
    protected String name;

    public ScoreRule(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 다형성 핵심! 각 규칙마다 calculateScore를 다르게 구현
    public abstract int calculateScore(int[] dice);

    // 선택 가능한지 확인 (예: 풀하우스, 스트레이트 등)
    public abstract boolean isApplicable(int[] dice);
}