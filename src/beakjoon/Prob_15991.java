package beakjoon;

import java.io.*;

public class Prob_15991 {
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

    static void solution() {
        dy = new long[Math.max(5, B + 1)];
        dy[0] = 1;
        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 2;
        dy[4] = 3;
        dy[5] = 3;
        for (int i = 6; i < dy.length; i++) {
            dy[i] = (dy[i - 2] + dy[i - 4] + dy[i - 6]) % 1000000009;
        }
    }

    static void show_result() {
        for (int i : cases) {
            System.out.println((i == 0) ? 0 : dy[i]);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
