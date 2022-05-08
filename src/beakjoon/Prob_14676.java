package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_14676 {
    static FastReader scan = new FastReader();
    static int N, M, K;
    static List<Integer>[] nodes;
    static int[] degree, exist, satisfaction, build_or_destroy;

    static void input() {
        N = scan.nextInt();
        M = scan.nextInt();
        K = scan.nextInt();
        degree = new int[N + 1];
        exist = new int[N + 1];
        satisfaction = new int[N + 1];
        make_nodes();
    }

    static void make_nodes() {
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            degree[n2]++;
        }
    }

    static void solution() {
        boolean possible = true;
        while (K-- > 0) {
            int c = scan.nextInt(), b = scan.nextInt();
            if (c == 1) {
                if (satisfaction[b] >= degree[b]) {
                    if (exist[b] == 0) for (int i : nodes[b]) satisfaction[i]++;
                    exist[b]++;
                } else {
                    possible = false;
                    break;
                }
            } else {
                if (exist[b] > 0) {
                    if (exist[b] == 1) for (int i : nodes[b]) satisfaction[i]--;
                    exist[b]--;
                }
                else {
                    possible = false;
                    break;
                }
            }
        }
        System.out.println((possible) ? "King-God-Emperor" : "Lier!");
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
