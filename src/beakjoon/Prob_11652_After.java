package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob_11652_After {
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

    static int N;
    static long ret, numbers[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        numbers = new long[N];
        for (int i = 0; i < N; i++) numbers[i] = scan.nextLong();
    }

    static void solve() {
        Arrays.sort(numbers);
        ret = numbers[0];
        int most_same = 1, now_same = 1;
        for (int i = 1; i < N; i++) {
            if (numbers[i] == numbers[i - 1]) {
                now_same++;
                if (most_same < now_same) {
                    most_same = now_same;
                    ret = numbers[i];
                }
            } else {
                now_same = 1;
            }
        }
    }

    public static void main(String[] args) {
        input();
        solve();
        System.out.println(ret);
    }
}
