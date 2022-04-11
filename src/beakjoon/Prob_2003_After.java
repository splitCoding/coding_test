package beakjoon;

import java.io.*;
import java.util.StringTokenizer;

public class Prob_2003_After {
    static int N, M, A[], sum = 0, count = 0;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt(); //배열의 길이, 1만 이하
        M = scan.nextInt(); //목표값, 3억 이하
        A = new int[N]; //배열, 요소들 3만 이하
        for (int i = 0; i < N; i++) A[i] = scan.nextInt();
    }

    static void solution() {
        //배열에서 합이 M이 나오는 부분배열의 갯수를 구하라
        for (int L = 0, R = 0; L < N; L++) {
            while (R < N && sum < M) sum += A[R++];
            if (sum  == M) count++;
            sum -= A[L];
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
