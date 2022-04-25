package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11724 {
    static int N, M, result;
    static List<Integer>[] nodes;
    static boolean[] visited;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        nodes = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int n1 = scan.nextInt();
            int n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void dfs(int n) {
        for (int i : nodes[n]) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i);
        }
    }

    static void solution() {
        visited[0] = true;
        for (int i = 0; i < nodes.length; i++) {
            if (visited[i]) continue;
            result++;
            dfs(i);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(result);
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
