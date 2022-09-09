package programmers;


//https://school.programmers.co.kr/learn/courses/30/lessons/12938

import java.util.Arrays;

public class Prob_12938 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(10, 101)));
        System.out.println(Arrays.toString(s.solution(2, 1)));
        System.out.println(Arrays.toString(s.solution(2, 8)));
    }

    static class Solution {
        int[] ret;
        int result;

        /*

        1. 무식하게 재귀를 통하여 풀어보기 ( 시간 초과 )

        2. 분배해야될 숫자를 분배해야될 갯수로 나눈값을 순서대로 넣는게 최대값이다.

         */

        public int[] solution(int n, int s) {
            int[] ret = new int[n];
            int retIdx = 0;
            //s가 n으로 나뉠 때 ( 모든 배열을 s / n으로 채워서 반환 )
            if (s % n == 0) {
                Arrays.fill(ret, s / n);
            } else {
                int idx = 0;
                //num : 분배 해야될 숫자
                //left : 분배가 필요한 숫자 갯수
                int num = s, left = n;
                //분배 해야될 숫자가 남아있을 때까지 반복
                while (left > 0) {
                    ret[idx++] = num / left;
                    num -= num / left;
                    left--;
                }
            }
            return (ret[0] == 0) ? new int[]{-1} : ret;

        }

        public int[] failure1(int n, int s) {
            int[] isUsed = new int[s];
            result = 0;
            ret = new int[n];
            recursive(0, 0, 1, isUsed, n, s);
            return (ret[0] == 0) ? new int[]{-1} : ret;
        }


        void recursive(int used, int sum, int multi, int[] isUsed, int maxUse, int max) {
            //maxUse 개를 사용하여 더한값이 max 인 경우
            if (sum == max && used == maxUse) {
                int retIdx = 0;
                if (multi > result) {
                    result = multi;
                    for (int i = 0; i < isUsed.length; i++) {
                        if (isUsed[i] == 0) continue;
                        for (int j = 0; j < isUsed[i]; j++) {
                            ret[retIdx++] = i;
                        }
                    }
                }
                return;
            }
            //maxUse 보다 더 많은 숫자를 사용했거나 더한값이 max 를 넘는경우
            if (used > maxUse || sum > max) {
                return;
            }
            for (int i = 1; i < max; i++) {
                isUsed[i]++;
                recursive(used + 1, sum + i, multi * i, isUsed, maxUse, max);
                isUsed[i]--;
            }
        }
    }
}
