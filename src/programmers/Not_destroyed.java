package programmers;

import java.util.Arrays;

public class Not_destroyed {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] board = {{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}};
        int[][] skill = {{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}};
        System.out.println(s.solution(board, skill));
    }

    static class Solution {
        public int solution(int[][] board, int[][] skill) {
            int[][] sum = new int[board.length + 1][board[0].length + 1];
            for (int[] s : skill) {
                int i1 = s[1], j1 = s[2];
                int i2 = s[3], j2 = s[4];
                int d = s[5] * (s[0] == 1 ? -1 : 1);
                sum[i1][j1] += d;
                sum[i2 + 1][j1] += d * -1;
                sum[i1][j2 + 1] += d * -1;
                sum[i2 + 1][j2 + 1] += d;
            }

            int answer = 0;
            //ÁÂ¿ì
            for (int r = 0; r < board.length; r++) {
                for (int c = 1; c < board[0].length; c++) {
                    sum[r][c] += sum[r][c - 1];
                }
            }
            //»óÇÏ
            for (int c = 0; c < board.length; c++) {
                for (int r = 1; r < board[0].length; r++) {
                    sum[r][c] += sum[r - 1][c];
                }
            }

            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[0].length; c++) {
                    if (board[r][c] + sum[r][c] > 0) answer++;
                }
            }
            return answer;
        }
    }
}
