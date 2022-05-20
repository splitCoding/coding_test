package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_9095_Again {
    static int T, B = 0;
    static int[] nums, dy;

    static void input() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        T = sc.nextInt();
        nums = new int[T];
        for (int i = 0; i < T; i++) {
            nums[i] = sc.nextInt();
            B = Math.max(B, nums[i]);
        }
    }

    static void solution() {
        dy = new int[Math.max(4, B + 1)];
        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 4;
        for (int i = 4; i <= B; i++) {
            dy[i] = dy[i - 3] + dy[i - 2] + dy[i - 1];
        }
    }

    static void show_result() {
        for (int i : nums) System.out.println(dy[i]);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}

