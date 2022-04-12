package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_15565 {
    static int N, K, A[], ret = 1000001;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); // 1 <= K <= N <= Math.pow(10, 6);
        K = scan.nextInt();
        A = new int[N]; // A[i]는 1(라이언) or 2(어피치)
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
    }

    static void solution() {
        //라이언이 K개 이상있는 제일 작은 연속된 부분집합의 크기를 구하라
        int[] check = new int[3];
        for (int L = 0, R = 0; L < N; L++) {
            while (check[1] < K && R < N) check[A[R++]]++;
            if (check[1] == K) ret = Math.min(ret, R - L);
            check[A[L]]--;
        }
        System.out.println((ret == 1000001) ? -1 : ret);
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
