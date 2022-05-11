package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1753 {
    static class Info {
        int to;
        int weight;

        Info(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V, E, R;
    static int[] ret;
    static List<Info>[] nodes;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        V = scan.nextInt();
        E = scan.nextInt();
        R = scan.nextInt();
        nodes = new List[V + 1];
        ret = new int[V + 1];
        for (int i = 1; i <= V; i++) ret[i] = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            nodes[scan.nextInt()].add(new Info(scan.nextInt(), scan.nextInt()));
        }
    }

    static void dijkstra_pq() {
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Info> q = new PriorityQueue<>(Comparator.comparing(o -> o.weight));
        //루트 넣기
        q.offer(new Info(R, 0));
        ret[R] = 0;
        while (!q.isEmpty()) {
            Info now = q.poll();
            if (ret[now.to] != now.weight) continue;
            for (Info i : nodes[now.to]) {
                if (ret[i.to] <= ret[now.to] + i.weight) continue;
                ret[i.to] = ret[now.to] + i.weight;
                q.offer(new Info(i.to, ret[i.to]));
            }
        }
    }

    static void dijkstra() {
        boolean[] check = new boolean[V + 1];
        int min, min_idx;
        ret[R] = 0;
        while (true) {
            min = Integer.MAX_VALUE;
            min_idx = -1;
            for (int i = 1; i <= V; i++) {
                if (ret[i] < min && !check[i]) {
                    min_idx = i;
                    min = ret[i];
                }
            }
            if (min_idx == -1) break;
            for (Info i : nodes[min_idx]) {
                int new_weight = ret[min_idx] + i.weight;
                if (ret[i.to] <= new_weight) continue;
                ret[i.to] = new_weight;
            }
            check[min_idx] = true;
        }
    }

    static void show_result() {
        for (int i = 1; i <= V; i++) {
            sb.append((ret[i] == Integer.MAX_VALUE) ? "INF" : ret[i]).append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        input();
        dijkstra_pq();
        show_result();
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