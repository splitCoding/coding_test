package beakjoon;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Prob_1920_After {
    static int N, M;
    static int[] arr1, arr2;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        arr1 = new int[N];
        for (int i = 0; i < N; i++) arr1[i] = scan.nextInt();
        M = scan.nextInt();
        arr2 = new int[M];
        for (int i = 0; i < M; i++) arr2[i] = scan.nextInt();
    }

    static void solution() {
        Arrays.sort(arr1);
        for (int i : arr2) {
            boolean find = false;
            int L = 0, R = N - 1;
            while (L <= R) {
                int mid = (L + R) / 2;
                if (arr1[mid] <= i) {
                    if (arr1[mid] == i) {
                        find = true;
                        break;
                    }
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
            if (find) {
                sb.append(1).append('\n');
            } else {
                sb.append(0).append('\n');
            }
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
