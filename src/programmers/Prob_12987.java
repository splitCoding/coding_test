package programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/12987

import java.util.Arrays;

public class Prob_12987 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
    }

    static class Solution {
        /*
        1. 두 배열을 오름차순으로 나열
        2. A배열의 제일 작은수를 이길 수 있는 B배열의 수가 나올때까지 진행
         */
        public int solution(int[] A, int[] B) {
            Arrays.sort(A);
            Arrays.sort(B);

            int aIdx = 0, bIdx = 0;
            int answer = 0;

            while (bIdx < B.length) {
                if (A[aIdx] < B[bIdx]) {
                    aIdx++;
                    answer++;
                }
                bIdx++;
            }

            return answer;
        }
    }
}
