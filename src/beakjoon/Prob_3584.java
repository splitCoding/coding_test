package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_3584 {
    static FastReader scan = new FastReader();
    static int N, n1, n2;
    static List<Integer> n1_p, n2_p;
    static int[] nodes;

    static void input() {
        N = scan.nextInt();
        nodes = new int[N + 1];
        for (int i = 0; i < N - 1; i++) {
            int p = scan.nextInt(), c = scan.nextInt();
            nodes[c] = p;
        }
        n1 = scan.nextInt();
        n2 = scan.nextInt();
        n1_p = new ArrayList<>();
        n2_p = new ArrayList<>();
    }

    static void dfs(List l, int k) {
        if (k == 0) return;
        l.add(k);
        dfs(l, nodes[k]);
    }

    public static void main(String[] args) {
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            dfs(n1_p, n1);
            int k = n2;
            while (k != 0) {
                if (n1_p.contains(k)) {
                    System.out.println(k);
                    break;
                } else {
                    k = nodes[k];
                }
            }
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
