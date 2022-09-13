package programmers.kakao;

import java.util.*;

public class Prob_118667 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2}));
    }

    static class Solution {
        /*

        q1, q2 의 길이의 합 * 2 보다 작업을 더 하면 원래대로 돌아오기 때문에 그전까지만 반복
        - target 보다 합이 큰쪽에서 pop 해서 작은 쪽으로 push
        - 두 큐중 합이 target 면 반복문 끝

         */

        public int solution(int[] queue1, int[] queue2) {
            Q q1 = new Q(), q2 = new Q();
            for (int i : queue1) q1.push(i);
            for (int i : queue2) q2.push(i);

            //총 합을 반으로 나누지 못하는 경우 -1를 반환한다.
            if((q1.total + q2.total) % 2 == 1) return -1;

            //총합의 반
            long target = (q1.total + q2.total) / 2;

            int answer = -1;
            int count = 0;

            while (count <= (q1.q.size() + q2.q.size()) *  2) {
                //두 큐의 합이 같으면 break;
                if (q1.total == q2.total) {
                    answer = count;
                    break;
                }
                //총합이 더 큰 큐에서 반대 큐로 작업
                if (q1.total > target) {
                    q2.push(q1.pop());
                } else {
                    q1.push(q2.pop());
                }
                count++;
            }

            return answer;
        }

        class Q {
            long total = 0;
            Queue<Integer> q = new LinkedList<>();

            void push(int i) {
                q.add(i);
                total += i;
            }

            int pop() {
                int poll = q.poll();
                total -= poll;
                return poll;
            }
        }
    }
}
