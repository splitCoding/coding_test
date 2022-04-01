package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_10816 {
    static StringBuilder sb = new StringBuilder();
    static int[] arr1, arr2;

    static void input() {
        FastReader scan = new FastReader();
        arr1 = new int[scan.nextInt()];
        for (int i = 0; i < arr1.length; i++) arr1[i] = scan.nextInt();
        arr2 = new int[scan.nextInt()];
        for (int i = 0; i < arr2.length; i++) arr2[i] = scan.nextInt();
    }

    static void solution() {
        HashMap<Integer, Integer> check = new HashMap<>(arr1.length);
        for (int i : arr1) check.merge(i, 1, (v1, v2) -> v1 + 1);
        for (int i : arr2) {
            if (check.containsKey(i)) sb.append(check.get(i)).append(" ");
            else sb.append(0).append(" ");
        }
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
