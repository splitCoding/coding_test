package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1015 {
    static int N, A[], sorted_A[], P[];
    static Hashtable<Integer, ArrayList<Integer>> A_hashtable;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        A = new int[N];
        P = new int[N];
        A_hashtable = new Hashtable(N);
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
        sorted_A = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted_A);
    }

    static void make_A_hashtable(int[] arr) {
        for (int i = 0; i < N; i++) {
            if (!A_hashtable.containsKey(arr[i])) A_hashtable.put(arr[i], new ArrayList<>(N));
            A_hashtable.get(arr[i]).add(i);
        }
    }

    static void get_P() {
        for (int i = 0; i < N; i++) P[i] = A_hashtable.get(A[i]).remove(0);
    }

    public static void main(String[] args) {
        input();
        make_A_hashtable(sorted_A);
        get_P();
        for (int i : P) sb.append(i).append(" ");
        System.out.println(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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

        String nextLine() {
            String ret = "";
            try {
                ret = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }
}
