package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_15990 {
    static int T, B = 0, divide = 1000000009;
    static int[] nums;
    static int[][] dy;

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
        dy = new int[Math.max(4, B + 1)][4];// [ 만들어야하는 수 ] [ 끝나는 숫자 ]
        dy[1][1] = 1;
        dy[2][2] = 1;
        dy[3][1] = 1;
        dy[3][2] = 1;
        dy[3][3] = 1;
        for (int i = 4; i <= B; i++) {
            dy[i][1] = (dy[i - 1][2] + dy[i - 1][3]) % divide;
            dy[i][2] = (dy[i - 2][1] + dy[i - 2][3]) % divide;
            dy[i][3] = (dy[i - 3][2] + dy[i - 3][1]) % divide;
        }
    }

    static void show_result() {
        for (int i : nums) {
            int sum = 0;
            for (int j = 1; j < 4; j++) {
                sum += dy[i][j];
                sum %= divide;
            }
            System.out.println(sum);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}

