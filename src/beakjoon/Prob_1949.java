package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1949 {
    static int N;
    static int[] numbers;
    static int[][] dy;
    static List<Integer>[] village;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();

        numbers = new int[N + 1];
        for (int i = 1; i <= N; i++) numbers[i] = scan.nextInt();

        village = new List[N + 1];
        for (int i = 1; i <= N; i++) village[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            village[n1].add(n2);
            village[n2].add(n1);
        }
    }

    static void solution() {
        dy = new int[N + 1][2];
        dfs(1, 0);
    }

    static void dfs(int k, int prev) {
        dy[k][1] = numbers[k];
        for (int i : village[k]) {
            if (i == prev) continue;
            dfs(i, k);
            dy[k][0] += Math.max(dy[i][0], dy[i][1]);
            dy[k][1] += dy[i][0];
        }
    }

    static void show_result() {
        System.out.println(Math.max(dy[1][0], dy[1][1]));
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
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

