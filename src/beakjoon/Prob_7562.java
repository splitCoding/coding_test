package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_7562 {
    static FastReader scan = new FastReader();
    static int I, start_r, start_c, adj_r, adj_c;
    static StringBuilder sb = new StringBuilder();
    static boolean[][] visited;
    static int[][] moves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    static void input() {
        I = scan.nextInt();
        visited = new boolean[I][I];
        start_r = scan.nextInt();
        start_c = scan.nextInt();
        adj_r = scan.nextInt();
        adj_c = scan.nextInt();
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start_r);
        q.offer(start_c);
        q.offer(0);
        visited[start_r][start_c] = true;
        while (!q.isEmpty()) {
            int r = q.poll(), c = q.poll(), n = q.poll();
            if (r == adj_r && c == adj_c) {
                sb.append(n + "\n");
                q.clear();
                continue;
            }
            for (int i = 0; i < moves.length; i++) {
                int nr = r + moves[i][0];
                int nc = c + moves[i][1];
                if (nr < 0 || nc < 0 || nr >= I || nc >= I) continue;
                if (visited[nr][nc]) continue;
                visited[nr][nc] = true;
                q.offer(nr);
                q.offer(nc);
                q.offer(n + 1);
            }
        }
    }

    public static void main(String[] args) {
        int t = scan.nextInt();
        for (int i = 0; i < t; i++) {
            input();
            solution();
        }
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
