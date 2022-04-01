package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3273 {
    static int N, X, count, arr[];
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = scan.nextInt();
        X = scan.nextInt();
    }

    static void solution() {
        Arrays.sort(arr);
        int L = 0, R = N - 1;
        while (L < R) {
            if (arr[L] + arr[R] >= X) {
                if (arr[L] + arr[R] == X) count++;
                R--;
            } else {
                L++;
            }
        }
        sb.append(count);
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

    }
}
