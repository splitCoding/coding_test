package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1389 {
    static int N, M;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited;
        int least_bacon = Integer.MAX_VALUE, result = Integer.MAX_VALUE;
        for (int f = 1; f <= N; f++) {
            int bacon = 0;
            visited = new boolean[N + 1];
            q.offer(f);
            q.offer(0);
            visited[f] = true;
            while (!q.isEmpty()) {
                int fr = q.poll();
                int n = q.poll();
                for (int i : nodes[fr]) {
                    if (visited[i]) continue;
                    visited[i] = true;
                    bacon += n + 1;
                    q.offer(i);
                    q.offer(n + 1);
                }
            }
            if (bacon <= least_bacon) {
                result = (bacon == least_bacon) ? Math.min(result, f) : f;
                least_bacon = bacon;
            }
        }
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
