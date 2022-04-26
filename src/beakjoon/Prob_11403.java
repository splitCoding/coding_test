package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11403 {
    static int N;
    static List<Integer>[] nodes;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>(N);
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (scan.nextInt() == 1) nodes[i].add(j);
            }
        }
    }

    static void dfs(int k) {
        for (int i : nodes[k]) {
            if (visited[i]) continue;
            visited[i] = true;
            dfs(i);
        }
    }

    static void solution() {
        for (int i = 1; i <= N; i++) {
            visited = new boolean[N + 1];
            if (!nodes[i].isEmpty()) dfs(i);
            for (int j = 1; j <= N; j++) sb.append((visited[j]) ? 1 + " " : 0 + " ");
            sb.append('\n');
        }
        System.out.println(sb.toString());
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
