package programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/12927

import java.util.*;

public class Prob_12927 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, new int[]{4, 3, 3}));
        System.out.println(s.solution(1, new int[]{2, 1, 2}));
        System.out.println(s.solution(3, new int[]{1, 1}));
    }

    static class Solution {
        /*
        우선순위큐를 사용하여 문제 풀이
         */
        public long solution(int n, int[] works) {
            PriorityQueue<Integer> pq = new PriorityQueue<>(((o1, o2) -> o2 - o1));
            Arrays.stream(works).forEach(o -> pq.offer(o));
            for (int i = 0; i < n; i++) {
                //더 이상 남은 작업이 없을 경우
                if (pq.size() == 0) return 0;
                int time = pq.poll();
                if (--time == 0) continue;
                pq.offer(time);
            }

            long answer = 0;

            while (!pq.isEmpty()) {
                answer += Math.pow(pq.poll(), 2);
            }
            return answer;
        }
    }
}
