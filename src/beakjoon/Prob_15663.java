package beakjoon;

import java.util.*;
import java.io.*;

public class Prob_15663 {
    static int N, M, numbers[], selected[];
    static boolean[] used;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        numbers = new int[N];
        selected = new int[M];
        used = new boolean[N];
        for (int i = 0; i < N; i++) numbers[i] = scan.nextInt();
    }

    public static void main(String[] args) {
        input();
        Arrays.sort(numbers);
        rec_func(0);
        System.out.println(sb.toString());
    }

    static void rec_func(int k) {
        if (k == M) {
            for (int i : selected) sb.append(i).append(' ');
            sb.append('\n');
        } else {
            int pre_selected = 0;
            for (int i = 0; i < N; i++) {
                if (!used[i] && pre_selected != numbers[i]) {
                    pre_selected = numbers[i];
                    selected[k] = numbers[i];
                    used[i] = true;
                    rec_func(k + 1);
                    used[i] = false;
                }
            }
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

        String nextLine() {
            String ret = "";
            try {
                ret = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }
}
