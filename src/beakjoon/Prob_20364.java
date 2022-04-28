package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob_20364 {
    static int N, Q;
    static int[] A;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        Q = scan.nextInt();
        visited = new boolean[N + 1];
        A = new int[Q];
        for (int i = 0; i < Q; i++) A[i] = scan.nextInt();
    }

    static void solution() {
        for (int i = 0; i < Q; i++) {
            boolean possible = true;
            int stuck = 0;
            int n = A[i];
            while (n > 0) {
                if (visited[n] && n != 1) {
                    possible = false;
                    stuck = n;
                }
                n /= 2;
            }
            if (possible) visited[A[i]] = true;
            sb.append((possible) ? 0 : stuck).append('\n');
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(sb.toString());
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
