package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_14267 {
    static int N, M, R;
    static List<Integer>[] nodes;
    static int[] p, point, ret;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        point = new int[N + 1];
        ret = new int[N + 1];
        M = scan.nextInt();
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int c = scan.nextInt();
            if (c == -1) {
                R = i;
                continue;
            }
            nodes[i].add(c);
            nodes[c].add(i);
        }
        for (int i = 0; i < M; i++) point[scan.nextInt()] += scan.nextInt();
    }

    static void dfs(int now, int prev) {
        for (int i : nodes[now]) {
            if (i == prev) continue;
            point[i] += point[now];
            dfs(i, now);
        }
    }

    static void solution() {
        dfs(R, 0);
        for(int i=1;i<=N;i++) System.out.print(point[i] + " ");
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
