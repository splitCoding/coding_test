package beakjoon;

import java.io.*;

public class Prob_1637_Re {
    static class Case {
        int a, b, c;

        Case(String s) {
            String[] arr = s.split(" ");
            this.a = Integer.parseInt(arr[0]);
            this.b = Integer.parseInt(arr[1]);
            max = Math.max(max, this.b);
            this.c = Integer.parseInt(arr[2]);
        }
    }

    static int N;
    static int max = 0;
    static Case[] cases;

    static void input() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        cases = new Case[N];
        for (int i = 0; i < N; i++) {
            cases[i] = new Case(bf.readLine());
        }
    }

    static long count_between(int end) {
        long count = 0;
        for (Case s : cases) {
            if (s.a > end) continue;
            count += (Math.min(end, s.b) - s.a) / s.c + 1;
        }
        return count;
    }

    static int how_many(int k) {
        int count = 0;
        for (Case s : cases) {
            if (k < s.a) continue;
            if (k > s.b) continue;
            if ((k - s.a) % s.c == 0) count++;
        }
        return count;
    }

    static void solution() {
        long num = 0;
        long L = 1, R = max;
        while (L <= R) {
            long mid = (L + R) / 2;
            if (count_between((int) mid) % 2 == 1) {
                num = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        System.out.println(num + " " + how_many((int)num));
    }

    public static void main(String[] args) throws IOException {
        input();
        if (count_between(max) % 2 == 0) {
            System.out.println("NOTHING");
        } else {
            solution();
        }
    }
}
