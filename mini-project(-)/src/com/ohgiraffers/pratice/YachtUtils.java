package com.ohgiraffers.pratice;

import java.util.Map;
import java.util.HashMap;

// 주사위 관련 유틸리티 함수 모음
public class YachtUtils {
    public static Map<Integer, Integer> countDice(int[] dice) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int d : dice) {
            map.put(d, map.getOrDefault(d, 0) + 1);
        }
        return map;
    }
}