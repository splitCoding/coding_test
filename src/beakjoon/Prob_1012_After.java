package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob_1012_After {
    static FastReader scan = new FastReader();
    static int T, R, C, K, result = 0;
    static boolean[][] Farm, visited;
    static int[][] Cabbage, move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void input() {
        C = scan.nextInt();
        R = scan.nextInt();
        K = scan.nextInt();
        Cabbage = new int[K][2];
        Farm = new boolean[R][C];
        visited = new boolean[R][C];
        for (int j = 0; j < K; j++) {
            int c = scan.nextInt();
            int r = scan.nextInt();
            Farm[r][c] = true;
            Cabbage[j][0] = r;
            Cabbage[j][1] = c;
        }
    }

    static void dfs(int r, int c) {
        for (int i = 0; i < move.length; i++) {
            int nr = r + move[i][0], nc = c + move[i][1];
            if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
            if (visited[nr][nc]) continue;
            if (!Farm[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < K; i++) {
            int[] ac = Cabbage[i];
            //이미 탐색했거나 배추가 없는 곳일 때
            if (visited[ac[0]][ac[1]] || !Farm[ac[0]][ac[1]]) continue;
            result++;
            visited[ac[0]][ac[1]] = true;
            dfs(ac[0], ac[1]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            solution();
            result = 0;
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
