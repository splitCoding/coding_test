package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob_2096 {
    static int N;
    static int[][] stage, max_dy, min_dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            stage = new int[N + 1][3];
            for (int i = 1; i <= N; i++) {
                int idx = 0;
                for (String s : bf.readLine().split(" ")) {
                    stage[i][idx++] = Integer.parseInt(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        max_dy = new int[N + 1][3];
        min_dy = new int[N + 1][3];
        for (int i = 0; i < 3; i++) {
            max_dy[1][i] = stage[1][i];
            min_dy[1][i] = stage[1][i];
        }
        for (int i = 2; i <= N; i++) {
            max_dy[i][0] = Math.max(max_dy[i - 1][0], max_dy[i - 1][1]) + stage[i][0];
            max_dy[i][1] = Math.max(max_dy[i - 1][0], max_dy[i - 1][1]);
            max_dy[i][1] = Math.max(max_dy[i][1], max_dy[i - 1][2]) + stage[i][1];
            max_dy[i][2] = Math.max(max_dy[i - 1][1], max_dy[i - 1][2]) + stage[i][2];
        }
        for (int i = 2; i <= N; i++) {
            min_dy[i][0] = Math.min(min_dy[i - 1][0], min_dy[i - 1][1]) + stage[i][0];
            min_dy[i][1] = Math.min(min_dy[i - 1][0], min_dy[i - 1][1]);
            min_dy[i][1] = Math.min(min_dy[i][1], min_dy[i - 1][2]) + stage[i][1];
            min_dy[i][2] = Math.min(min_dy[i - 1][1], min_dy[i - 1][2]) + stage[i][2];
        }
    }

    static void show_result() {
        int min = Integer.MAX_VALUE, max = -1;
        for (int i : min_dy[N]) min = Math.min(min, i);
        for (int i : max_dy[N]) max = Math.max(max, i);
        System.out.println(max + " " + min);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
