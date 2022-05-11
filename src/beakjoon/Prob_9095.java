package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_9095 {
    static int T;
    static int[] nums;
    static HashSet<String>[] result;

    static void input() {
        FastReader scan = new FastReader();
        T = scan.nextInt();
        nums = new int[T];
        result = new HashSet[12];
        for (int i = 1; i <= 11; i++) result[i] =  new HashSet<>();
        for (int i = 0; i < T; i++) nums[i] = scan.nextInt();
    }

    static void solution() {
        for (int i = 1; i <= 3; i++) result[i].add(i + "");
        int idx = 2;
        while (idx <= 11) {
            int idx1 = 1;
            while (idx1 < idx) {
                for (String s1 : result[idx1]) {
                    for (String s2 : result[idx- idx1]) {
                        result[idx].add(s1 + s2);
                    }
                }
                idx1++;
            }
            idx++;
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        for(int i : nums) System.out.println(result[i].size());
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

