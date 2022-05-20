package beakjoon;

import java.io.*;

public class Prob_1495 {
    static int N, S, M;
    static int[] volume;
    static int[][] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            String tmp = bf.readLine();
            N = Integer.parseInt(tmp.split(" ")[0]); //곡의 갯수
            S = Integer.parseInt(tmp.split(" ")[1]); //시작볼륨
            M = Integer.parseInt(tmp.split(" ")[2]); //볼륨의 최대치
            volume = new int[N + 1];
            int idx = 1;
            for (String s : bf.readLine().split(" ")) volume[idx++] = Integer.parseInt(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy = new int[N + 1][M + 1];
        dy[0][S] = 1;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                if (dy[i - 1][j] == 0) continue;
                if (j + volume[i] <= M) dy[i][j + volume[i]] += dy[i - 1][j];
                if (j - volume[i] > -1) dy[i][j - volume[i]] += dy[i - 1][j];
            }
        }
    }

    static void show_result() {
        int max = -1;
        for (int i = 0; i <= M; i++) {
            if (dy[N][i] == 0) continue;
            max = Math.max(max, i);
        }
        System.out.println((max == -1) ? -1 : max);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
