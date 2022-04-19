package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1206 {
    static int N, M, V;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        V = scan.nextInt();
        visited = new boolean[N + 1];
        nodes = new List[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int i1 = scan.nextInt();
            int i2 = scan.nextInt();
            nodes[i1].add(i2);
            nodes[i2].add(i1);
        }
        for (int i = 0; i <= N; i++) Collections.sort(nodes[i]);
    }

    static void dfs(int k) {
        if (visited[k]) return;
        visited[k] = true;
        sb.append(k + " ");
        for (int i : nodes[k]) {
            if (visited[i]) continue;
            dfs(i);
        }
    }

    static void bfs(int k) {
        sb.append('\n');
        Queue<Integer> q = new LinkedList<>();
        q.offer(k);
        while (!q.isEmpty()) {
            int node = q.poll();
            sb.append(node + " ");
            visited[node] = true;
            for (int j : nodes[node]) {
                if (visited[j]) continue;
                visited[j] = true;
                q.offer(j);
            }
        }
    }

    public static void main(String[] args) {
        input();
        dfs(V);
        visited = new boolean[N + 1];
        bfs(V);
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

        Long nextLong() {
            return Long.parseLong(next());
        }

    }
}
