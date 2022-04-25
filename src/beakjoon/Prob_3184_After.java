package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob_3184_After {
    static int R, C, tmp_wolf, tmp_sheep, final_wolf, final_sheep;
    static String[] back;
    static boolean[][] visited;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void input() {
        FastReader scan = new FastReader();
        R = scan.nextInt();
        C = scan.nextInt();
        visited = new boolean[R][C];
        back = new String[R];
        for (int i = 0; i < R; i++) back[i] = scan.next();
    }

    static void dfs(int i, int j) {
        visited[i][j] = true;
        if (back[i].charAt(j) == 'v') tmp_wolf++;
        if (back[i].charAt(j) == 'o') tmp_sheep++;
        for (int k = 0; k < move.length; k++) {
            int nr = i + move[k][0], nc = j + move[k][1];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (visited[nr][nc]) continue;
            if (back[nr].charAt(nc) == '#') continue;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }

    static void solution() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j]) continue;
                if (back[i].charAt(j) != '#') {
                    tmp_sheep = 0;
                    tmp_wolf = 0;
                    dfs(i, j);
                    if (tmp_sheep <= tmp_wolf) final_wolf += tmp_wolf;
                    else final_sheep += tmp_sheep;
                }
            }
        }
        System.out.println(final_sheep + " " + final_wolf);
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

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
