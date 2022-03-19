package beakjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob_7795_After {
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

    static int lower_bound(int[] arr, int k) {
        int L = 0, R = arr.length - 1;
        int ret = 0;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] < k) {
                ret = mid + 1;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return ret;
    }

    static void solution() {
        for (int[] arr : B) Arrays.sort(arr);
        for (int idx = 0; idx < T; idx++) {
            int count = 0;
            for (int a : A[idx]) count += lower_bound(B[idx], a);
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
