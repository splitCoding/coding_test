package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1181 {
    static int N;
    static List<String> words;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        words = new ArrayList<>(N);
        HashSet<String> set = new HashSet();
        for (int i = 0; i < N; i++) set.add(scan.next());
        words.addAll(set);
        words.sort((o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        for (String s : words) {
            sb.append(s).append('\n');
        }
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
