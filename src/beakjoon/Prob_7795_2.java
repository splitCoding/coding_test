package beakjoon;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob_7795_2 {
    static int T, A[][], B[][];
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        T = scan.nextInt();
        A = new int[T][];
        B = new int[T][];
        for (int i = 0; i < T; i++) {
            A[i] = new int[scan.nextInt()];
            B[i] = new int[scan.nextInt()];
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = scan.nextInt();
            }
            for (int j = 0; j < B[i].length; j++) {
                B[i][j] = scan.nextInt();
            }
        }
    }

    static void solution() {
        for (int[] arr : A) Arrays.sort(arr);
        for (int[] arr : B) Arrays.sort(arr);
        for (int idx = 0; idx < T; idx++) {
            int[] A_now = A[idx], B_now = B[idx];
            //A의 제일 큰값이 B의 제일 작은 값보다 작거나 같을 때
            if (A_now[A_now.length - 1] <= B_now[0]) {
                sb.append(0).append("\n");
                continue;
            }
            //A의 제일 작은 값이 B의 제일 큰 값보다 클 때
            if (A_now[0] > B_now[B_now.length - 1]) {
                sb.append(A_now.length * B_now.length).append("\n");
                continue;
            }
            int count = 0;
            for (int a = 0; a < A_now.length; a++) {
                //비의 제일 작은 값이 A의 현재 값보다 크거나 같을 때
                if (A_now[a] <= B_now[0]) continue;
                //A의 현재 값이 B의 제일 큰 값보다 클때
                else if (A_now[a] > B_now[B_now.length - 1]) {
                    count += (A_now.length - a) * B_now.length;
                    break;
                }
                for (int b = 0; b < B_now.length; b++) {
                    if (A_now[a] <= B_now[b]) {
                        count += b;
                        break;
                    }
                }
            }
            sb.append(count).append('\n');
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
