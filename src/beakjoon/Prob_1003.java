package beakjoon;

import java.io.*;

public class Prob_1003 {
    static int T, B;
    static int[] cases, dy0, dy1;

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

    static void find(int k) {
        if (k <= 1) return;
        find(k - 1);
        dy1[k] += dy1[k - 1] + dy1[k - 2];
        dy0[k] += dy0[k - 1] + dy0[k - 2];
    }

    static void solution() {
        dy0 = new int[B + 1];
        dy1 = new int[B + 1];
        if (B == 0) {
            System.out.println("1 0");
        } else if (B == 1) {
            System.out.println("0 1");
        } else {
            dy0[0] = 1;
            dy1[1] = 1;
            find(B);
        }
        for (int i : cases) System.out.println(dy0[i] + " " + dy1[i]);
    }

    public static void main(String[] args) {
        input();
        solution();
    }
}
