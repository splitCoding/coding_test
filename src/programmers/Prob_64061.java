package programmers;

import java.util.Stack;

//https://school.programmers.co.kr/learn/courses/30/lessons/64061

public class Prob_64061 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 3},
                {0, 2, 5, 0, 1},
                {4, 2, 4, 4, 2},
                {3, 5, 1, 3, 1}}, new int[]{1, 5, 3, 5, 1, 2, 1, 4}));
    }

    static class Solution {
        /*
        4 3 1 1 3 2 4
         */

        public int solution(int[][] board, int[] moves) {
            int result = 0;
            Stack<Integer> st = new Stack<>();

            for (int i : moves) {
                for (int j = 0; j < board.length; j++) {
                    if (board[j][i - 1] == 0) continue;

                    st.push(board[j][i - 1]);
                    board[j][i - 1] = 0;

                    if (st.size() > 1) {
                        int pop = st.pop();
                        if (pop == st.peek()) {
                            st.pop();
                            result += 2;
                        } else {
                            st.push(pop);
                        }
                    }
                    break;
                }
            }
            return result;
        }
    }
}
