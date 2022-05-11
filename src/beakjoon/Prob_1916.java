package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1916 {
    static class Bus {
        int to;
        int cost;

        Bus(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static int city, bus, st, end;
    static int[] ret;
    static List<Bus>[] nodes;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        city = scan.nextInt();
        bus = scan.nextInt();
        nodes = new List[city + 1];
        ret = new int[city + 1];
        for (int i = 1; i <= city; i++) ret[i] = Integer.MAX_VALUE;
        for (int i = 1; i <= city; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < bus; i++) {
            nodes[scan.nextInt()].add(new Bus(scan.nextInt(), scan.nextInt()));
        }
        st = scan.nextInt();
        end = scan.nextInt();
    }

    static void dijkstra_pq() {
        boolean[] visited = new boolean[city + 1];
        PriorityQueue<Bus> q = new PriorityQueue<>(Comparator.comparing(o -> o.cost));
        //루트 넣기
        q.offer(new Bus(st, 0));
        ret[st] = 0;
        while (!q.isEmpty()) {
            Bus now = q.poll();
            if (ret[now.to] != now.cost) {
                if (now.to == end) break;
                continue;
            }
            for (Bus i : nodes[now.to]) {
                if (ret[i.to] <= ret[now.to] + i.cost) continue;
                ret[i.to] = ret[now.to] + i.cost;
                q.offer(new Bus(i.to, ret[i.to]));
            }
        }
    }

    static void dijkstra() {
        boolean[] check = new boolean[city + 1];
        int min, min_idx;
        ret[st] = 0;
        while (true) {
            min = Integer.MAX_VALUE;
            min_idx = -1;
            for (int i = 1; i <= city; i++) {
                if (ret[i] < min && !check[i]) {
                    min_idx = i;
                    min = ret[i];
                }
            }
            if (min_idx == -1 || min_idx == end) break;
            for (Bus i : nodes[min_idx]) {
                int new_weight = ret[min_idx] + i.cost;
                if (ret[i.to] <= new_weight) continue;
                ret[i.to] = new_weight;
            }
            check[min_idx] = true;
        }
    }

    static void show_result() {
        System.out.println(ret[end]);
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
