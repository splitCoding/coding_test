package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2343 {
    static int N, M, lesson[], max = 0, sum = 0;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        lesson = new int[N];
        M = scan.nextInt();
        for (int i = 0; i < N; i++) {
            lesson[i] = scan.nextInt();
            sum += lesson[i];
            max = Math.max(max, lesson[i]);
        }
    }

    static boolean is_possible(int divide) {
        int sum = 0;
        int count = 1;
        for (int i : lesson) {
            if (sum + i > divide) {
                count++;
                sum = i;
            } else {
                sum += i;
            }
        }
        return count <= M;
    }

    static void solution() {
        int L = max, R = sum, ret = 0;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (is_possible(mid)) {
                ret = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
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
