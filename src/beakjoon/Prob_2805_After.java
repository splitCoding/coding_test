package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2805_After {
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

    static boolean is_enough(int height) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (woods[i] > height) sum += woods[i] - height;
        }
        return sum >= M;
    }

    static void solution() {
        //높이를 기준으로 이분 탐색
        int ret = 0;
        int Left = 1, Right = 2000000000;
        while (Left < Right) {
            int mid = (Left + Right) / 2;
            if (is_enough(mid)) {
                ret = mid;
                Left = mid + 1;
            } else {
                Right = mid - 1;
            }
        }
        sb.append(ret);
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
