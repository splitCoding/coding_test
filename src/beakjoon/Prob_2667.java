package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2667 {
    static int N, group = 0, count = 0, A[][];
    static boolean[][] visited;
    static List<Integer> result = new ArrayList<>();
    static Queue<Integer[]> q = new LinkedList<>();


    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        A = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            String s = scan.next();
            for (int j = 0; j < N; j++) {
                A[i][j] = s.charAt(j) - '0';
            }
        }
    }

    static void able(int row, int col) {
        q.offer(new Integer[]{row, col});
        visited[row][col] = true;
        count++;
    }

    static void find_left(int row, int col) {
        if (col == 0 || visited[row][--col] || A[row][col] == 0) return;
        able(row, col);
    }

    static void find_right(int row, int col) {
        if (col == N - 1 || visited[row][++col] || A[row][col] == 0) return;
        able(row, col);
    }

    static void find_up(int row, int col) {
        if (row == 0 || visited[--row][col] || A[row][col] == 0) return;
        able(row, col);
    }

    static void find_down(int row, int col) {
        if (row == N - 1 || visited[++row][col] || A[row][col] == 0) return;
        able(row, col);
    }

    static void solution() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (A[i][j] == 0 || visited[i][j]) continue;
                group++;
                q.offer(new Integer[]{i, j});
                count++;
                visited[i][j] = true;
                while (!q.isEmpty()) {
                    Integer[] now = q.poll();
                    int row = now[0];
                    int col = now[1];
                    find_left(row, col);
                    find_right(row, col);
                    find_up(row, col);
                    find_down(row, col);
                }
                result.add(count);
                count = 0;
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        Collections.sort(result);
        System.out.println(result.size());
        for(int i : result) System.out.println(i);
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
