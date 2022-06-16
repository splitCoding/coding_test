package beakjoon;

import java.io.*;
import java.util.*;

//백준 문제 풀이용 템플릿

public class Main {
    static void input() {
        FastReader scan = new FastReader();
    }

    static void solution() {

    }

    static void show_result() {

    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }

    private static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(bf.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
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
