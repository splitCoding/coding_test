package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_20291 {
    static int N;
    static Hashtable<String, Integer> files;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        files = new Hashtable<>(N);
        for (int i = 0; i < N; i++) {
            files.merge(scan.next().split("\\.")[1], 1, (Integer v1, Integer v2) -> v1 + v2);
        }
    }

    static void solution() {
        String[] ext = files.keySet().toArray(new String[]{});
        Arrays.sort(ext);
        for (String e : ext) {
            sb.append(e).append(" ").append(files.get(e)).append('\n');
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(s));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

    }
}
