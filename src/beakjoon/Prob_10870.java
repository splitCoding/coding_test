package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_10870 {
    static int N;

    static void input() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
    }

    static int fibonacci(int k) {
        if (k <= 1) return k;
        return fibonacci(k - 1) + fibonacci(k - 2);
    }

    static void solution() {
        System.out.println(fibonacci(N));
    }

    public static void main(String[] args) {
        input();
        solution();
    }
}
