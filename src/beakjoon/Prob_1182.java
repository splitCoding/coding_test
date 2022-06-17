package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob_1182 {
    private static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(bf.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static int N, S, count, numbers[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = scan.nextInt();
    }

    static void rec_func(int k, int result) {
        if (k == N) {
            if (result == S) count++;
        } else {
            rec_func(k + 1, result + numbers[k]);
            rec_func(k + 1, result);
        }
    }

    public static void main(String[] args) {
        input();
        if (S == 0) count = -1;
        rec_func(0, 0);
        System.out.println(count);
    }
}
