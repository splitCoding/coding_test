package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Prob_9095_After {
    static int T;
    static int[] nums, result;

    static void input() {
        FastReader scan = new FastReader();
        T = scan.nextInt();
        nums = new int[T];
        for (int i = 0; i < T; i++) nums[i] = scan.nextInt();
        result = new int[12];
    }

    static void solution() {
        result[1] = 1;
        result[2] = 2;
        result[3] = 4;
        for (int i = 4; i <= 11; i++) {
            result[i] = result[i - 1] + result[i - 2] + result[i - 3];
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        for (int i : nums) System.out.println(result[i]);
    }

    static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(bf.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

