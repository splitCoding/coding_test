package programmers.kakao;

public class Prob_81301 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("one4seveneight"));
        System.out.println(s.solution("23four5six7"));
        System.out.println(s.solution("2three45sixseven"));
        System.out.println(s.solution("123"));
    }

    static class Solution {
        public int solution(String s) {
            StringBuilder sb = new StringBuilder();

            int start = 0, end = 0;
            while (start < s.length() && end < s.length()) {
                //s의 start 번째가 문자일 시
                char now = s.charAt(start);
                if (now == 'o') {
                    start += 3;
                    sb.append("1");
                } else if (now == 'z') {
                    start += 4;
                    sb.append("0");
                } else if (now == 'n') {
                    start += 4;
                    sb.append("9");
                } else if (now == 'e') {
                    start += 5;
                    sb.append("8");
                } else if (now == 't') {
                    if (s.charAt(start + 1) == 'w') {
                        start += 3;
                        sb.append("2");
                    } else {
                        start += 5;
                        sb.append("3");
                    }
                } else if (now == 's') {
                    if (s.charAt(start + 1) == 'i') {
                        start += 3;
                        sb.append("6");
                    } else {
                        start += 5;
                        sb.append("7");
                    }
                } else if (now == 'f') {
                    if (s.charAt(start + 1) == 'o') {
                        start += 4;
                        sb.append("4");
                    } else {
                        start += 4;
                        sb.append("5");
                    }
                } else {
                    sb.append(now);
                    start++;
                }
            }

            return Integer.parseInt(sb.toString());
        }
    }
}
