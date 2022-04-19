package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob_2667_After {
    static int N, count = 0;
    static int[][] cases = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static String[] A;
    static boolean[][] visited;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        A = new String[N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) A[i] = scan.next();
    }

    static void dfs(int row, int col) {
        visited[row][col] = true;
        count++;
        for (int[] aCase : cases) {
            int tmp_row = row + aCase[0];
            int tmp_col = col + aCase[1];
            if (tmp_row < 0 || tmp_row >= N || tmp_col < 0 || tmp_col >= N) continue;
            if (visited[tmp_row][tmp_col] || A[tmp_row].charAt(tmp_col) - '0' == 0) continue;
            dfs(tmp_row, tmp_col);
        }
    }

    static void solution() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i].charAt(j) - '0' == 0 || visited[i][j]) continue;
                dfs(i, j);
                result.add(count);
                count = 0;
            }
        }
        Collections.sort(result);
        System.out.println(result.size());
        for (int i : result) System.out.println(i);
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
