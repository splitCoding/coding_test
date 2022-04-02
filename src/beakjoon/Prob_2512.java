package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2512 {
    static int N, provinces[], M;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        provinces = new int[N];
        for (int i = 0; i < N; i++) provinces[i] = scan.nextInt();
        M = scan.nextInt();
    }

    static boolean is_possible(long k) {
        long total = 0;
        for (int i : provinces) {
            if (i <= k) {
                total += i;
            } else {
                total += k;
            }
            if (total > M) return false;
        }
        return true;
    }

    static void solution() {
        long total = 0;
        int max = 0;
        for (int i : provinces) {
            total += i;
            max = Math.max(max, i);
        }
        if (total <= M) {
            sb.append(max);
        } else {
            long L = 1, R = 1000000000, ret = 0;
            while (L <= R) {
                long mid = (L + R) / 2;
                if (is_possible(mid)) {
                    ret = mid;
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            sb.append(ret);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(sb.toString());
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
