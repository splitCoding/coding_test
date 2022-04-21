package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2251 {
    static class Now {
        int[] b = new int[3];

        Now(int b1, int b2, int b3) {
            b[0] = b1;
            b[1] = b2;
            b[2] = b3;
        }

        Now Move(int from, int to, int limit) {
            int[] tmp_b = new int[3];
            System.arraycopy(b, 0, tmp_b, 0, 3);
            //옮길병에 남은 용량
            int left = limit - tmp_b[to];
            if (tmp_b[from] < left) {
                tmp_b[to] += tmp_b[from];
                tmp_b[from] = 0;
            } else {
                tmp_b[to] = limit;
                tmp_b[from] -= left;
            }
            return new Now(tmp_b[0], tmp_b[1], tmp_b[2]);
        }
    }

    static void bfs(Now n) {
        Queue<Now> q = new LinkedList<>();
        q.offer(n);
        check[n.b[0]][n.b[1]][n.b[2]] = true;
        while (!q.isEmpty()) {
            Now tmp = q.poll();
            if (tmp.b[0] == 0 && !result[tmp.b[2]]) result[tmp.b[2]] = true;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (i == j) continue;
                    Now next = tmp.Move(i, j, limit[j]);
                    if (check[next.b[0]][next.b[1]][next.b[2]]) continue;
                    check[next.b[0]][next.b[1]][next.b[2]] = true;
                    q.offer(next);
                }
            }
        }
    }

    static boolean[][][] check = new boolean[201][201][201];
    static boolean[] result = new boolean[201];
    static int[] limit = new int[3];

    static void input() {
        FastReader scan = new FastReader();
        for (int i = 0; i < 3; i++) limit[i] = scan.nextInt();
    }

    static void solution() {
        bfs(new Now(0, 0, limit[2]));
        for (int i = 0; i < result.length; i++) if (result[i]) System.out.print(i + " ");
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

