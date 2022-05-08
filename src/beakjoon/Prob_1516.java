package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1516 {
    static FastReader scan = new FastReader();
    static int N;
    static int[] time_need, degree, result;
    static List<Integer>[] nodes;

    static void input() {
        N = scan.nextInt();
        time_need = new int[N + 1];
        degree = new int[N + 1];
        result = new int[N + 1];
        make_nodes();
    }

    static void make_nodes() {
        nodes = new List[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            String line = scan.nextLine();
            int idx = 0;
            for (String s : line.split(" ")) {
                if (idx == 0) {
                    time_need[i] = Integer.parseInt(s);
                    idx++;
                } else {
                    if (Integer.parseInt(s) == -1) continue;
                    nodes[Integer.parseInt(s)].add(i);
                    degree[i]++;
                }
            }
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < degree.length; i++) if (degree[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            int now = q.poll();
            result[now] += time_need[now];
            for (int i : nodes[now]) {
                result[i] = Math.max(result[i], result[now]);
                if (--degree[i] == 0) q.offer(i);
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        for (int i : result) {
            if (i == 0) continue;
            System.out.println(i);
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

        String nextLine() {
            String ret = "";
            try {
                ret = bf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
