package programmers;

import java.util.Arrays;

public class Not_destroyed_Re {
    public static void main(String[] args) {
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        Solution s = new Solution();
        System.out.println(s.solution(board, skill));
    }

    static class Solution {
        public int solution(int[][] board, int[][] skill) {
            int[][] prog = new int[board.length + 1][board[0].length + 1];
            int len = prog.length;
            for (int[] s : skill) {
                int type = s[0], r1 = s[1], r2 = s[3], c1 = s[2], c2 = s[4];
                int degree = (type == 1) ? s[5] * -1 : s[5];
                prog[r1][c1] += degree;
                prog[r1][c2 + 1] -= degree;
                prog[r2 + 1][c1] -= degree;
                prog[r2 + 1][c2 + 1] += degree;
            }
            for (int r = 0; r < len; r++) {
                for (int c = 1; c < prog[0].length; c++) {
                    prog[r][c] += prog[r][c - 1];
                }
            }
            for (int c = 0; c < prog[0].length; c++) {
                for (int r = 1; r < len; r++) {
                    prog[r][c] += prog[r - 1][c];
                }
            }
            int answer = 0;

            for (int i = 0; i < prog.length - 1; i++) {
                for (int j = 0; j < prog[i].length - 1; j++) {
                    if (board[i][j] + prog[i][j] > 0) answer++;
                }
            }

            return answer;
        }
    }
}
