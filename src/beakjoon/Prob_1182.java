package beakjoon;

public class Prob_1182 {
    static int N, S, count, numbers[];

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        S = scan.nextInt();
        numbers = new int[N];
        for (int i = 0; i < N; i++) numbers[i] = scan.nextInt();
    }

    static void rec_func(int k, int result) {
        if (k == N) {
            if (result == S) count++;
        } else {
            rec_func(k + 1, result + numbers[k]);
            rec_func(k + 1, result);
        }
    }

    public static void main(String[] args) {
        input();
        if (S == 0) count = -1;
        rec_func(0, 0);
        System.out.println(count);
    }
}
