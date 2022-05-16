package beakjoon;

import java.io.*;

public class Prob_2156 {
    static int N, R;
    static int[] amount;
    static int[][] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            amount = new int[N + 1];
            for (int i = 1; i <= N; i++) amount[i] = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        //[i][0] = 이전꺼는 마시지 않고 이번껀 마심
        //[i][1] = 이전꺼도 마시고 이번꺼도 마심
        //[i][2] = 이전꺼도 마시지 않고 이번꺼도 마시지 않음
        dy = new int[N + 1][3];
        dy[1][0] = amount[1];
        dy[1][1] = amount[1];
        dy[1][2] = 0;
        if (N > 1) {
            dy[2][0] = amount[2];
            dy[2][1] = amount[1] + amount[2];
            dy[2][2] = 0;
            for (int i = 3; i <= N; i++) {
                dy[i][0] = Math.max(dy[i - 2][0], dy[i - 2][1]);
                dy[i][0] = Math.max(dy[i][0], dy[i - 2][2]) + amount[i];
                dy[i][1] = dy[i - 1][0] + amount[i];
                dy[i][2] = Math.max(dy[i - 2][0], dy[i - 1][1]);
            }
        }
    }

    static void show_result() {
        for (int i = 0; i <= 2; i++) {
            R = Math.max(R, dy[N - 1][i]);
            R = Math.max(R, dy[N][i]);
        }
        System.out.println(R);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
