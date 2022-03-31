package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1764 {
    static int N, M, count;
    static String[] never_heard, never_seen;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        never_heard = new String[N];
        M = scan.nextInt();
        never_seen = new String[M];
        for (int i = 0; i < N; i++) never_heard[i] = scan.next();
        for (int i = 0; i < M; i++) never_seen[i] = scan.next();
    }

    static void solution() {
        Arrays.sort(never_seen);
        Arrays.sort(never_heard);
        for (String name : never_heard) {
            int L = 0, R = M - 1;
            while (L <= R) {
                int mid = (L + R) / 2;
                if (never_seen[mid].compareTo(name) >= 0) {
                    if (never_seen[mid].equals(name)) {
                        sb.append(name).append('\n');
                        count++;
                        break;
                    }
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(count);
        System.out.println(sb.toString());
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
