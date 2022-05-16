package beakjoon;

import java.io.*;

public class Prob_1149 {
    static int N;
    static int[][] cost;
    static int[][] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            cost = new int[N + 1][3];
            for (int i = 1; i <= N; i++) {
                int idx = 0;
                for (String s : bf.readLine().split(" ")) {
                    cost[i][idx++] = Integer.parseInt(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy = new int[N + 1][3];
        dy[1][0] = cost[1][0];
        dy[1][1] = cost[1][1];
        dy[1][2] = cost[1][2];
        for (int i = 2; i <= N; i++) {
            dy[i][0] = Math.min(dy[i - 1][1], dy[i - 1][2]) + cost[i][0];
            dy[i][1] = Math.min(dy[i - 1][0], dy[i - 1][2]) + cost[i][1];
            dy[i][2] = Math.min(dy[i - 1][0], dy[i - 1][1]) + cost[i][2];
        }
    }

    static void show_result() {
        int min = Integer.MAX_VALUE;
        for (int i : dy[N]) min = Math.min(min, i);
        System.out.println(min);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
