package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1300 {
    static int N, K;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        K = scan.nextInt();
    }

    static int is_possible(long num) {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, num / i);
        }
        return count;
    }

    static void solution() {
        long L = 1, R = Math.min((int) Math.pow(10, 9), (100000 * 100000)), ret = 0;
        while (L <= R) {
            long mid = (L + R) / 2;
            int idx = is_possible(mid);
            if (idx >= K) {
                ret = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        System.out.println(ret);
    }

    public static void main(String[] args) {
        input();
        solution();
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
