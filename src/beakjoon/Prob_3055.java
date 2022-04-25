package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3055 {
    static int R, C, D[]; // 50보다 작거나 같은 자연수
    static String[] map;
    static int[][] when_water_arrive, when_animal_arrive;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static Queue<Integer> q = new LinkedList<>();


    static void input() {
        FastReader scan = new FastReader();
        R = scan.nextInt();
        C = scan.nextInt();
        map = new String[R];
        when_water_arrive = new int[R][C];
        when_animal_arrive = new int[R][C];
        for (int i = 0; i < R; i++) map[i] = scan.next();
    }

    static void set_when_water_arrive() {
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i].charAt(j) == '*') {
                    q.add(i);
                    q.add(j);
                    D = new int[]{i, j};
                    when_water_arrive[i][j] = 1;
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int row = q.poll();
            int col = q.poll();
            for (int i = 0; i < move.length; i++) {
                int nrow = row + move[i][0];
                int ncol = col + move[i][1];
                if (nrow < 0 || ncol < 0 || nrow >= R || ncol >= C) continue;
                if (map[nrow].charAt(ncol) != '.') continue;
                if (visited[nrow][ncol]) continue;
                visited[nrow][ncol] = true;
                when_water_arrive[nrow][ncol] = when_water_arrive[row][col] + 1;
                q.offer(nrow);
                q.offer(ncol);
            }
        }
//        for (int[] i : when_water_arrive) System.out.println(Arrays.toString(i));
    }

    static void set_when_animal_arrive() {
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i].charAt(j) == 'S') {
                    q.add(i);
                    q.add(j);
                    when_animal_arrive[i][j] = 1;
                    visited[i][j] = true;
                } else if (map[i].charAt(j) == 'D') {
                    D = new int[]{i, j};
                }
            }
        }
        while (!q.isEmpty()) {
            int row = q.poll();
            int col = q.poll();
            for (int i = 0; i < move.length; i++) {
                int nrow = row + move[i][0];
                int ncol = col + move[i][1];
                if (nrow < 0 || ncol < 0 || nrow >= R || ncol >= C) continue;
                if (map[nrow].charAt(ncol) != '.' && map[nrow].charAt(ncol) != 'D') continue;
                if (when_water_arrive[nrow][ncol] <= when_animal_arrive[row][col] + 1 && when_water_arrive[nrow][ncol] != 0)
                    continue;
                if (visited[nrow][ncol]) continue;
                when_animal_arrive[nrow][ncol] = when_animal_arrive[row][col] + 1;
                visited[nrow][ncol] = true;
                q.offer(nrow);
                q.offer(ncol);
            }
        }
//        for (int[] i : when_animal_arrive) System.out.println(Arrays.toString(i));
        System.out.println((when_animal_arrive[D[0]][D[1]] == 0) ? "KAKTUS" : when_animal_arrive[D[0]][D[1]] - 1);
    }

    static void solution() {
        set_when_water_arrive();
        set_when_animal_arrive();
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
