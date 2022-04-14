package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11725 {
    static int N;
    static List<Integer>[] Nodes;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        Nodes = new List[N + 1];
        for (int i = 0; i <= N; i++) Nodes[i] = new ArrayList();
        for (int i = 0; i < N - 1; i++) {
            int i1 = scan.nextInt(), i2 = scan.nextInt();
            Nodes[i1].add(i2);
            Nodes[i2].add(i1);
        }
    }

    static void solution() {
        int[] parent = new int[N + 1];
        Queue<Integer> q = new LinkedList();
        q.offer(1);
        while (!q.isEmpty()) {
            int visit = q.poll();
            for (int i : Nodes[visit]) {
                if (parent[i] == 0) {
                    parent[i] = visit;
                    q.offer(i);
                }
            }
        }
        for (int i = 2; i < parent.length; i++) System.out.println(parent[i]);
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }

    }
}
