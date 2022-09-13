package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/77485

import java.util.*;

public class Prob_77485 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}});
    }

    static class Solution {
        /*
        x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.
        ( x1, y2, x2, y2 )
         */
        public int[] solution(int rows, int columns, int[][] queries) {
            int[] answer = new int[queries.length];
            int[][] table = new int[rows + 1][columns + 1];
            int num = 1;
            for (int i = 1; i < table.length; i++) {
                for (int j = 1; j < table[i].length; j++) {
                    table[i][j] = num++;
                }
            }
            int idx = 0;
            for (int[] query : queries) {
                answer[idx++] = spin(table, query);
            }
            return answer;
        }

        int spin(int[][] table, int[] query) {
            //시계방향으로 회전
            int x1 = query[0], y1 = query[1], x2 = query[2], y2 = query[3];

            List<Integer> numbers = new ArrayList<>();

            int row = x1, col = y1;
            int min = Integer.MAX_VALUE;

            //숫자들 받아오기
            //오른쪽
            while (col <= y2) {
                numbers.add(table[row][col]);
                min = Math.min(min, table[row][col]);
                col++;
            }
            //아래
            col--;
            while (++row <= x2) {
                numbers.add(table[row][col]);
                min = Math.min(min, table[row][col]);
            }
            //왼쪽
            row--;
            while (--col >= y1) {
                numbers.add(table[row][col]);
                min = Math.min(min, table[row][col]);
            }
            //위쪽
            col++;
            while (--row > x1) {
                numbers.add(table[row][col]);
                min = Math.min(min, table[row][col]);
            }


            //숫자들 회전하여 입력하기
            row = x1;
            col = y1 + 1;
            int idx = 0;
            //오른쪽으로
            while (col <= y2) {
                table[row][col] = numbers.get(idx++);
                col++;
            }
            //아래
            col--;
            while (++row <= x2) {
                table[row][col] = numbers.get(idx++);
            }
            //왼쪽
            row--;
            while (--col >= y1) {
                table[row][col] = numbers.get(idx++);
            }
            //위쪽
            col++;
            while (--row >= x1) {
                table[row][col] = numbers.get(idx++);
            }
            return min;
        }
    }
}
