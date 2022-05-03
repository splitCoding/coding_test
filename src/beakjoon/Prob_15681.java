package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_15681 {
    static int N, R, Q;
    static List<Integer>[] nodes;
    static int[] target, subs;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        R = scan.nextInt();
        Q = scan.nextInt();
        target = new int[Q];
        nodes = new List[N + 1];
        subs = new int[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
        for (int i = 0; i < Q; i++) target[i] = scan.nextInt();
    }

    static void dfs(int now, int prev) {
        subs[now] = 1;
        for (int i : nodes[now]) {
            if (i == prev) continue;
            dfs(i, now);
            subs[now] += subs[i];
        }
    }

    static void solution() {
        dfs(R, R);
        for (int i : target) System.out.println(subs[i]);
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
