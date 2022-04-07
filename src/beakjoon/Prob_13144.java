package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_13144 {
    static int N, a[], check[];
    static long count = 0;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        a = new int[N + 1];
        for (int i = 1; i <= N; i++) a[i] = scan.nextInt();
        check = new int[100000 + 1];
    }

    static void solution() {
        for (int L = 1, R = 1; L <= N; L++) {
            while (R <= N && check[a[R]] == 0) {
                check[a[R++]]++;
            }
            count += R - L;
            check[a[L]]--;
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        solution();
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
