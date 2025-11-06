package com.ohgiraffers.pratice;

import java.util.*;


// 게임 메인 클래스
public class YachtDiceGame {
    private static List<ScoreRule> rules = new ArrayList<>();
    private static Map<String, Integer> scoreboard = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);
    private static String currentUsername = "";
    private static Map<String, String> userDatabase = new HashMap<>(); // username -> password
    private static Map<String, Integer> userHighScores = new HashMap<>(); // username -> best score

    public static void main(String[] args) {
        initRules();

        System.out.println("🎲 ═══════════════════════════════════════ 🎲");
        System.out.println("   요트다이스 게임에 오신 걸 환영합니다!");
        System.out.println("🎲 ═══════════════════════════════════════ 🎲\n");

        // 회원가입 또는 로그인
        authenticateUser();

        // 게임 시작 여부 확인
        if (!confirmGameStart()) {
            System.out.println("게임을 종료합니다. 다음에 뵙겠습니다! 👋\n");
            return;
        }

        // 게임 횟수 선택
        int gameCount = selectGameCount();

        // 게임 플레이
        for (int game = 1; game <= gameCount; game++) {
            playGame(game, gameCount);

            if (game < gameCount) {
                System.out.print("\n다음 게임을 시작하시겠습니까? (y/n): ");
                String response = sc.next().toLowerCase();
                if (!response.equals("y")) {
                    System.out.println("게임을 종료합니다.");
                    break;
                }
            }
        }

        showFinalStats();
        System.out.println("\n게임을 종료합니다. 즐거웠습니다! 👋\n");
    }

    private static void authenticateUser() {
        while (true) {
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인\n");
            System.out.print("선택하세요 (1 또는 2): ");
            String choice = sc.next();

            if (choice.equals("1")) {
                signup();
            } else if (choice.equals("2")) {
                if (login()) {
                    break;
                }
            } else {
                System.out.println("잘못된 입력입니다.\n");
            }
        }
    }

    private static void signup() {
        System.out.print("\n사용자명을 입력하세요: ");
        String username = sc.next();

        if (userDatabase.containsKey(username)) {
            System.out.println("❌ 이미 존재하는 사용자명입니다!\n");
            return;
        }

        System.out.print("비밀번호를 입력하세요: ");
        String password = sc.next();

        userDatabase.put(username, password);
        userHighScores.put(username, 0);
        System.out.println("✅ 회원가입이 완료되었습니다!\n");
    }

    private static boolean login() {
        System.out.print("\n사용자명을 입력하세요: ");
        String username = sc.next();

        if (!userDatabase.containsKey(username)) {
            System.out.println("❌ 존재하지 않는 사용자명입니다!\n");
            return false;
        }

        System.out.print("비밀번호를 입력하세요: ");
        String password = sc.next();

        if (!userDatabase.get(username).equals(password)) {
            System.out.println("❌ 비밀번호가 틀렸습니다!\n");
            return false;
        }

        currentUsername = username;
        System.out.println("✅ " + username + "님 로그인되었습니다!\n");
        return true;
    }

    private static boolean confirmGameStart() {
        System.out.print("게임을 시작하시겠습니까? (y/n): ");
        String response = sc.next().toLowerCase();
        return response.equals("y");
    }

    private static int selectGameCount() {
        while (true) {
            System.out.print("\n게임을 몇 번 플레이하시겠습니까? (1~10): ");
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해 주세요!");
                sc.next();
                continue;
            }
            int count = sc.nextInt();

            if (count < 1 || count > 10) {
                System.out.println("1~10 사이의 숫자를 입력해 주세요!");
                continue;
            }

            return count;
        }
    }

    private static void playGame(int currentGame, int totalGames) {
        System.out.println("\n\n╔════════════════════════════════════════╗");
        System.out.println("║          게임 " + currentGame + "/" + totalGames + " 시작");
        System.out.println("╚════════════════════════════════════════╝\n");

        resetScoreboard();

        for (int turn = 1; turn <= 12; turn++) {
            System.out.println("=== 턴 " + turn + " ===");
            int[] dice = rollDice();
            System.out.println("주사위: " + Arrays.toString(dice));

            showAvailableRules(dice);
            selectRule(dice);
            showScoreboard();
        }

        int total = scoreboard.values().stream()
                .filter(score -> score != -1)
                .mapToInt(Integer::intValue)
                .sum();

        System.out.println("\n🎉 게임 " + currentGame + " 종료! 획득 점수: " + total + "점 🎉");

        // 최고 점수 업데이트
        if (total > userHighScores.getOrDefault(currentUsername, 0)) {
            userHighScores.put(currentUsername, total);
            System.out.println("🏆 새로운 최고 점수입니다!");
        }
    }

    private static void resetScoreboard() {
        scoreboard.clear();
        for (ScoreRule rule : rules) {
            scoreboard.put(rule.getName(), -1);
        }
    }

    private static void initRules() {
        rules.add(new NumberRule("Ones", 1));
        rules.add(new NumberRule("Twos", 2));
        rules.add(new NumberRule("Threes", 3));
        rules.add(new NumberRule("Fours", 4));
        rules.add(new NumberRule("Fives", 5));
        rules.add(new NumberRule("Sixes", 6));
        rules.add(new ThreeOfAKindRule());
        rules.add(new FourOfAKindRule());
        rules.add(new FullHouseRule());
        rules.add(new SmallStraightRule());
        rules.add(new LargeStraightRule());
        rules.add(new YachtRule());
        rules.add(new ChoiceRule());

        resetScoreboard();
    }

    private static int[] rollDice() {
        Random r = new Random();
        int[] dice = new int[5];
        for (int i = 0; i < 5; i++) {
            dice[i] = r.nextInt(6) + 1;
        }
        Arrays.sort(dice);
        return dice;
    }

    private static void showAvailableRules(int[] dice) {
        System.out.println("\n선택 가능한 항목:");
        for (int i = 0; i < rules.size(); i++) {
            ScoreRule rule = rules.get(i);
            if (scoreboard.get(rule.getName()) == -1) {
                int score = rule.calculateScore(dice);
                String mark = rule.isApplicable(dice) ? "⭕" : "❌";
                System.out.printf("%d. %s: %d점 %s%n",
                        i+1, rule.getName(), score, mark);
            }
        }
    }

    private static void selectRule(int[] dice) {
        while (true) {
            System.out.print("\n선택할 번호를 입력하세요 (1~" + rules.size() + "): ");
            if (!sc.hasNextInt()) {
                System.out.println("숫자를 입력해 주세요!");
                sc.next();
                continue;
            }
            int choice = sc.nextInt();

            if (choice < 1 || choice > rules.size()) {
                System.out.println("잘못된 번호입니다!");
                continue;
            }

            ScoreRule selected = rules.get(choice - 1);
            if (scoreboard.get(selected.getName()) != -1) {
                System.out.println("이미 사용한 항목입니다!");
                continue;
            }

            int score = selected.calculateScore(dice);
            scoreboard.put(selected.getName(), score);
            System.out.println("✅ " + selected.getName() + " 선택! " + score + "점 획득!");
            break;
        }
    }

    private static void showScoreboard() {
        System.out.println("\n현재 점수판:");
        int total = 0;
        for (String name : scoreboard.keySet()) {
            int score = scoreboard.get(name);
            String status = score == -1 ? "미사용" : score + "점";
            System.out.printf("  %-18s : %s%n", name, status);
            if (score != -1) total += score;
        }
        System.out.println("  총점: " + total + "점");
        System.out.println("─".repeat(40));
    }

    private static void showFinalStats() {
        System.out.println("\n\n╔════════════════════════════════════════╗");
        System.out.println("║             🔳게임 종료🔳");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n📊 " + currentUsername + "님의 통계");
        System.out.println("   최고 점수: " + userHighScores.getOrDefault(currentUsername, 0) + "점");
    }
}