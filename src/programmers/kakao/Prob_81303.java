package programmers.kakao;

import java.util.Stack;

public class Prob_81303 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(8, 2, new String[]{"D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"}));
    }

    static class Solution {
        public String solution(int rowCount, int cursor, String[] cmd) {
            Stack<Integer> deleted = new Stack<>();
            int totalRow = rowCount;
            for (int i = 0; i < cmd.length; i++) {
                char c = cmd[i].charAt(0);

                if (c == 'C') {
                    deleted.add(cursor);
                    totalRow--;
                    if (cursor == totalRow) {
                        cursor--;
                    }
                }
                if (c == 'Z') {
                    int pop = deleted.pop();
                    if (pop <= cursor) {
                        cursor++;
                    }
                    totalRow++;

                }
                if (c == 'D') {
                    cursor += Integer.parseInt(cmd[i].substring(2));
                }
                if (c == 'U') {
                    cursor -= Integer.parseInt(cmd[i].substring(2));
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < totalRow; i++) {
                sb.append("O");
            }
            while (!deleted.isEmpty()) {
                sb.insert(deleted.pop().intValue(), "X");
            }
            return sb.toString();
        }
    }
}
