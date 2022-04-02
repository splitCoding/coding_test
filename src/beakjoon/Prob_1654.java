package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1654 {
    static int K, N;
    static int wires[];
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        K = scan.nextInt();
        N = scan.nextInt();
        wires = new int[K];
        for (int i = 0; i < K; i++) wires[i] = scan.nextInt();
    }

    static long can_make(long k) {
        long count = 0;
        for (int i : wires) count += i / k;
        return count;
    }

    static void solution() {
        Arrays.sort(wires);
        long ret = 0;
        long L = 1, R = wires[wires.length-1];
        while (L <= R) {
            long mid = (L + R) / 2;
            if (can_make(mid) < N) {
                R = mid - 1;
            } else {
                ret = mid;
                L = mid + 1;
            }
        }
        sb.append(ret);
    }

    public static void main(String[] args) {
        input();
        solution();
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

    }
}
