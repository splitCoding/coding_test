package programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/1845

import java.util.HashMap;

public class PocketMon {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{3, 1, 2, 3}));
        System.out.println(s.solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(s.solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

    static class Solution {
        public int solution(int[] nums) {
            int answer = 0;
            HashMap<Integer, Integer> map = new HashMap<>();
            //포켓몬 별로 몇마리있는지 정렬
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
            //포켓몬 의 종류가 총마리수의 반보다 적거나 같으면 모든 종류 가능
            if(map.keySet().size() <= nums.length / 2){
                answer = map.keySet().size();
            } else {
                answer = nums.length / 2;
            }
            return answer;
        }
    }
}
