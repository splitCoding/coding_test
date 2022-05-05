package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_9470 {
    static FastReader scan = new FastReader();
    static int T, K, M, P;
    static List<Integer>[] out;
    static int[] order, degree, count;
    static StringBuilder sb = new StringBuilder();


    static void input() {
        K = scan.nextInt();
        M = scan.nextInt();
        P = scan.nextInt();
        //���� ����� ������ �ε���
        out = new List[M + 1];
        for (int i = 0; i <= M; i++) out[i] = new ArrayList<>();
        //���� ����
        degree = new int[M + 1];
        //���� �迭
        order = new int[M + 1];
        //���� �ִ�������� ����
        count = new int[M + 1];
        for (int i = 0; i < P; i++) {
            int st = scan.nextInt(), dt = scan.nextInt();
            out[st].add(dt);
            degree[dt]++;
        }
    }

    static void solution() {
        Queue<Integer> q = new LinkedList<>();
        int mx = 0;
        //���� ������ 0�� ���� ť�� offer( ������ 1�� ���� ���� )
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 0) {
                q.offer(i);
                order[i] = count[i] = 1;
            }
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            mx = Math.max(mx, order[now]);
            for (int i : out[now]) {
                if (order[now] > order[i]) {
                    order[i] = order[now];
                    count[i] = 1;
                } else if (order[now] == order[i]) {
                    count[i]++;
                }
                degree[i]--;
                if (degree[i] == 0) {
                    if (count[i] > 1) order[i]++;
                    q.offer(i);
                }
            }
        }
        sb.append(mx).append("\n");
    }

    public static void main(String[] args) {
        T = scan.nextInt();
        for (int c = 1; c <= T; c++) {
            sb.append(c + " ");
            input();
            solution();
        }
        System.out.println(sb.toString());
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
