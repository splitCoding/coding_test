package beakjoon;

import java.io.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class Prob_20291_After {
    static int N;
    static String[] files;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        files = new String[N];
        for (int i = 0; i < N; i++) {
            files[i] = scan.next().split("\\.")[1];
        }
    }

    static void solution() {
        if (files.length == 1) {
            System.out.println(files[0] + " " + 1);
        } else {
            Arrays.sort(files);
            int count = 1;
            for (int i = 1; i < files.length; i++) {
                if (files[i].equals(files[i - 1])) {
                    count++;
                    if (i == files.length - 1) sb.append(files[i]).append(" ").append(count);
                } else {
                    sb.append(files[i - 1]).append(" ").append(count).append('\n');
                    count = 1;
                    if (i == files.length - 1) sb.append(files[i]).append(" ").append(1);
                }
            }
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
