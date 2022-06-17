package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob_14888 {
    private static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(bf.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }

    static int N;
    static int[] numbers, ops;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        rec_func(1, numbers[0]);
        sb.append(MAX).append('\n').append(MIN);
        System.out.println(sb.toString());
    }

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        numbers = new int[N];
        ops = new int[4];
        for (int i = 0; i < N; i++) numbers[i] = scan.nextInt();
        for (int i = 0; i < 4; i++) ops[i] = scan.nextInt();
    }

    static int calculate(int num1, int op, int num2) {
        if (op == 0) {
            return num1 + num2;
        } else if (op == 1) {
            return num1 - num2;
        } else if (op == 2) {
            return num1 * num2;
        } else {
            return num1 / num2;
        }
    }

    static void rec_func(int k, int result) {
        if (k == N) {
            if( MAX < result || MIN > result){
                MIN = Math.min(MIN, result);
                MAX = Math.max(MAX, result);
            }
        } else {
            for (int i = 0; i < ops.length; i++) {
                if (ops[i] > 0) {
                    ops[i]--;
                    rec_func(k + 1, calculate(result, i, numbers[k]));
                    ops[i]++;
                }
            }
        }
    }
}

