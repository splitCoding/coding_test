package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2252 {
    static int N, M;
    static List<Integer>[] nodes;
    static int[] degree;
    static boolean[] visited;


    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        degree = new int[N + 1];
        M = scan.nextInt();
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            degree[n2]++;
        }
    }

    static void bfs(int k) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            System.out.println(now);
            for (int i : nodes[now]) {
                degree[i]--;
                if (degree[i] == 0) q.offer(i);
            }
        }
    }

    static void solution() {
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                bfs(i);
                break;
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
