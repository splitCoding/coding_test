package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1806 {
    static int N, S, arr[], total = 0, answer = 0;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scan.nextInt();
            total += arr[i];
        }
    }

    static void solution() {
        if (total < S) {
            System.out.println(0);
        } else if (total == S) {
            System.out.println(N);
        } else {
            int R = 0, sum = 0, answer = N;
            for (int L = 0; L < N; L++) {
                while (sum < S && R < N) {
                    sum += arr[R];
                    R++;
                }
                if (sum >= S) {
                    answer = Math.min(answer, R - L);
                }
                //다음 숫자부터 더한값을 구하기 위해 첫번째 숫자를 빼준다.
                sum -= arr[L];
            }
            System.out.println(answer);
        }
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
