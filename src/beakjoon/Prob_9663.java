package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob_9663 {
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
    static int[] queens;
    static int count;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        queens = new int[N];
    }

    static boolean attackable(int x1, int y1, int x2, int y2) {
        if (x1 == x2) return true;
        if (x1 - y1 == x2 - y2 || x1 + y1 == x2 + y2) return true;
        return false;
    }

    static void rec_func(int k) {
        if (k == N) {
            count++;
        } else {
            for (int i = 0; i < N; i++) {
                boolean able = true;
                for (int j = 0; j < k; j++) {
                    if (attackable(queens[j], j, i, k)) {
                        able = false;
                        break;
                    }
                }
                if (able) {
                    queens[k] = i;
                    rec_func(k + 1);
                }

            }
        }
    }

    public static void main(String[] args) {
        input();
        rec_func(0);
        System.out.println(count);
    }
}