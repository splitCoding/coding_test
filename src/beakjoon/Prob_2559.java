package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2559 {
    static int N, K, A[], ret = Integer.MIN_VALUE;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); // 온도를 측정한 날짜의 수 , 2 <= N <= 100,000
        K = scan.nextInt(); // 합칠 날의 갯수, 1 <= K <= N
        A = new int[N]; // -100 <= A[i] <= 100
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
    }

    static void solution() {
        int tmp_sum = 0;
        if (N == K) {
            for (int i : A) tmp_sum += i;
            ret = tmp_sum;
        } else {
            for (int L = 0, R = 0; L <= N - K; L++) {
                while (R < L + K) tmp_sum += A[R++];
                ret = Math.max(ret, tmp_sum);
                tmp_sum -= A[L];
            }
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
