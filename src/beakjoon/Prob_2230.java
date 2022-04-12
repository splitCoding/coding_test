package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2230 {
    static int N, M, A[], ret = Integer.MAX_VALUE;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
        //1 ≤ N ≤ 100,000
        //0 ≤ M ≤ 2,000,000,000
        //0 ≤ |A[i]| ≤ 1,000,000,000
    }

    static void solution() {
        Arrays.sort(A);
        for (int L = 0, R = 1; L < N - 1; L++) {
            while (R < N && A[R] - A[L] < M) R++;
            if (R < N) ret = Math.min(ret, A[R] - A[L]);
        }
        System.out.println(ret);
    }

    public static void main(String[] args) {
        input();
        solution();
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
}
