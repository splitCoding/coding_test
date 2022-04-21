package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_14502 {
    static int N, M;
    static int[][] A;
    static int final_result = 0;
    static int[][] move = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static List<Integer[]> virus = new ArrayList<>();
    static List<Integer[]> empty = new ArrayList<>();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        A = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                A[i][j] = scan.nextInt();
                //비어있는 구역과 바이러스가 존재하는 구역을 미리 저장
                if (A[i][j] == 2) virus.add(new Integer[]{i, j});
                if (A[i][j] == 0) empty.add(new Integer[]{i, j});
            }
        }
    }

    static void find_unsafe_bfs() {
        //안전하지 않은 구역인지 체크하는 2차 배열
        boolean[][] check = new boolean[N][M];
        //벽인 구역을 안전하지 않은 구역으로 체크
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (A[i][j] == 1) check[i][j] = true;
        Queue<Integer[]> q = new LinkedList<>();
        //바이러스가 있는 구역을 시작으로 탐색
        for (int v_idx = 0; v_idx < virus.size(); v_idx++) {
            Integer[] vi = virus.get(v_idx);
            //이미 탐색한 구역일 경우 continue
            if (check[vi[0]][vi[1]]) continue;
            check[vi[0]][vi[1]] = true;
            q.offer(vi);
            while (!q.isEmpty()) {
                Integer[] v = q.poll();
                //바이러스가 퍼질수 있는 4가지 경우 탐색 (오른쪽, 왼쪽, 아래, 위 )
                for (int i = 0; i < move.length; i++) {
                    int nv0 = v[0] + move[i][0];
                    int nv1 = v[1] + move[i][1];
                    //연구실 영역을 나갈 경우, 방문한 구역일 경우
                    if (nv0 < 0 || nv1 < 0 || nv0 >= N || nv1 >= M || check[nv0][nv1]) continue;
                    check[nv0][nv1] = true;
                    q.offer(new Integer[]{nv0, nv1});
                }
            }
        }
        //false 인 구역을 카운트해서 안전한 구역 갯수 구함
        int result = 0;
        for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) if (!check[i][j]) result++;
        final_result = Math.max(final_result, result);
    }

    static void choose_wall_dfs(int k, int count) {
        //벽3개를 세운 뒤 안전한 구역의 갯수 구하기
        if (count == 3) {
            find_unsafe_bfs();
            return;
        }
        //더이상 비어있는 구역이 없을 때
        if (k == empty.size()) return;
        Integer[] w = empty.get(k);
        //k번째 비어있는 구역에 벽을 세운뒤 재귀
        A[w[0]][w[1]] = 1;
        choose_wall_dfs(k + 1, count + 1);
        //k번째 비어있는 구역에 세우지 않은 채 재귀
        A[w[0]][w[1]] = 0;
        choose_wall_dfs(k + 1, count);
    }

    static void solution() {
        choose_wall_dfs(0, 0);
        System.out.println(final_result);
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
