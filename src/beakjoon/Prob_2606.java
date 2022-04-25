package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2606 {
    static int N, E, result;
    static List<Integer>[] nodes;
    static boolean[] visited;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        E = scan.nextInt();
        nodes = new List[N + 1];
        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void dfs(int k) {
        if (visited[k]) return;
        visited[k] = true;
        for (int i : nodes[k]) {
            if (visited[i]) continue;
            result++;
            dfs(i);
        }
    }

    static void solution() {
        dfs(1);
        System.out.println(result);
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
