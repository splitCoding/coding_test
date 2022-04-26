package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2644 {
    static int n, adj1, adj2, ret = -1;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        n = scan.nextInt();
        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new ArrayList<>();
        adj1 = scan.nextInt();
        adj2 = scan.nextInt();
        int m = scan.nextInt();
        for (int i = 0; i < m; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        visited[adj1] = true;
        q.offer(adj1);
        q.offer(0);
        while (!q.isEmpty()) {
            int n = q.poll();
            int count = q.poll();
            if (n == adj2) {
                ret = count;
                q.clear();
                continue;
            }
            for (int i : nodes[n]) {
                if (visited[i]) continue;
                visited[i] = true;
                q.offer(i);
                q.offer(count + 1);
            }
        }
        System.out.println(ret);
    }

    public static void main(String[] args) {
        input();
        bfs();
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
