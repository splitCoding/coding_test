package programmers;

import java.util.*;

public class Prob_42862 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, new int[]{2, 4}, new int[]{1, 3, 5}));
        System.out.println(s.solution(3, new int[]{3}, new int[]{1}));
    }

    static class Solution {
        public int solution(int n, int[] lost, int[] reserve) {
            int answer = 0;

            //앞에부터
            int[] have = new int[n];
            Arrays.fill(have, 1);
            for (int i : lost) have[i - 1]--;
            for (int i : reserve) have[i - 1]++;

            for (int i = 0; i < have.length; i++) {
                if (have[i] > 0) continue;
                if (i != 0 && have[i - 1] == 2) {
                    have[i]++;
                    have[i - 1]--;
                } else if (i != have.length - 1 && have[i + 1] == 2) {
                    have[i]++;
                    have[i + 1]--;
                }
            }
            for (int i : have) {
                if (i > 0) answer++;
            }
            return answer;
        }
    }
}
