package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_9489_Re {
    static int n, k, answer;
    static int[] list, parent;
    static int target;


    static void input() {
        FastReader scan = new FastReader();
        while (true) {
            n = scan.nextInt();
            k = scan.nextInt();
            if (n == 0 && k == 0) break;
            list = new int[n];
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                list[i] = scan.nextInt();
                if (list[i] == k) target = i;
            }
            solution();
        }
    }

    static void solution() {
        //부모가 누군지
        answer = 0;
        parent[0] = -1;
        int parent_now = 0;
        for (int i = 1; i < list.length; i++) {
            int now = list[i];
            int prev = list[i - 1];
            if (now - prev == 1) {
                parent[i] = parent[i - 1];
            } else {
                parent[i] = parent_now++;
            }
        }
        int target_parent = parent[target];
        if (target_parent == -1) {
            answer = 0;
        } else if (target_parent == 0) {
            answer = -1;
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] == -1) answer++;
                else break;
            }
        } else {
            int target_grandpa = parent[parent[target]];
            for (int i = 0; i < list.length; i++) {
                int n_parent = parent[i];
                int n_grandpa = (n_parent == -1) ? -1 : parent[n_parent];
                if (n_grandpa != target_grandpa) continue;
                if (n_parent == target_parent) continue;
                answer++;
            }
        }
        show_result();
    }

    static void show_result() {
        System.out.println(answer);
    }

    public static void main(String[] args) {
        input();
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
