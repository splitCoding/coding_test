package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob_4963 {
    static FastReader scan = new FastReader();
    static int w, h, result;
    static int[][] map, move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    static boolean[][] visited;

    static void input() {
        w = scan.nextInt();
        h = scan.nextInt();
        map = new int[h][w];
        visited = new boolean[h][w];
        for (int i = 0; i < h; i++) for (int j = 0; j < w; j++) map[i][j] = scan.nextInt();
    }

    static void dfs(int r, int c) {
        visited[r][c] = true;
        for (int i = 0; i < move.length; i++) {
            int nr = r + move[i][0];
            int nc = c + move[i][1];
            if (nr < 0 || nc < 0 || nr >= h || nc >= w) continue;
            if (map[nr][nc] == 0) continue;
            if (visited[nr][nc]) continue;
            dfs(nr, nc);
        }
    }

    static void solution() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (visited[i][j]) continue;
                if (map[i][j] == 1) {
                    result++;
                    dfs(i, j);
                }
            }
        }
        System.out.println(result);
        result = 0;
    }

    public static void main(String[] args) {
        while (true) {
            input();
            if (w == 0 && h == 0) break;
            solution();
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
