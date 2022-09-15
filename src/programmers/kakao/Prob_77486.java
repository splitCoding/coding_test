package programmers.kakao;

import java.util.*;

public class Prob_77486 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        )));

        System.out.println(Arrays.toString(s.solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"sam", "emily", "jaimie", "edward"},
                new int[]{2,3,5,4}
        )));

    }

    static class Solution {
        /*
        판매원이 제품을 판매하면 피라미들 타고 조금씩 분배되는 형태이다.
        자신을 추천한 사람에게 자신의 이익의 10%로를 주는 형태이다 ( 판매금, 추천인에게 받은 금액 다 포함, 10%를 한금액이 1원 이하일 경우 주지 않는다. )
        칫솔은 개당 100원의 이익을 낸다.
        민호는 피라미드 제일 상단에 있다.
         */
        public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
            int[] result = new int[enroll.length];

            Map<String, Member> memberMap = new HashMap<>();
            for (int i = 0; i < enroll.length; i++) {
                memberMap.put(enroll[i], new Member(i, referral[i]));
            }

            for (int i = 0; i < seller.length; i++) {
                sell(seller[i], amount[i], result, memberMap);
            }
            return result;
        }

        void sell(String seller, int amount, int[] result, Map<String, Member> memberMap) {
            int total = amount * 100;
            Member findMember = memberMap.get(seller);

            result[findMember.idx] += (total - (total / 10));
            tenPercent(total / 10, findMember.whoInvite, result, memberMap);
        }

        void tenPercent(int total, String invite, int[] result, Map<String, Member> memberMap) {
            if (total == 0) return;
            if (invite.equals("-")) return;
            Member findMember = memberMap.get(invite);
            result[findMember.idx] += (total - (total / 10));
            tenPercent(total / 10, findMember.whoInvite, result, memberMap);
        }

        class Member {
            int idx;
            String whoInvite;

            public Member(int idx, String whoInvite) {
                this.idx = idx;
                this.whoInvite = whoInvite;
            }
        }
    }
}
