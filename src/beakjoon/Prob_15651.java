package beakjoon;

public class Prob_15651 {
    static int N, M;
    static int[] selected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        input();
        rec_func(1);
        System.out.println(sb.toString());
    }

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        M = scan.nextInt();
        selected = new int[M + 1];
    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i < M + 1; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand < N + 1; cand++) {
                selected[k] = cand;
                rec_func(k + 1);
                selected[k] = 0;
            }
        }

    }

}
