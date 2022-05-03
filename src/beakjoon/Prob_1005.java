package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1005 {
    static int N, K, adj, ret = Integer.MAX_VALUE;
    static List<Integer>[] nodes;
    static int[] degree, time_need, time_spent;
    static FastReader scan = new FastReader();

    static void input() {
        N = scan.nextInt();
        K = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        time_need = new int[N + 1];
        time_spent = new int[N + 1];
        for (int i = 1; i <= N; i++) time_need[i] = scan.nextInt();
        degree = new int[N + 1];
        for (int i = 0; i < K; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            degree[n2]++;
        }
        adj = scan.nextInt();
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (degree[i] == 0) {
                time_spent[i] = time_need[i];
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i : nodes[now]) {
                degree[i]--;
                time_spent[i] = Math.max(time_spent[i], time_spent[now] + time_need[i]);
                if (degree[i] == 0) q.offer(i);
            }
        }
    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            solution();
            System.out.println(time_spent[adj]);
        }
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
