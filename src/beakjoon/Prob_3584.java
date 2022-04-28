package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3584 {
    static FastReader scan = new FastReader();
    static int N, n1, n2;
    static boolean[] check;
    static int[] nodes;

    static void input() {
        N = scan.nextInt();
        nodes = new int[N + 1];
        check = new boolean[N + 1];
        for (int i = 0; i < N - 1; i++) {
            int p = scan.nextInt(), c = scan.nextInt();
            nodes[c] = p;
        }
        n1 = scan.nextInt();
        n2 = scan.nextInt();
    }

    static void solution() {
        int i = n1;
        while (i > 0) {
            check[i] = true;
            i = nodes[i];
        }
        i = n2;
        while (i > 0) {
            if (check[i]) {
                System.out.println(i);
                break;
            } else {
                i = nodes[i];
            }
        }
    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            solution();
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
