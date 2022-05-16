package beakjoon;

import java.io.*;

public class Prob_11052 {
    static int N;
    static int[] cards;
    static int[] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            cards = new int[N + 1];
            int idx = 1;
            for (String s : bf.readLine().split(" ")) {
                cards[idx++] = Integer.parseInt(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy = new int[N + 1];
        for (int i = 1; i <= N; i++) dy[i] = cards[i];
        for (int need = 2; need <= N; need++) {
            for (int i = need; i > need / 2; i--) {
                dy[need] = Math.max(dy[need], dy[i] + dy[need - i]);
            }
            if (need % 2 == 0) dy[need] = Math.max(dy[need], dy[need / 2] * 2);
        }
    }

    static void show_result() {
        System.out.println(dy[N]);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
