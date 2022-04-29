package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_9489 {
    static FastReader scan = new FastReader();
    static int N, K, target, A[], P[], GP[];

    static void input() {
        A = new int[N];
        P = new int[N];
        GP = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = scan.nextInt();
            if (A[i] == K) target = i;
        }
    }


    static void solution() {
        int parent = 0;
        for (int i = 1; i < N; i++) {
            if (A[i - 1] + 1 == A[i]) {
                P[i] = P[i - 1];
            } else {
                P[i] = parent++;
            }
        }
        if (P[target] == 0) {
            System.out.println(0);
        } else {
            //부모, 조부모
            int t_p = P[target], t_gp = P[P[target]];
            int count = 0;
            for (int i = 0; i < N; i++) {
                //부모일 경우
                if (P[i] == t_gp) continue;
                //형제일 경우
                if (P[i] == t_p) continue;
                //사촌인 경우
                if (P[P[i]] == t_gp) count++;
            }
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        while (true) {
            N = scan.nextInt();
            K = scan.nextInt();
            if (N == 0 && K == 0) break;
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
