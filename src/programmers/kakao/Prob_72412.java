package programmers.kakao;

import java.util.*;

public class Prob_72412 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{
                        "java backend junior pizza 150",
                        "python frontend senior chicken 210",
                        "python frontend senior chicken 150",
                        "cpp backend senior pizza 260",
                        "java backend junior chicken 80",
                        "python backend senior chicken 50"
                },
                new String[]{
                        "java and backend and junior and pizza 100",
                        "python and frontend and senior and chicken 200",
                        "cpp and - and senior and pizza 250",
                        "- and backend and senior and - 150",
                        "- and - and - and chicken 100",
                        "- and - and - and - 150"
                })));
    }


    static class Solution {
        /*
        개발언어, 직군 항문, 경력, 선호하는 푸드
         */
        ArrayList<Integer> in = new ArrayList<>();

        public int[] solution(String[] info, String[] query) {
            int[] answer = new int[query.length];

            //[언어][직군][경력][소울푸드]
            Map<String, List<Integer>> m = new HashMap<>();

            //참가자들 맵으로 분류
            for (String s : info) {
                recursive(0, "", s.split("\\s"), m);
            }

            Collection<List<Integer>> values = m.values();
            for (List<Integer> value : values) {
                Collections.sort(value);
            }

            for (int i = 0; i < query.length; i++) {
                query[i] = query[i].replace(" and ", "");
                String key = query[i].split("\\s")[0];
                int score = Integer.parseInt(query[i].split("\\s")[1]);
                if (!m.containsKey(key)) {
                    answer[i] = 0;
                    continue;
                }
                answer[i] = find(m.get(key), score);
            }
            return answer;
        }

        void recursive(int depth, String key, String[] sp, Map<String, List<Integer>> m) {
            if (depth == 4) {
                in = new ArrayList<>();
                if (!m.containsKey(key)) {
                    m.put(key, in);
                }
                m.get(key).add(Integer.parseInt(sp[4]));
                return;
            }
            recursive(depth + 1, key + "-", sp, m);
            recursive(depth + 1, key + sp[depth], sp, m);
        }

        int find(List<Integer> list, int score) {
            int left = 0, right = list.size() - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (list.get(mid) >= score) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return list.size() - left;
        }
    }
}
