package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_7795 {
    static int T, A[][], B[][];
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        T = scan.nextInt();
        A = new int[T][];
        B = new int[T][];
        for (int i = 0; i < T; i++) {
            A[i] = new int[scan.nextInt()];
            B[i] = new int[scan.nextInt()];
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = scan.nextInt();
            }
            for (int j = 0; j < B[i].length; j++) {
                B[i][j] = scan.nextInt();
            }
        }
    }

    static void solution() {
        for (int[] arr : B) Arrays.sort(arr);
        for (int idx = 0; idx < T; idx++) {
            int count = 0;
            for (int i = 0; i < A[idx].length; i++) {
                if (A[idx][i] < B[idx][0]) {
                    continue;
                }
                if (A[idx][i] > B[idx][B[idx].length - 1]) {
                    count += B[idx].length;
                    continue;
                }
                for (int j = 0; j < B[idx].length; j++) {
                    if (A[idx][i] > B[idx][j]) count++;
                    else break;
                }
            }
            sb.append(count).append("\n");
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

        FastReader(String s) {
            try {
                bf = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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
