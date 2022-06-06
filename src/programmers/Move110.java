package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/77886

import java.util.*;

public class Move110 {
    public static void main(String[] args) {
        Failed_Solution fs = new Failed_Solution();
        System.out.println(Arrays.toString(fs.solution(new String[]{"1110", "100111100", "0111111010"})));
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{"1110", "100111100", "0111111010"})));
    }

    static class Solution {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        void find_stack(String s) {
            Stack<Character> st = new Stack<>();
            count = 0;
            for (int i = 0; i < s.length(); i++) {
                st.push(s.charAt(i));
                if (st.size() > 2) {
                    //마지막 들어간 문자가 0일 경우
                    if (st.peek() == '0') {
                        char first = st.pop();
                        char second = st.pop();
                        //그 전에 들어간 문자가 1이 아닌 경우
                        if (second != '1') {
                            st.push(second);
                            st.push(first);
                            continue;
                        }
                        char third = st.pop();
                        if (third != '1') {
                            st.push(third);
                            st.push(second);
                            st.push(first);
                            continue;
                        }
                        count++;
                    }
                }
            }
            sb.setLength(0);
            while (!st.isEmpty()) sb.insert(0, st.pop());
        }


        void add_stack(String s) {
            Stack<Character> st = new Stack<>();
            int continuous_one = 0;
            boolean done = false;
            for (int i = 0; i < s.length(); i++) {
                st.push(s.charAt(i));
                if (st.peek() == '1') {
                    continuous_one++;
                    //11이 들어갔을 때
                    if (continuous_one == 2) {
                        st.pop();
                        st.pop();
                        for (int c = 0; c < count; c++) {
                            st.push('1');
                            st.push('1');
                            st.push('0');
                        }
                        st.push('1');
                        st.push('1');
                        done = true;
                    }
                } else {
                    continuous_one = 0;
                }
            }
            sb.setLength(0);
            while (!st.isEmpty()) sb.insert(0, st.pop());

            if (!done) {
                if (continuous_one == 0) {
                    for (int i = 0; i < count; i++) sb.append("110");
                } else {
                    for (int i = 0; i < count; i++) sb.insert(sb.length() - 1, "110");
                }
            }

        }

        public String[] solution(String[] s) {
            String[] answer = new String[s.length];
            int idx = 0;
            for (String st : s) {
                find_stack(st);
                add_stack(sb.toString());
                answer[idx++] = sb.toString();
            }
            return answer;
        }
    }

    //dfs사용으로 시간초과하여 실패
    static class Failed_Solution {
        StringBuilder sb = new StringBuilder();
        int count = 0;

        void find_dfs(String s) {
            if (s.contains("110")) {
                int idx = s.indexOf("110");
                int idx2 = s.lastIndexOf("110");
                if (idx == idx2) {
                    count++;
                    find_dfs(s.substring(0, idx) + s.substring(idx + 3));
                } else {
                    count += 2;
                    find_dfs(s.substring(0, idx) + s.substring(idx + 3, idx2) + s.substring(idx2 + 3));
                }
            } else {
                sb.append(s);
            }
        }


        void add(String s) {
            sb.setLength(0);
            int is_one = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '0') {
                    is_one = 0;
                } else {
                    is_one++;
                    if (is_one == 2) {
                        sb.append(s);
                        for (int c = 0; c < count; c++) sb.insert(i - 1, "110");
                        return;
                    }
                }
            }
            sb.append(s);
            if (is_one == 0) {
                for (int c = 0; c < count; c++) sb.insert(sb.length(), "110");
            } else {
                if (sb.length() == 1) {
                    for (int c = 0; c < count; c++) sb.insert(0, "110");
                } else {
                    for (int c = 0; c < count; c++) sb.insert(sb.length() - 1, "110");
                }
            }
        }


        public String[] solution(String[] s) {
            String[] answer = new String[s.length];
            int idx = 0;
            for (String st : s) {
                count = 0;
                sb.setLength(0);
                find_dfs(st);
                add(sb.toString());
                answer[idx++] = sb.toString();
            }
            return answer;
        }
    }
}
