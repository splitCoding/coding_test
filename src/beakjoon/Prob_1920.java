package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1920 {
    static int N, M;
    static int[] arr2;
    static HashSet<Integer> check;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        check = new HashSet<>(N);
        for (int i = 0; i < N; i++) check.add(scan.nextInt());
        M = scan.nextInt();
        arr2 = new int[M];
        for (int i = 0; i < M; i++) arr2[i] = scan.nextInt();
    }

    static void solution() {
        int ret = 0;
        for (int i : arr2) {
            ret = (check.contains(i)) ? 1 : 0;
            sb.append(ret).append('\n');
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
