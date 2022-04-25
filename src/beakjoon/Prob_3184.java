package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3184 {
    static int R, C, final_wolf, final_sheep;
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

    static void bfs(int i, int j) {
        int wolves = 0, sheeps = 0;
        if( back[i].charAt(j) == 'v') wolves++;
        if( back[i].charAt(j) == 'o') sheeps++;
        Queue<Integer> q = new LinkedList<>();
        visited[i][j] = true;
        q.offer(i);
        q.offer(j);
        while (!q.isEmpty()) {
            int r = q.poll(), c = q.poll();
            for (int k = 0; k < move.length; k++) {
                int nr = r + move[k][0], nc = c + move[k][1];
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (visited[nr][nc]) continue;
                if (back[nr].charAt(nc) == '#') continue;
                if (back[nr].charAt(nc) == 'v') wolves++;
                if (back[nr].charAt(nc) == 'o') sheeps++;
                visited[nr][nc] = true;
                q.offer(nr);
                q.offer(nc);
            }
        }
        if (sheeps > wolves) final_sheep += sheeps;
        else final_wolf += wolves;
    }

    static void solution() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (visited[i][j]) continue;
                if (back[i].charAt(j) != '#') {
                    bfs(i, j);
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
