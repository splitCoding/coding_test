package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_4803 {
    static int N, M, edge_count = 0, node_count = 1, case_count = 1;
    ; //N ≤ 500, M ≤ N(N-1)/2
    static boolean[] visited;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        while (true) {
            N = scan.nextInt();
            M = scan.nextInt();
            if (N == 0 && M == 0) break;
            visited = new boolean[N + 1];
            nodes = new List[N + 1];
            for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>(N);
            for (int i = 0; i < M; i++) {
                int e1 = scan.nextInt();
                int e2 = scan.nextInt();
                nodes[e1].add(e2);
                nodes[e2].add(e1);
            }
            solution();
        }
    }

    static void dfs(int idx) {
        if (visited[idx]) return;
        visited[idx] = true;
        edge_count += nodes[idx].size();
        for (int i : nodes[idx]) {
            if (visited[i]) continue;
            node_count++;
            dfs(i);
        }
    }

    static void solution() {
        int count = 0;
        for (int i = 1; i < nodes.length; i++) {
            if (visited[i]) continue;
            dfs(i);
            if (node_count - 1 == edge_count / 2) {
                count++;
            }
            node_count = 1;
            edge_count = 0;
        }
        if (count == 0) {
            System.out.println("Case " + case_count++ + ": No trees.");
        } else if (count == 1) {
            System.out.println("Case " + case_count++ + ": There is one tree.");
        } else {
            System.out.println("Case " + case_count++ + ": A forest of " + count + " trees.");
        }
    }

    public static void main(String[] args) {
        input();
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

        Long nextLong() {
            return Long.parseLong(next());
        }

    }
}
