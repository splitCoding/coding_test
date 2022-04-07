package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1253 {
    static int N, a[], count;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        a = new int[N];
        for (int i = 0; i < N; i++) a[i] = scan.nextInt();
    }

    static void solution() {
        Arrays.sort(a);
        for (int i = 0; i < N; i++) {
            int L = 0, R = N - 1;
            while (L < R) {
                if (L == i) L++;
                else if (R == i) R--;
                else {
                    if (a[L] + a[R] > a[i]) R--;
                    else if (a[L] + a[R] == a[i]) {
                        count++;
                        break;
                    } else L++;
                }
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) {
        input();
        solution();
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
