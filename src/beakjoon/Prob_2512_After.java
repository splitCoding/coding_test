package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob_2512_After {
    static int N, provinces[], M, max = 0;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        provinces = new int[N];
        for (int i = 0; i < N; i++) {
            provinces[i] = scan.nextInt();
            max = Math.max(max, provinces[i]);
        }
        M = scan.nextInt();
    }

    static boolean is_possible(long k) {
        long total = 0;
        for (int i : provinces) total += Math.min(i, k);
        return total <= M;
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
        sb.append(ret);
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
