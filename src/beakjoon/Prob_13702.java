package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_13702 {
    static int N, K, pots[], max = 0;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        K = scan.nextInt();
        pots = new int[N];
        for (int i = 0; i < N; i++) {
            pots[i] = scan.nextInt();
            max = Math.max(max, pots[i]);
        }
    }

    static boolean is_possible(long divide) {
        long count = 0;
        for (int i = 0; i < N; i++) {
            count += pots[i] / divide;
            if (count >= K) return true;
        }
        return false;
    }

    static void solution() {
        long L = 1, R = max, ret = 0;
        while (L <= R) {
            long mid = (L + R) / 2;
            if (is_possible(mid)) {
                ret = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
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
    }
}
