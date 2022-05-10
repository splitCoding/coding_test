package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2637 {
    static int N;
    static List<Integer>[] parts;
    static int[] ret, degree, in_degree;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        parts = new List[N + 1];
        degree = new int[N + 1];
        in_degree = new int[N + 1];
        ret = new int[N + 1];
        for (int i = 1; i <= N; i++) parts[i] = new ArrayList<>();
        int M = scan.nextInt();
        for (int i = 0; i < M; i++) {
            int X = scan.nextInt(), Y = scan.nextInt(), K = scan.nextInt();
            parts[X].add(Y);
            parts[X].add(K);
            degree[Y]++;
            in_degree[X]++;
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] != 0) continue;
            q.offer(i);
            ret[i] = 1;
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (int i = 1; i < parts[now].size(); i += 2) {
                int need = parts[now].get(i - 1), num = parts[now].get(i);
                ret[need] += num * ret[now];
                degree[need]--;
                if (degree[need] == 0) q.offer(need);
            }
        }
    }


    public static void main(String[] args) {
        input();
        solution();
        for (int i = 1; i <= N; i++) {
            if (in_degree[i] != 0) continue;
            System.out.println(i + " " + ret[i]);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
