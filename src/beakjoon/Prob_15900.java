package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_15900 {
    static int N, ret;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        q.offer(1);
        q.offer(0);
        visited[1] = true;
        while (!q.isEmpty()) {
            boolean find = false;
            int n = q.poll();
            int count = q.poll();
            for (int i : nodes[n]) {
                if (visited[i]) continue;
                visited[i] = true;
                q.offer(i);
                q.offer(count + 1);
                find = true;
            }
            if (!find) ret += count;
        }
    }

    static void solution() {
        bfs();
        System.out.println((ret % 2 == 0) ? "No" : "Yes");
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
