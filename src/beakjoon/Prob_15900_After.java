package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob_15900_After {
    static int N, ret;
    static List<Integer>[] nodes;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) nodes[i] = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int n1 = scan.nextInt(), n2 = scan.nextInt();
            nodes[n1].add(n2);
            nodes[n2].add(n1);
        }
    }

    static void dfs(int k, int prev, int depth) {
        if (nodes[k].size() == 1 && k != 1) ret += depth;
        for (int i : nodes[k]) {
            if (i == prev) continue;
            dfs(i, k, depth + 1);
        }
    }

    static void solution() {
        dfs(1, -1, 0);
        System.out.println((ret % 2 == 0) ? "No" : "Yes");
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
