package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_16472 {
    static int N, alphabet[], using = 0, answer = 1;
    static String str;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        str = scan.next();
        alphabet = new int[26];
    }

    static boolean add(int idx) {
        boolean ret = false;
        //사용중인 알파벳이 N보다 작을 경우
        if (using < N) {
            //처음 사용하는 알파벳일 경우
            if (alphabet[idx] == 0) using++;
            alphabet[idx]++;
            ret = true;
        }
        //N개 사용한 상태에서 이미사용된 알파벳이 사용될 때
        else if (using == N && alphabet[idx] > 0) {
            alphabet[idx]++;
            ret = true;
        }
        return ret;
    }

    static void remove(int idx) {
        alphabet[idx]--;
        //해당 알파벳 사용수가 0이 된 경우
        if (alphabet[idx] == 0) using--;
    }

    static void solution() {
        for (int L = 0, R = 0; L < str.length(); L++) {
            while (R < str.length() && add(str.charAt(R) - 'a')) R++;
            answer = Math.max(answer, R - L);
            remove(str.charAt(L) - 'a');
        }
        System.out.println(answer);
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
