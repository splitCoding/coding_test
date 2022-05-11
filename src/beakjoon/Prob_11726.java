package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11726 {
    static int n;
    static int[] result;

    static void input() {
        FastReader scan = new FastReader();
        n = scan.nextInt();
        result = new int[n + 1];
    }

    static void solution() {
        result[0] = 0;
        result[1] = 1;
        result[2] = 2;
        for (int i = 3; i <= n; i++) {
            result[i] = ( result[i-1] + result[i - 2] ) % 10007;
        }
    }

    public static void main(String[] args) {
        input();
        if (n < 3) {
            System.out.println(n);
        } else {
            solution();
            System.out.println(result[n]);
        }
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
