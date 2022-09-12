package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/60058

public class Prob_60058 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("(()())()"));
    }

    /*
    문제에서 제시한 방법만 재귀로 구현하면 된다.

    1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
    2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
    3. 문자열 u가 "올바른 괄호 문자열" 이라면 문자열 v에 대해 1단계부터 다시 수행합니다.
      3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
    4. 문자열 u가 "올바른 괄호 문자열"이 아니라면 아래 과정을 수행합니다.
      4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다.
      4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다.
      4-3. ')'를 다시 붙입니다.
      4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
      4-5. 생성된 문자열을 반환합니다.

     */
    static class Solution {
        StringBuilder answer = new StringBuilder();

        public String solution(String p) {
            recursive(p, 0);
            return answer.toString();
        }

        void recursive(String w, int start) {
            if (w.equals("")) return ;
            int divide = divide(start, w);
            String u = w.substring(start, divide + 1);
            String v = w.substring(divide + 1);
            if (isRight(u)) {
                answer.append(u);
                recursive(v, 0);
            } else {
                answer.append("(");
                recursive(v, 0);
                answer.append(")");
                String newU = u.substring(1, u.length() - 1);
                for (int i = 0; i < newU.length(); i++) {
                    if(newU.charAt(i) == '(') answer.append(")");
                    if(newU.charAt(i) == ')') answer.append("(");
                }
            }
        }

        int divide(int start, String s) {
            int open = 0, close = 0;
            int idx = start;
            while (idx < s.length()) {
                if (s.charAt(idx) == '(') open++;
                if (s.charAt(idx) == ')') close++;
                if (open == close) break;
                idx++;
            }
            return idx;
        }

        boolean isRight(String s) {
            int open = 0, close = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '(') open++;
                if (s.charAt(i) == ')') close++;
                if (close > open) return false;
            }
            return true;
        }
    }
}
