package beakjoon;

import java.io.*;

public class Prob_15988 {
    static int T, B;
    static int[] cases;
    static long[] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            T = Integer.parseInt(bf.readLine());
            cases = new int[T];
            for (int i = 0; i < T; i++) {
                cases[i] = Integer.parseInt(bf.readLine());
                B = Math.max(B, cases[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void rec(int k) {
        if (k < 4) return;
        rec(k - 1);
        dy[k] = (dy[k - 1] + dy[k - 2] + dy[k - 3]) % 1000000009;
        System.out.println(k + " = " + dy[k]);
    }

    static void solution() {
        dy = new long[Math.max(4, B + 1)];
        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 4;
        if (B > 3) {
            for (int i = 4; i <= B; i++) {
                dy[i] = (dy[i - 1] + dy[i - 2] + dy[i - 3]) % 1000000009;
            }
        }
    }

    static void show_result() {
        for (int i : cases) System.out.println(dy[i]);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
