package programmers;

import java.util.Arrays;
import java.util.Comparator;

public class Prob_42884 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}}));
    }

    static class Solution2 {
        public int solution(int[][] routes) {
            int answer = 0;
        /*
        1. 차가 고속도로를 나간 순으로 정렬한다.
        2. 첫 차부터 나가는 지점에 카메라를 설치하고 checked 에 설치 지점을 대입한다.
        3. if ( 다음 차의 고속도로 시작 지점 < checked ){ 이전에 설치된 카메라에 같이 잡히게 된다. }
         */

            // 1. 차가 고속도로를 나간 순으로 정렬한다.
            Arrays.sort(routes, Comparator.comparingInt(o -> o[1]));

            // checked : 이전 카메라가 설치된 지점을 넣을 변수
            int checked = Integer.MIN_VALUE;

            for (int[] route : routes) {
                //start : 현재 차량이 고속도로를 시간한 지점
                int start = route[0];

                if (start <= checked) {
                    //이전에 설치된 카메라보다 start 가 이전이나 같으므로 카메라에 같이 찍히게 되니 다음 차량으로 넘어간다.
                    continue;
                } else {
                    //이전에 설치된 카메라보다 start 가 나중이므로 카메라에 같이 찍힐 수 없어 새로운 카메라를 설치하고 설치된 지점을 저장한다.
                    checked = route[1];
                    answer++;
                }
            }
            return answer;
        }
    }

}
