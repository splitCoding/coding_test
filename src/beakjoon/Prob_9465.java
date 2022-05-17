package beakjoon;

import java.io.*;

public class Prob_9465 {
    static int N;
    static int[][] score, dy;
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static void input() {
        try {
            N = Integer.parseInt(bf.readLine());
            score = new int[3][N + 1];
            dy = new int[3][N + 1];
            for (int i = 1; i <= 2; i++) {
                int idx = 1;
                for (String s : bf.readLine().split(" ")) {
                    score[i][idx++] = Integer.parseInt(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy[1][1] = score[1][1];
        dy[2][1] = score[2][1];
        for (int i = 2; i <= N; i++) {
            dy[1][i] = Math.max(dy[2][i-1], dy[2][i-2]) + score[1][i];
            dy[2][i] = Math.max(dy[1][i-1], dy[1][i-2]) + score[2][i];
        }
        sb.append(Math.max(dy[1][N], dy[2][N])).append('\n');
    }

    static void show_result() {
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bf.readLine());
        for(int i=0;i<T;i++){
            input();
            solution();
        }
        show_result();
    }
}
