package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2688 {
    static int T, B;
    static int[] nums;
    static long[] sum;
    static long[][] dy;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            T = Integer.parseInt(bf.readLine());
            B = 0;
            nums = new int[T];
            for (int i = 0; i < T; i++) {
                nums[i] = Integer.parseInt(bf.readLine());
                B = Math.max(B, nums[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        dy = new long[B + 1][10];
        sum = new long[B + 1];
        if(B > 0){
            for (int i = 0; i < 10; i++) {
                dy[1][i] = 1;
                sum[1] += dy[1][i];
            }
            for (int i = 2; i <= B; i++) {
                dy[i][0] = sum[i - 1];
                sum[i] += dy[i][0];
                for (int j = 1; j < 10; j++) {
                    dy[i][j] = dy[i][j - 1] - dy[i - 1][j - 1];
                    sum[i] += dy[i][j];
                }
            }
        }
    }

    static void show_result() {
        for (int i : nums) sb.append(sum[i]).append('\n');
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        show_result();
    }
}
