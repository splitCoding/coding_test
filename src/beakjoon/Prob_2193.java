package beakjoon;

import java.io.*;

public class Prob_2193 {
    static int N;
    static long[][] dy;

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void solution() {
        //dy[i][j] = i자리에 j로 끝나는 이친수의 갯수
        dy = new long[N + 1][2];
        if (N > 0) {
            dy[1][0] = 0;
            dy[1][1] = 1;
        }
        if (N > 1) {
            dy[2][0] = dy[1][1];
            dy[2][1] = dy[1][0];
            for (int i = 3; i <= N; i++) {
                dy[i][0] = dy[i - 1][0] + dy[i - 1][1];
                dy[i][1] = dy[i - 1][0];
            }
        }
    }

    static void show_result() {
        long sum = 0;
        for (long i : dy[N]) sum += i;
        System.out.println(sum);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
