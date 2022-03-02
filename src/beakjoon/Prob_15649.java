package beakjoon;

public class Prob_15649 {
    static int N, M;
    static int[] selected;
    static boolean[] is_used;
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
        is_used = new boolean[N + 1];
    }

    static void rec_func(int k) {
        if (k == M + 1) {
            for (int i = 1; i < M + 1; i++) sb.append(selected[i]).append(' ');
            sb.append('\n');
        } else {
            for (int cand = 1; cand < N + 1; cand++) {
                if (!is_used[cand]) {
                    is_used[cand] = true;
                    selected[k] = cand;
                    rec_func(k + 1);
                    is_used[cand] = false;
                    selected[k] = 0;
                }
            }
        }

    }
}
