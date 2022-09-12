package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/72411

import java.util.*;

public class Prob_72411 {
    public static void main(String[] args) {
        int[] arr = {0,1,2,3};
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, 3);
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
        char[] a = new char[3];
        System.out.println(Arrays.toString(a));
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, new int[]{2, 3, 5})));
    }

    static class Solution {
        public String[] solution(String[] orders, int[] course) {
            Arrays.sort(orders);

            //가능한 모든 조합을 저장할 리스트
            Set<String> combinations = new HashSet<>();
            List<String> result = new ArrayList<>();

            for (String order : orders) {
                //order 로 만들 수 있는 모든 조합
                Set<String> comb = getCombinations(order);
                for (String combination : comb) {
                    combinations.add(combination);
                }
            }

            Map<Integer, List<Combination>> map = new HashMap<>();
            for (int i : course) {
                map.put(i, new ArrayList<>());
            }
            for (String combination : combinations) {
                int len = combination.length();
                //코스가 1개 이하로 이루어져 있거나 코스의 메뉴갯수가 원하는 갯수가 아닐 경우
                if (len < 2 || !map.containsKey(len)) continue;
                //시킨 사람이 2명 이상일 경우
                int howMany = findContains(combination, orders);
                if (howMany >= 2) {
                    map.get(combination.length()).add(new Combination(combination, howMany));
                }
            }

            //조합을 시킨 사람의 수를 기준으로 내림차순 정렬
            for (List<Combination> value : map.values()) {
                value.sort((o1, o2) -> o2.howMany - o1.howMany);
            }

            for (int i : course) {
                List<Combination> list = map.get(i);
                //아무 조합도 없을 경우
                if (list.size() == 0) continue;
                //제일 많이 시킨 조합들을 후보로 넣는다
                int count = list.get(0).howMany;
                for (Combination combination : list) {
                    if (combination.howMany < count) break;
                    result.add(combination.name);
                }
            }

            //알파벳을 기준으로 오름차순 정렬
            String[] answer = result.stream().toArray(String[]::new);
            Arrays.sort(answer);
            return answer;
        }

        class Combination {
            String name;
            int howMany;

            public Combination(String name, int howMany) {
                this.name = name;
                this.howMany = howMany;
            }
        }

        //해당 조합을 시킨 사람의 수를 반환
        int findContains(String combination, String[] orders) {
            int count = 0;
            for (String order : orders) {
                boolean able = true;
                for (int i = 0; i < combination.length(); i++) {
                    if (!order.contains(combination.charAt(i) + "")) {
                        able = false;
                        break;
                    }
                }
                if (able) count++;
            }
            return count;
        }

        //주문을 가지고 만들 수 있는 모든 조합을 반환
        Set<String> getCombinations(String order) {
            String[] menus = new String[order.length()];
            boolean[] isUsed = new boolean[order.length()];
            for (int i = 0; i < menus.length; i++) {
                menus[i] = order.charAt(i) + "";
            }
            //메뉴를 알파벳 기준 오름차순으로 정렬
            Arrays.sort(menus);

            Set<String> result = new HashSet<>();
            for (int i = 0; i < menus.length; i++) {
                //i부터 시작하여 만들 수 있는 모든 조합을 result에 저장
                recursive(result, menus, i, isUsed, "");
            }
            return result;
        }

        void recursive(Set<String> result, String[] menu, int start, boolean[] isUsed, String now) {
            //이미 만든 조합일 경우
            if (result.contains(now)) return;
            result.add(now);
            for (int i = start; i < menu.length; i++) {
                if (isUsed[i]) continue;
                isUsed[i] = true;
                recursive(result, menu, i, isUsed, now + menu[i]);
                isUsed[i] = false;
            }
        }
    }
}
