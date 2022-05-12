package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2579 {
    static int S;
    static int[] score;
    static int[][] result;

    static void input() {
        FastReader scan = new FastReader();
        S = scan.nextInt();
        score = new int[S + 1];
        for (int i = 1; i <= S; i++) {
            score[i] = scan.nextInt();
        }
    }

    static void solution1() {
        result = new int[S + 1][3];
        //안밟는 경우
        result[1][0] = 0;
        //연속두개를 밟는 경우
        result[1][1] = score[1];
        //처음으로 밟는 경우
        result[1][2] = score[1];

        for (int i = 2; i <= S; i++) {
            result[i][0] = Math.max(result[i - 1][1], result[i - 1][2]);
            result[i][1] = result[i - 1][2] + score[i];
            result[i][2] = result[i - 1][0] + score[i];
        }
        
        int answer = 0;
        for (int i : result[S]) answer = Math.max(answer, i);
        System.out.println(answer);
    }

    static void solution2() {
        result = new int[S + 1][2];
        //이전 계단을 안밟았을 떄
        result[1][0] = score[1];
        //이전 계단을 밟았을 때
        result[1][1] = score[1];
        for (int i = 2; i <= S; i++) {
            result[i][0] = Math.max(result[i - 2][0], result[i - 2][1]) + score[i];
            result[i][1] = result[i - 1][0] + score[i];
        }
        System.out.println(Math.max(result[S][0], result[S][1]));
    }

    public static void main(String[] args) {
        input();
        solution2();
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
