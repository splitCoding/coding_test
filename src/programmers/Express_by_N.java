package programmers;

import java.util.HashSet;

public class Express_by_N {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, 12));
        System.out.println(s.solution(2, 11));
    }

    static class Solution {
        public int solution(int N, int number) {
            HashSet<Integer>[] dy = new HashSet[9];
            for (int i = 0; i < 9; i++) dy[i] = new HashSet<>();
            //연속으로 붙힌 숫자 추가
            for (int i = 1; i < 9; i++) {
                String num = "";
                for (int j = 1; j <= i; j++) num += N;
                dy[i].add(Integer.parseInt(num));
            }
            //1개로 만들 수 잇는 경우
            if (number == N) return 1;

            //8개 이내로 만들수 있는 경우
            for (int i = 2; i <= 8; i++) {
                //dy[4] = dy[1] 와 dy[3]의 조합
                //dy[4] = dy[2] 와 dy[2]의 조합
                //dy[4] = dy[3] 와 dy[1]의 조합
                for (int pre = 1; pre < i; pre++) {
                    for (int num : dy[pre]) {
                        for (int num2 : dy[i - pre]) {
                            dy[i].add(num + num2);
                            dy[i].add(num - num2);
                            dy[i].add(num * num2);
                            if (num2 != 0) dy[i].add(num / num2);
                            if (dy[i].contains(number)) return i;
                        }
                    }
                }
            }

            //8개 이내로 만들 수 없는 경우
            return -1;
        }
    }
}


