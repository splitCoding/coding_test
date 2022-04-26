package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_18404 {
    static int N, M, start_r, start_c;
    static int[] ret;
    static int[][] adj, moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        ret = new int[M];
        start_r = scan.nextInt();
        start_c = scan.nextInt();
        adj = new int[M][2];
        for (int i = 0; i < M; i++) {
            adj[i][0] = scan.nextInt();
            adj[i][1] = scan.nextInt();
        }
    }

    static void solution() {
        int find_count = 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        q.offer(start_r);
        q.offer(start_c);
        q.offer(0);
        visited[start_r][start_c] = true;
        while (find_count < M && !q.isEmpty()) {
            int r = q.poll(), c = q.poll(), n = q.poll();
            for (int i = 0; i < M; i++) {
                if (r == adj[i][0] && c == adj[i][1]) {
                    ret[i] = n;
                    find_count++;
                }
            }
            for (int[] i : moves) {
                int nr = r + i[0];
                int nc = c + i[1];
                if (nr < 0 || nc < 0 || nr > N || nc > N) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(nr);
                q.offer(nc);
                q.offer(n + 1);
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        for (int i : ret) System.out.print(i + " ");
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
