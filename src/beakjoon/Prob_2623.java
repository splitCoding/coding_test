package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2623 {
    static int N, M, count;
    static int[] degree;
    static List<Integer>[] nodes;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        nodes = new List[N + 1];
        degree = new int[N + 1];
        for (int i = 0; i <= N; i++) nodes[i] = new ArrayList<>();
        List<Integer> singers = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int num = scan.nextInt();
            for (int j = 0; j < num; j++) singers.add(scan.nextInt());
            for (int s = 1; s < singers.size(); s++) {
                int prev = singers.get(s-1), after = singers.get(s);
                nodes[prev].add(after);
                degree[after]++;
            }
            singers.clear();
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < degree.length; i++) if (degree[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            int now = q.poll();
            count++;
            sb.append(now).append('\n');
            for (int i : nodes[now]) {
                degree[i]--;
                if (degree[i] == 0) q.offer(i);
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println((count == N) ? sb.toString() : 0);
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
