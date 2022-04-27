package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_5639 {
    static List<Integer> tree = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String N = "";
        while (true) {
            try {
                N = bf.readLine();
                if (N == null || N.equals("")) break;
                tree.add(Integer.parseInt(N));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void solution2(int start, int end) {
        if (start >= end) return;
        int bd = end;
        for (int i = start + 1; i < end; i++) {
            if (tree.get(i) > tree.get(start)) {
                bd = i;
                break;
            }
        }
        solution2(start + 1, bd);
        solution2(bd, end);
        sb.append(tree.get(start) + "\n");
    }

    public static void main(String[] args) {
        input();
        solution2(0, tree.size());
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
