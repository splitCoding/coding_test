package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/12936

import java.util.Arrays;

public class How_to_line_up {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(20, s.factorial(20))));
    }

    static class Solution {
        long factorial(int k) {
            long ret = 1;
            for (int i = k; i > 0; i--) ret *= i;
            return ret;
        }

        int[] solution(int n, long k) {
            int[] answer = new int[n];
            boolean[] is_used = new boolean[n + 1];
            long now = k, divide = factorial(n - 1), count, order;
            for (int idx = 1; idx <= n; idx++) {
                count = order = (now - 1) / divide;
                for (int i = 1; i < is_used.length; i++) {
                    if (count == -1) break;
                    if (is_used[i]) continue;
                    if (count-- == 0) {
                        is_used[i] = true;
                        answer[idx - 1] = i;
                    }
                }
                if (idx < n) divide /= (n - idx);
                now -= factorial(n - idx) * order;
            }
            return answer;
        }
    }
}
