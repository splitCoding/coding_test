package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_10816_2 {
    static StringBuilder sb = new StringBuilder();
    static int[] arr1, arr2;

    static void input() {
        FastReader scan = new FastReader();
        arr1 = new int[scan.nextInt()];
        for (int i = 0; i < arr1.length; i++) arr1[i] = scan.nextInt();
        arr2 = new int[scan.nextInt()];
        for (int i = 0; i < arr2.length; i++) arr2[i] = scan.nextInt();
    }

    static int find_first(int k) {
        int L = 0, R = arr1.length - 1;
        int ret = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr1[mid] >= k) {
                ret = mid;
                R = mid - 1;
            } else L = mid + 1;
        }
        return ret;
    }

    static int find_last(int k) {
        int L = 0, R = arr1.length - 1;
        int ret = -1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr1[mid] <= k) {
                ret = mid;
                L = mid + 1;
            } else R = mid - 1;
        }
        return ret;
    }

    static void solution() {
        Arrays.sort(arr1);
        for (int num : arr2) {
            int first = find_first(num);
            int last = find_last(num);
            int answer = (first == -1 || last == -1) ? 0 : last - first + 1;
            sb.append(answer).append(" ");
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
