package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1562 {
    static int N;
    static int[][][] dy;
    static int divide = 1000000000;

    static void input() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        dy = new int[N + 1][10][1 << 10];
    }

    static void solution() {
        for (int i = 0; i < 10; i++) dy[1][i][1 << i] = 1;
        for (int len = 2; len <= N; len++) {
            for (int st = 0; st <= 9; st++) {
                for (int bit = 1; bit < (1 << 10); bit++) {
                    int check_bit = bit | (1 << st);
                    if (st != 0) {
                        dy[len][st][check_bit] += dy[len - 1][st - 1][bit] % divide;
                        dy[len][st][check_bit] %= divide;
                    }
                    if (st != 9) {
                        dy[len][st][check_bit] += dy[len - 1][st + 1][bit];
                        dy[len][st][check_bit] %= divide;
                    }
                }
            }
        }
    }

    static void show_result() {
        int sum = 0;
        for (int i = 1; i < 10; i++) {
            sum += dy[N][i][(1 << 10) - 1];
            sum %= divide;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        show_result();
    }
}
