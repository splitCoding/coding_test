package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3055_Re {
    static int answer, R, C, hMoves[][], wMoves[][];
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static Info finish;
    static String[] map;
    static boolean checked[][];
    static Queue<Info> q = new LinkedList<>();


    static void input() {
        FastReader scan = new FastReader();
        R = scan.nextInt();
        C = scan.nextInt();
        map = new String[R];
        for (int i = 0; i < map.length; i++) map[i] = scan.next();
    }

    static class Info {
        int r, c;

        Info(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "( " + r + " , " + c + " )";
        }

    }

    static void init() {
        q.clear();
        checked = new boolean[R][C];
    }

    static void find(char target, Queue<Info> q, boolean[][] checked) {
        if (target == '*') {
            //* 찾아서 큐에 Info로 넣고, checked에 true로 체크
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length(); j++) {
                    if (map[i].charAt(j) == target) {
                        checked[i][j] = true;
                        q.offer(new Info(i, j));
                    }
                }
            }
        } else if (target == 'D') {
            //D 찾아서 finish에 좌표로 저장
            All:
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length(); j++) {
                    if (map[i].charAt(j) == target) {
                        finish = new Info(i, j);
                        break All;
                    }
                }
            }
        } else if (target == 'S') {
            //S 찾아서 큐에 Info로 넣고 checked에 true로 체크
            All:
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length(); j++) {
                    if (map[i].charAt(j) == target) {
                        checked[i][j] = true;
                        q.offer(new Info(i, j));
                        break All;
                    }
                }
            }
        }
    }


    static boolean waterDisable(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C || checked[r][c] || map[r].charAt(c) == 'X' || map[r].charAt(c) == 'D';
    }

    static boolean hodgeDisable(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C || checked[r][c] || map[r].charAt(c) == 'X';
    }

    static void moves(int[][] progress, int r, int c, int prev_r, int prev_c) {
        checked[r][c] = true;
        progress[r][c] = progress[prev_r][prev_c] + 1;
        q.offer(new Info(r, c));
    }


    static void waterMove() {
        wMoves = new int[R][C];
        init();
        find('*', q, checked);

        while (!q.isEmpty()) {
            Info now = q.poll();
            for (int[] m : move) {
                int n_r = now.r + m[0], n_c = now.c + m[1];
                if (!waterDisable(n_r, n_c)) moves(wMoves, n_r, n_c, now.r, now.c);
            }
        }
    }

    static int hodgeMove() {
        hMoves = new int[R][C];
        init();
        find('S', q, checked);
        find('D', null, null);
        while (!q.isEmpty()) {
            Info now = q.poll();
            for (int[] m : move) {
                int r = now.r, c = now.c;
                int n_r = r + m[0], n_c = c + m[1];
                if (!hodgeDisable(n_r, n_c)) {
                    if (finish.r == n_r && finish.c == n_c) return hMoves[r][c] + 1;
                    //물이 들어온 시간이 고슴도치가 온 시간보다 빠를때
                    if (wMoves[n_r][n_c] != 0 && wMoves[n_r][n_c] <= hMoves[r][c] + 1) continue;
                    moves(hMoves, n_r, n_c, r, c);
                }
            }
        }
        return 0;
    }

    static void solution() {
        waterMove();
        answer = hodgeMove();
    }

    static void showResult() {
        System.out.println((answer == 0) ? "KAKTUS" : answer);
    }

    public static void main(String[] args) {
        input();
        solution();
        showResult();
    }

    private static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(bf.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
