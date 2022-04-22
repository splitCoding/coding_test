package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1697 {
    static int N, K, result = Integer.MAX_VALUE;
    static boolean[] visit = new boolean[100001];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); //0 ≤ N ≤ 100,000
        K = scan.nextInt(); //0 ≤ K ≤ 100,000
    }

    static void bfs(int time, int now) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{now, time});
        visit[now] = true;
        while (!q.isEmpty()) {
            int[] aq = q.poll();
            int n = aq[0];
            int t = aq[1];

            if (n == K) {
                //최단 시간을 찾았음으로 반복문 탈출
                result = Math.min(result, t);
                q.clear();
            }
            //n이 0보다 클때, 탐색하지 않은 위치일때만 n-1진행
            if (n > 0 && !visit[n - 1]) {
                visit[n - 1] = true;
                q.offer(new int[]{n - 1, t + 1});
            }
            //n*2가 100000보다 작을때, 탐색하지 않은 위치일때만 n*2 진행
            if (n * 2 <= 100000 && !visit[n * 2]) {
                visit[n * 2] = true;
                q.offer(new int[]{n * 2, t + 1});
            }
            //n이 K보다 작을때, 탐색하지 않은 위치일때만 n+1 진행
            if (n < K && !visit[n + 1]) {
                visit[n + 1] = true;
                q.offer(new int[]{n + 1, t + 1});
            }

        }
    }

    static void solution() {
        bfs(0, N);
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
