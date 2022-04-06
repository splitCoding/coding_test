package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1637 {
    static int N, numbers[][], total = 0, max = 0;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        numbers = new int[N][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                numbers[i][j] = scan.nextInt();
            }
            total += (numbers[i][1] - numbers[i][0]) / numbers[i][2] + 1;
            max = Math.max(max, numbers[i][1]);
        }
        System.out.println(total + " , " + max);
    }

    static boolean is_contain_odd(long num) {
        //num 보다 작거나 같은 숫자들의 갯수
        long count = 0;
        for (int i = 0; i < N; i++) {
            int A = numbers[i][0];
            int C = numbers[i][1];
            int B = numbers[i][2];
            //C 보다 num이 크거나 같은 경우
            if (num >= A) {
                if (num > C) {
                    count += (C - A) / B + 1;
                } else {
                    count += (num - A) / B + 1;
                }
            }
        }
        return count % 2 == 1;
    }

    static void solution() {
        if (total % 2 == 0) {
            System.out.println("NOTHING");
        } else {
            long L = 1, R = max, ret = 0;
            //모든 숫자의 갯수가 짝수 일떄는 NOTHING 출력
            while (L <= R) {
                long mid = (L + R) / 2;
                if (is_contain_odd(mid)) {
                    ret = mid;
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            }
            int count = 0;
            for (int i = 0; i < N; i++) {
                int A = numbers[i][0];
                int B = numbers[i][2];
                int C = numbers[i][1];
                //ret이 A보다 크거나 같고 C보다 작거나 같을 떄
                if (A <= ret && ret <= C) {
                    if ((ret - A) % B == 0) count++;
                }
            }
            System.out.println(ret + " " + count);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
