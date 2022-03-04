package beakjoon;

import java.util.*;

public class Prob_15970 {
    static int N, total;
    static Hashtable<Integer, List<Integer>> points_hashtable;

    static void input() {
        int x, color;
        FastReader scan = new FastReader();
        N = scan.nextInt();
        points_hashtable = new Hashtable<>(N);
        for (int i = 0; i < N; i++) {
            x = scan.nextInt();
            color = scan.nextInt();
            if (!points_hashtable.containsKey(color)) {
                points_hashtable.put(color, new ArrayList<>(N));
                points_hashtable.get(color).add(x);
            } else {
                points_hashtable.get(color).add(x);
            }
        }
    }

    static void solve() {
        for (List<Integer> same_color : points_hashtable.values()) {
            Collections.sort(same_color);
            for (int i = 0; i < same_color.size(); i++) {
                if (i == 0) {
                    total += same_color.get(i + 1) - same_color.get(i);
                } else if (i == same_color.size() - 1) {
                    total += same_color.get(i) - same_color.get(i - 1);
                } else {
                    int case1 = same_color.get(i) - same_color.get(i - 1);
                    int case2 = same_color.get(i + 1) - same_color.get(i);
                    total += Math.min(case1, case2);
                }
            }
        }
    }

    public static void main(String[] args) {
        input();
        solve();
        System.out.println(total);
    }
}
