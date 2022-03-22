package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2470 {
    static int N;
    static int[] Liquids;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        Liquids = new int[N];
        for (int i = 0; i < N; i++) Liquids[i] = scan.nextInt();
    }

    static void solution() {
        //용액들 정렬
        Arrays.sort(Liquids);
        int[] ret = new int[2];
        //모두 산성용액 때
        if (Liquids[0] >= 0) {
            ret[0] = Liquids[0];
            ret[1] = Liquids[1];
        }
        //모두 알칼리용액일 때
        else if (Liquids[Liquids.length - 1] <= 0) {
            ret[0] = Liquids[Liquids.length - 2];
            ret[1] = Liquids[Liquids.length - 1];
        } else {
            int Left = 0, Right = N - 1;
            int min = Integer.MAX_VALUE;
            while (Left < Right) {
                int sum = Liquids[Left] + Liquids[Right];
                if (min > Math.abs(sum)) {
                    min = Math.abs(sum);
                    ret[0] = Liquids[Left];
                    ret[1] = Liquids[Right];
                    if (sum == 0) break;
                }
                if (sum < 0) {
                    Left++;
                } else {
                    Right--;
                }
            }
        }
        sb.append(ret[0]).append(" ").append(ret[1]);
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

        FastReader(String s) {
            try {
                bf = new BufferedReader(new FileReader(s));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
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

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
