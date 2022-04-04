package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_17266 {
    static int N, M, light[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        light = new int[M];
        for (int i = 0; i < M; i++) light[i] = scan.nextInt();
    }

    static boolean is_possible(int length) {
        if (light[0] > length || N - light[light.length - 1] > length) return false;
        int count = 0;
        for (int i = 1; i < M; i++) {
            if (light[i] - light[i - 1] > length * 2) return false;
            else count++;
        }
        return count <= M;
    }

    static void solution() {
        int L = 1, R = 100000, ret = 0;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (is_possible(mid)) {
                ret = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        System.out.println(ret);
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
