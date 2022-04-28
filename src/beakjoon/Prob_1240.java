package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1240 {
    static int N, M;
    static List<Integer>[] nodes;
    static int[][] adj;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt(), cost = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n1].add(cost);
            nodes[n2].add(n1);
            nodes[n2].add(cost);
        }
        adj = new int[M][2];
        for (int i = 0; i < M; i++) {
            adj[i][0] = scan.nextInt();
            adj[i][1] = scan.nextInt();
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited;
        int[] check = new int[N + 1];

        for (int i = 0; i < M; i++) {
            int n1 = adj[i][0], n2 = adj[i][1];
            visited = new boolean[N + 1];
            q.offer(n1);
            check[n1] = 0;
            visited[n1] = true;
            while (!q.isEmpty()) {
                int n = q.poll();
                if (n == n2) {
                    System.out.println(check[n]);
                    q.clear();
                    break;
                }
                for (int j = 1; j < nodes[n].size(); j += 2) {
                    int next = nodes[n].get(j - 1);
                    int cost = nodes[n].get(j);
                    if (visited[next]) continue;
                    visited[next] = true;
                    check[next] = check[n] + cost;
                    q.offer(next);
                }
            }
        }
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
