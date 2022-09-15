package programmers.kakao;

import java.util.Arrays;

public class Prob_77484 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
    }

    static class Solution {
        /*

        로또의 일부번호를 알수 없을때 최고, 최저 순위를 구하여라
        알아볼 수 없는 번호 = 0

        */
        public int[] solution(int[] lottos, int[] win_nums) {
            int count = 0;
            int undefined = 0;
            for (int i : lottos) {
                if (i == 0) {
                    undefined++;
                    continue;
                }
                for (int win_num : win_nums) {
                    if (i == win_num) count++;
                }
            }
            int[] answer = new int[2];
            if (undefined == 0) {
                answer[0] = Math.min(7 - count, 6);
                answer[1] = Math.min(7 - count, 6);
            } else {
                answer[0] = 7 - (count + undefined);
                answer[1] = Math.min(6, 7 - count);
            }

            return answer;
        }
    }
}
