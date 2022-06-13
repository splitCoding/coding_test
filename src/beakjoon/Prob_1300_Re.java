package beakjoon;

import java.io.*;

public class Prob_1300_Re {
    static long N, k;
    static long result = 0;

    static void input() throws IOException{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(bf.readLine());
        k = Long.parseLong(bf.readLine());
    }

    static long how_many(long num) {
        long count = 0;
        for (int i = 1; i <= N; i++) {
            count += Math.min(N, num / i);
        }
        return count;
    }

    static void solution() {
        long L = 1, R = Math.min(1000000000, N * N);
        while (L <= R) {
            long mid = (L + R) / 2;
            if (how_many(mid) >= k) {
                result = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
    }

    static void show_result() {
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        input();
        if (k == 0) {
            System.out.println(1);
        } else {
            solution();
            show_result();
        }
    }
}
