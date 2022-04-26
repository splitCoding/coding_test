package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_5567 {
    static int n, m;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        n = scan.nextInt();
        m = scan.nextInt();
        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int f1 = scan.nextInt(), f2 = scan.nextInt();
            nodes[f1].add(f2);
            nodes[f2].add(f1);
        }
    }

    static void solution1() {
        Queue<Integer> q = new LinkedList<>();
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        q.offer(1);
        q.offer(0);
        visited[1] = true;
        while (!q.isEmpty()) {
            int n = q.poll();
            int k = q.poll();
            for (int i : nodes[n]) {
                if (visited[i]) continue;
                if (k + 1 > 2) continue;
                visited[i] = true;
                count++;
                q.offer(i);
                q.offer(k + 1);
            }
        }
        System.out.println(count);
    }

    static void solution2() {
        int count = 0;
        boolean[] visited = new boolean[n + 1];
        visited[1] = true;
        for (int i : nodes[1]) {
            count++;
            visited[i] = true;
        }
        for (int i : nodes[1]) {
            for (int j : nodes[i]) {
                if (visited[j]) continue;
                count++;
                visited[j] = true;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        solution1();
        solution2();
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
