package beakjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob_2805 {
    static int N, M;
    static int[] woods;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        woods = new int[N];
        for (int i = 0; i < N; i++) woods[i] = scan.nextInt();
    }

    static void solution() {
        //나무가 하나일 떄
        if (N == 1) {
            sb.append(woods[0] - M);
            return;
        }
        Arrays.sort(woods);
        //필요한 높이가 1일 때
        if (M == 1) {
            sb.append(woods[N - 1] - 1);
            return;
        }
        int height = woods[N - 1];
        int sum = 0;
        while (sum < M) {
            sum = 0;
            height--;
            for (int i = N - 1; i >= 0; i--) {
                if (woods[i] > height) sum += woods[i] - height;
                else break;
            }
        }
        sb.append(height);
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
