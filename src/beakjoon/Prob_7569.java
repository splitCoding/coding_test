package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_7569 {
    static int R, C, B;
    static int[][][] box;
    static int[][] moves = {{0, 1, 0}, {0, -1, 0}, {0, 0, -1}, {0, 0, 1}, {1, 0, 0}, {-1, 0, 0}};

    static void input() {
        FastReader scan = new FastReader();
        C = scan.nextInt();
        R = scan.nextInt();
        B = scan.nextInt();
        box = new int[B][R][C];
        for (int i = 0; i < B; i++)
            for (int j = 0; j < R; j++)
                for (int k = 0; k < C; k++)
                    box[i][j][k] = scan.nextInt();
    }

    static void solution() {
        //안익은 토마토의 갯수구하기
        int need = 0;
        for (int i = 0; i < B; i++)
            for (int j = 0; j < R; j++)
                for (int k = 0; k < C; k++)
                    if (box[i][j][k] == 0) need++;

        boolean[][][] visited = new boolean[B][R][C];
        Queue<Integer> q = new LinkedList<>();

        //익은 토마토들 큐에 넣기
        for (int i = 0; i < B; i++)
            for (int j = 0; j < R; j++)
                for (int k = 0; k < C; k++)
                    if (box[i][j][k] == 1) {
                        q.offer(i);
                        q.offer(j);
                        q.offer(k);
                        q.offer(0);
                        visited[i][j][k] = true;
                    }


        int result = -1;
        //익어야되는 사과가 있는 경우
        if (need > 0) {
            while (!q.isEmpty()) {
                int b = q.poll(), r = q.poll(), c = q.poll(), count = q.poll();
                for (int i = 0; i < moves.length; i++) {
                    int nb = b + moves[i][0];
                    int nr = r + moves[i][1];
                    int nc = c + moves[i][2];
                    //범위를 벗어날때
                    if (nb < 0 || nr < 0 || nc < 0 || nb >= B || nr >= R || nc >= C) continue;
                    //이미 체크한 자리 일대
                    if (visited[nb][nr][nc]) continue;
                    //안익은 사과가 있는 자리가 아닐때
                    if (box[nb][nr][nc] != 0) continue;
                    visited[nb][nr][nc] = true;
                    need--;
                    q.offer(nb);
                    q.offer(nr);
                    q.offer(nc);
                    q.offer(count + 1);
                }
                //모든 사과가 익었을 경우
                if (need == 0) {
                    result = count + 1;
                    q.clear();
                }
            }
        }
        //익어야하는 사과가 없는 경우
        else result = 0;
        System.out.println(result);
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
