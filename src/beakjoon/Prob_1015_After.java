package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1015_After {
    static int N, P[];
    static Element[] B;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        B = new Element[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            B[i] = new Element();
            B[i].num = scan.nextInt();
            B[i].idx = i;
        }
    }

    static void solve() {
        Arrays.sort(B);
        for (int i = 0; i < N; i++) {
            P[B[i].idx] = i;
        }
    }

    public static void main(String[] args) {
        input();
        solve();
        for (int i : P) sb.append(i).append(" ");
        System.out.println(sb.toString());
    }

    static class Element implements Comparable<Element> {
        int num, idx;

        @Override
        public int compareTo(Element o) {
            return num - o.num;
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
