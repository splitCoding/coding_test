package programmers;

//https://school.programmers.co.kr/learn/courses/30/lessons/12909

import java.util.Stack;

public class Prob_12909 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("()()"));
    }

    static class Solution {
        boolean solution(String s) {
            int check = 0;
            for(int i=0;i<s.length();i++) {
                char c = s.charAt(i);
                if (c == '(') {
                    check++;
                } else if (c == ')') {
                    if (check == 0) {
                        return false;
                    } else {
                        check--;
                    }
                }
            }
            return check == 0;
        }
    }
}
