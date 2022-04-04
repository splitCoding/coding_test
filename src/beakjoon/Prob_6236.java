package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_6236 {
    static int N, M, max, money_need[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        money_need = new int[N];
        for (int i = 0; i < N; i++) {
            money_need[i] = scan.nextInt();
            max = Math.max(max, money_need[i]);
        }
    }

    static boolean is_possible(int money) {
        int current = money;
        int count = 1;
        for (int i = 0; i < N; i++) {
            if (money_need[i] <= current) {
                current -= money_need[i];
            } else {
                current = money - money_need[i];
                count++;
            }
        }
        return count <= M;
    }

    static void solution() {
        int L = max, R = 1000000000, ret = 0;
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
