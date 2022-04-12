package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11728 {
    static int N, M, A[], B[];
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        //N,M <= 1,000,000
        A = new int[N];
        B = new int[M];
        //배열들의 모든 요소는 절대값이 1,000,000,000 보다 작거나 같은 정수
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
        for (int i = 0; i < M; i++) B[i] = scan.nextInt();
    }

    static void solution() {
        int i2 = 0;
        for (int i1 = 0; i1 < N; i1++) {
            while (i2 < M && A[i1] >= B[i2]) {
                sb.append(B[i2++] + " ");
            }
            sb.append(A[i1] + " ");
        }
        while (i2 < M) sb.append(B[i2++] + " ");
        System.out.println(sb);
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
