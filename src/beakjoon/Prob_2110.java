package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2110 {
    static int N, C;
    static int[] wifi;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        wifi = new int[N];
        C = scan.nextInt();
        for (int i = 0; i < N; i++) wifi[i] = scan.nextInt();
    }

    static boolean is_possible(int k) {
        int start = wifi[0], count = 1;
        for (int i = 1; i < wifi.length; i++) {
            if (wifi[i] - start >= k) {
                count++;
                start = wifi[i];
            }
        }
        return count >= C;
    }

    static void solution() {
        Arrays.sort(wifi);
        int ret = 0;
        int L = 0, R = wifi[wifi.length - 1];
        while (L <= R) {
            int mid = (L + R) / 2;
            if (is_possible(mid)) {
                ret = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
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
