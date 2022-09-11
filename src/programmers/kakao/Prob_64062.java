package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/64062

public class Prob_64062 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution2(new int[]{2, 4, 5, 3, 2, 1, 4, 2, 5, 1}, 3));
        System.out.println(s.solution2(new int[]{10, 0, 0, 0, 10}, 3));
    }

    static class Solution {
        public int solution2(int[] stones, int max) {
            int left = 1, right = 200000000;
            int answer = 0;

            while (left <= right) {
                //건널 사람
                int mid = ( left + right ) / 2;

                //mid 만큼의 사람이 건널 수 있는 지 체크
                int disable = 0;
                for (int i = 0; i < stones.length; i++) {
                    if (stones[i] < mid){
                        disable++;
                    } else {
                        disable = 0;
                    }
                    if (disable == max) break;
                }

                //불가능 시 right 를 mid - 1로 줄인다.
                if (disable == max) right = mid -1;
                //가능할 시 answer 에 mid 를 저장하고 left 를 mid + 1 로 올린다.
                if (disable < max ) {
                    answer = mid;
                    left = mid + 1;
                }
            }

            return answer;
        }
    }
}



/* 1차 효율성 실패 코드 ( 이분 탐색에 대해서 떠올림 )

class Solution {
    public int solution(int[] stones, int max) {
        int zero = 0, answer = 0;

        while (zero < max) {
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] == 0) continue;
                if (stones[i] == 1) zero++;
                stones[i]--;
            }
            answer++;
        }

        All:
        while (true) {
            //연속으로 0이 max 개 나오면 끝
            int zeroCount = 0;
            for (int stone : stones) {
                if (stone == 0) {
                    zeroCount++;
                    if (zeroCount == max) break All;
                } else zeroCount = 0;
            }
            answer++;
            //0이 max개 나올때까지는 계속 줄이
            for (int i = 0; i < stones.length; i++) {
                if (stones[i] == 0) continue;
                stones[i]--;
            }
        }
        return answer;
    }

}
 */
