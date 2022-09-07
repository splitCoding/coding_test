package programmers;

import java.util.*;

public class Prob_42885 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{70, 50, 80, 50}, 100));
        System.out.println(s.solution(new int[]{70, 80, 50}, 100));
    }

    static class Solution {
        public int solution(int[] people, int limit) {
            /*
                구하지 않은 인원 중 제일 무거운 사람을 태우고 남은 자리에 제일 가벼운 순으로 한계가 넘지 않게 채우는 과정을 반복한다.
             */
            Arrays.sort(people);
            int answer = 0, saved = 0;
            int smallest = 0, biggest = people.length - 1;

            //while(smallest <= biggest) 의 조건으로도 가능하다. 모든인원이 구해질때 까지가 이해하기 더 쉬울것 같아 아래의 조건으로 하였다.
            while (saved < people.length) {
                //people[biggest] 를 보트에 태운다
                saved++;

                //남은 제한
                int left = limit - people[biggest];

                /*
                제한을 넘지 않을 때 까지 people[smallest] 를 보트에 태운다
                smallest == biggest 일때는 biggest 를 제외한 모두를 구한 경우이다.
                 */
                int total = 0;
                while(smallest < biggest){
                    //limit 을 초과할 경우
                    if(total + people[smallest] > left) break;
                    total += people[smallest];
                    saved++;
                    smallest++;
                }

                //보트하나가 출발했으니 answer++;
                answer++;
                //biggest 를 구했으니 제외한 제일 무거운 사람으로 이동
                biggest--;
            }
            return answer;
        }
    }
}
