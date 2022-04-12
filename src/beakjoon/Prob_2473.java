package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2473 {
    static int N;
    static long r1, r2, r3, A[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); // 3 <= N <= 5000
        A = new long[N]; // -10억 <= A[i] <= 10억
        for (int i = 0; i < N; i++) A[i] = scan.nextLong();
    }

    static void solution() {
        Arrays.sort(A);
        long sum = (long) Math.pow(10, 9) * 3;
        boolean find = false;
        All:
        for (int L = 0; L < N - 2; L++) {
            int M = L + 1, R = N - 1;
            while (M < R) {
                long tmp_sum = A[L] + A[M] + A[R];
                if (tmp_sum > 0) {
                    if (Math.abs(tmp_sum) < sum) {
                        sum = Math.abs(tmp_sum);
                        r1 = A[L];
                        r2 = A[M];
                        r3 = A[R];
                        find = true;
                    }
                    R--;
                } else if (Math.abs(tmp_sum) == 0) {
                    r1 = A[L];
                    r2 = A[M];
                    r3 = A[R];
                    find = true;
                    break All;
                } else {
                    if (Math.abs(tmp_sum) < sum) {
                        sum = Math.abs(tmp_sum);
                        r1 = A[L];
                        r2 = A[M];
                        r3 = A[R];
                        find = true;
                    }
                    M++;
                }
            }
        }
        if (find) {
            System.out.printf("%d %d %d", r1, r2, r3);
        } else {
            System.out.printf("%d %d %d", A[0], A[1], A[2]);
        }
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

        Long nextLong() {
            return Long.parseLong(next());
        }

    }
}
