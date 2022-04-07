package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2470_Second {
    static int N, a[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        a = new int[N];
        for (int i = 0; i < N; i++) {
            a[i] = scan.nextInt();
        }
    }

    static void solution() {
        Arrays.sort(a);
        int ret1 = 0, ret2 = 0, min = Integer.MAX_VALUE;
        int L = 0, R = N - 1;
        while (L < R) {
            int sum = a[L] + a[R];
            if (sum == 0) {
                ret1 = a[L];
                ret2 = a[R];
                break;
            } else {
                if (Math.abs(sum) < min) {
                    ret1 = a[L];
                    ret2 = a[R];
                    min = Math.abs(a[L] + a[R]);
                }
                if (sum > 0) R--;
                if (sum < 0) L++;
            }
        }
        System.out.println(ret1 + " " + ret2);
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
