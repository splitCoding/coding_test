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
            //���ϸ� ���� ����ִ��� ����
            for (int num : nums) {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            }
            //���ϸ� �� ������ �Ѹ������� �ݺ��� ���ų� ������ ��� ���� ����
            if(map.keySet().size() <= nums.length / 2){
                answer = map.keySet().size();
            } else {
                answer = nums.length / 2;
            }
            return answer;
        }
    }
}
