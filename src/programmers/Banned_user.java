package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/64064

import java.util.*;

public class Banned_user {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"frodo", "fradi", "crodo", "abc123", "frodoc"},
                new String[]{"*rodo", "*rodo", "******"}));
    }

    static class Solution {
        String[] user_id, banned_id;
        HashSet<Integer> result = new HashSet<>();

        //idx = 찾을 제재 아이디의 인덱스 번호, is_used = 이미 사용된 아이디를 체크한 배열, num = 사용된 숫자들을 알려주는 비트마스킹 숫자
        void dfs(int idx, boolean[] is_used, int num) {
            //모든 제재 아이디에 해당되는 아이디를 찾은 경우
            if (idx == banned_id.length) {
                result.add(num);
                return;
            }
            //찾을 제재 아이디
            String bid = banned_id[idx];
            for (int i = 0; i < user_id.length; i++) {
                //이미 사용된 아이디일 경우
                if (is_used[i]) continue;
                String uid = user_id[i];
                //길이가 다를 경우
                if (bid.length() != uid.length()) continue;
                //다른 아이디일 경우 same = false인 상태로 break;
                boolean same = true;
                for (int j = 0; j < uid.length(); j++) {
                    if (bid.charAt(j) == '*') continue;
                    if (bid.charAt(j) != uid.charAt(j)) {
                        same = false;
                        break;
                    }
                }
                //같은 아이디 일 경우
                if (same) {
                    is_used[i] = true;
                    dfs(idx + 1, is_used, num | (1 << i));
                    is_used[i] = false;
                }
            }
        }

        public int solution(String[] user_id, String[] banned_id) {
            this.user_id = user_id;
            this.banned_id = banned_id;
            dfs(0, new boolean[user_id.length], 0);
            return result.size();
        }
    }
}
