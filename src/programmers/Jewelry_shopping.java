package programmers;

import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/67258

public class Jewelry_shopping {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] gem = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(s.solution(gem)));
        gem = new String[]{"AA", "AB", "AC", "AA", "AC"};
        System.out.println(Arrays.toString(s.solution(gem)));
        gem = new String[]{"XYZ", "XYZ", "XYZ"};
        System.out.println(Arrays.toString(s.solution(gem)));
        gem = new String[]{"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
        System.out.println(Arrays.toString(s.solution(gem)));
    }
    static class Solution {
        class Cart {
            int total;
            HashMap<String, Integer> items;

            Cart(HashSet<String> hs) {
                total = 0;
                items = new HashMap<>();
                for (String s : hs) items.put(s, 0);
            }

            void add(String item) {
                items.put(item, items.get(item) + 1);
                if (items.get(item) == 1) total++;
            }

            void remove(String item) {
                if (items.get(item) > 0) {
                    items.put(item, items.get(item) - 1);
                    if (items.get(item) == 0) total--;
                }
            }
        }

        public int[] solution(String[] gems) {
            HashSet<String> kind_of_gem = new HashSet<>();
            for (String s : gems) kind_of_gem.add(s);

            Cart c = new Cart(kind_of_gem);

            int left = 0, right = 0;
            int a_left = 1;
            int a_right = gems.length;
            int a_answer = gems.length;

            while (right < gems.length) {
                //모든 것들을 1개이상 가질 떄까지 right++
                while (right < gems.length && c.total < kind_of_gem.size()) {
                    c.add(gems[right]);
                    right++;
                }
                //모든 것들을 1개이상 가지지 않을 때까지 left++
                while (c.total == kind_of_gem.size()) {
                    c.remove(gems[left]);
                    left++;
                }

                //모든 것들을 1개씩만 다 담을 수 있는 경우가 있을 때
                if ((right - left + 1) == kind_of_gem.size()) {
                    a_left = left;
                    a_right = right;
                    break;
                }
                //그전 구간보다 짧은 구간을 찾았을 때
                if (a_answer > (right - left + 1)) {
                    a_answer = right - left + 1;
                    a_left = left;
                    a_right = right;
                }
            }
            return new int[]{a_left, a_right};
        }
    }
}

