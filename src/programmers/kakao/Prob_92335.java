package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/92335

public class Prob_92335 {
    public static void main(String[] args) {
        Solution s = new Solution();
        String s1 = "100";
        System.out.println(s.solution(1000000, 2));
        System.out.println(s.solution(19, 10));
    }

    static class Solution {
        public int solution(int n, int k) {
            int answer = 0;
            String changed = (k == 10) ? n + "" : change(n, k);
            String[] split = changed.split("0");
            for (String s : split) {
                long num = 0;
                for (int i = 0; i < s.length(); i++) {
                    num = num * 10 + (s.charAt(i) - '0');
                }
                if (able(num)) answer++;
            }
            return answer;
        }

        String change(int n, int k) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                sb.insert(0, n % k);
                n /= k;
            }
            return sb.toString();
        }

        boolean able(Long num) {
            if (num == 1 || num == 0) return false;
            for (long i = 2; i * i <= num; i++) {
                if (num % i == 0) return false;
            }
            return true;
        }
    }
}
