package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Prob_1309 {
    static int N;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            N = Integer.parseInt(bf.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void base_solution() {
        int[][] dy = new int[N + 1][3];
        //[i][0] = i번째 0번 칸에 사자를 가둘때 만들수 잇는 경우의 수
        //[i][1] = i번째 1번 칸에 사자를 가둘때 만들수 잇는 경우의 수
        //[i][2] = i번째 칸이 있을 때 만들 수 있는 모든 경우의 수
        dy[1][0] = 1;
        dy[1][1] = 1;
        dy[1][2] = 3; //사자가 모두 없는 경우도 인정
        for (int i = 2; i <= N; i++) {
            dy[i][0] = dy[i - 1][2] - dy[i - 1][0];
            dy[i][1] = dy[i - 1][2] - dy[i - 1][1];
            dy[i][2] = dy[i][0] + dy[i][1] + dy[i - 1][2];
        }
        //진행은 가능하지만 숫자가 너무커짐
    }

    static void solution() {
        int[] dy = new int[N + 1];
        dy[0] = 1;
        dy[1] = 3;
        for (int i = 2; i < dy.length; i++) dy[i] = (dy[i - 1] * 2 + dy[i - 2]) % 9901;
        sb.append(dy[N]);
    }

    static void show_result() {
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        show_result();
    }
}
