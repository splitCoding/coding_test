package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11057 {
    static int N;
    static int[][] count;
    static int[][] sum;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        count = new int[N + 1][10];
    }

    static void solution1() {
        for (int i = 0; i < 10; i++) count[1][i] = 1;
        sum[1][9] = count[1][9];
        for (int i = 8; i >= 0; i--) {
            sum[1][i] = sum[1][i + 1] + count[1][i];
        }
        for (int idx = 2; idx <= N; idx++) {
            count[idx][0] = sum[idx - 1][0];
            for (int i = 1; i < 10; i++) {
                if (i == 9) {
                    count[idx][i] = 1;
                } else {
                    count[idx][i] = sum[idx - 1][i];
                }
            }
            sum[idx][9] = count[idx][9];
            for (int i = 8; i >= 0; i--) {
                sum[idx][i] = sum[idx][i + 1] + count[idx][i];
            }
        }
    }

    static void solution2() {
        for (int i = 0; i < 10; i++) count[0][i] = 1;
        for (int i = 1; i <= N; i++) {
            for (int idx = 9; idx >= 0; idx--) {
                if (idx == 9) {
                    count[i][idx] = 1;
                    continue;
                }
                count[i][idx] = (count[i - 1][idx] + count[i][idx + 1]) % 10007 ;
            }
        }
        System.out.println(count[N][0]);
    }

    public static void main(String[] args) {
        input();
        solution2();
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
