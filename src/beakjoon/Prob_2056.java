package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2056 {
    static int N;
    static List<Integer>[] nodes;
    static int[] time_need, degree, result;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList();
        time_need = new int[N + 1];
        degree = new int[N + 1];
        result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            time_need[i] = scan.nextInt();
            degree[i] = scan.nextInt();
            for (int c = 0; c < degree[i]; c++) nodes[scan.nextInt()].add(i);
        }
    }

    static void solution() {
        int ret = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < degree.length; i++) if (degree[i] == 0) q.offer(i);
        while (!q.isEmpty()) {
            int now = q.poll();
            result[now] += time_need[now];
            ret = Math.max(ret, result[now]);
            for (int i : nodes[now]) {
                degree[i]--;
                result[i] = Math.max(result[i], result[now]);
                if (degree[i] == 0) q.offer(i);
            }
        }
        System.out.println(ret);
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
