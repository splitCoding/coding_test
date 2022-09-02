package programmers;

import java.util.*;

public class Prob_12906 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new int[]{1, 1, 3, 3, 0, 1, 1})));
        System.out.println(Arrays.toString(s.solution(new int[]{4, 4, 4, 3, 3})));
    }

    static class Solution {
        public int[] solution(int[] arr) {
            List<Integer> list = new ArrayList();
            list.add(arr[0]);
            int before = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] == before) {
                    continue;
                } else {
                    list.add(arr[i]);
                    before = arr[i];
                }
            }
            return list.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
