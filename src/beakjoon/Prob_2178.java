package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2178 {
    static int N, M, result;
    static String[] A;
    static boolean[][] check;
    static int[][] cost;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        A = new String[N];
        M = scan.nextInt();
        check = new boolean[N][M];
        cost = new int[N][M];
        result = N * M;
        for (int i = 0; i < N; i++) A[i] = scan.next();
    }

    static void bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        check[row][col] = true;
        cost[row][col]++;
        q.offer(new int[]{row, col});
        while (!q.isEmpty()) {
            int[] now = q.poll();
            for (int i = 0; i < 4; i++) {
                int n_row = now[0] + move[i][0];
                int n_col = now[1] + move[i][1];
                //미로 밖으로 나갈 경우
                if (n_row < 0 || n_col < 0 || n_row >= N || n_col >= M) continue;
                //이미 지나간 길이거나 지나갈수 없는 길인 경우
                if (check[n_row][n_col] || A[n_row].charAt(n_col) - '0' == 0) continue;
                cost[n_row][n_col] = cost[now[0]][now[1]] + 1;
                check[n_row][n_col] = true;
                q.offer(new int[]{n_row, n_col});
            }
        }
        System.out.println(cost[N-1][M-1]);
    }

    static void dfs(int row, int col, int count) {
        //도착 지점에 도착시
        if (row == N - 1 && col == M - 1) {
            result = Math.min(result, count);
            return;
        }
        //현재 지점에서 좌우상하로 이동한 경우로 재귀
        for (int i = 0; i < 4; i++) {
            int n_row = row + move[i][0];
            int n_col = col + move[i][1];
            //미로 밖으로 나갈 경우
            if (n_row < 0 || n_col < 0 || n_row >= N || n_col >= M) continue;
            //이미 지나간 길이거나 지나갈수 없는 길인 경우
            if (check[row][col] || A[row].charAt(col) - '0' == 0) continue;
            //해당 구역을 지나가고 재귀
            check[row][col] = true;
            dfs(n_row, n_col, count + 1);
            check[row][col] = false;
        }
    }

    static void solution() {
        bfs(0, 0);
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
