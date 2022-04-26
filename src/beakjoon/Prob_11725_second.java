package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11725_second {
    static int N;
    static int[] parent;
    static List<Integer>[] nodes;


    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void bfs() {
        parent = new int[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        while (!q.isEmpty()) {
            int n = q.poll();
            for (int i : nodes[n]) {
                if (parent[i] != 0) continue;
                parent[i] = n;
                q.offer(i);
            }
        }
    }

    static void solution() {
        bfs();
        for (int i = 2; i <= N; i++) System.out.println(parent[i]);
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
