package beakjoon;

import java.io.*;

public class Prob_5557 {
    static int N;
    static int[] nums;
    static long[][] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
            nums = new int[N + 1];
            int idx = 1;
            for (String s : bf.readLine().split(" ")) nums[idx++] = Integer.parseInt(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy = new long[N + 1][21];
        dy[1][nums[1]] = 1;
        for (int i = 2; i < N; i++) {
            for (int j = 0; j <= 20; j++) {
                if (dy[i - 1][j] == 0) continue;
                if (j + nums[i] <= 20) dy[i][j + nums[i]] += dy[i - 1][j];
                if (j - nums[i] >= 0) dy[i][j - nums[i]] += dy[i - 1][j];
            }
        }
    }

    static void show_result() {
        System.out.println(dy[N - 1][nums[N]]);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
