package programmers.kakao;

import java.util.Arrays;

public class Prob_60061 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(5, new int[][]{
                {1, 0, 0, 1},
                {1, 1, 1, 1},
                {2, 1, 0, 1},
                {2, 2, 1, 1},
                {5, 0, 0, 1},
                {5, 1, 0, 1},
                {4, 2, 1, 1},
                {3, 2, 1, 1}});
        s.solution(5, new int[][]{
                {0, 0, 0, 1},
                {2, 0, 0, 1},
                {4, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 1, 1, 1},
                {2, 1, 1, 1},
                {3, 1, 1, 1},
                {2, 0, 0, 0},
                {1, 1, 1, 0},
                {2, 2, 0, 1}});
    }

    static class Solution {
        public int[][] solution(int n, int[][] build_frame) {
            int structureCount = 0;
            boolean[][] column = new boolean[n + 1][n + 1];
            boolean[][] floor = new boolean[n + 1][n + 1];
            for (int[] build : build_frame) {
                int row = build[1];
                int col = build[0];
                int structure = build[2];
                int command = build[3];

                if (command == 1) {
                    if (structure == 0) {
                        if (canConstructColumn(row, col, column, floor)) {
                            column[row][col] = true;
                            structureCount++;
                        }
                    } else {
                        if (canConstructFloor(row, col, column, floor)) {
                            floor[row][col] = true;
                            structureCount++;
                        }
                    }
                } else {
                    if (structure == 0) {
                        column[row][col] = false;
                        if (!canDestroy(column, floor)) {
                            column[row][col] = true;
                        } else {
                            structureCount--;
                        }
                    } else {
                        floor[row][col] = false;
                        if (!canDestroy(column, floor)) {
                            floor[row][col] = true;
                        } else {
                            structureCount--;
                        }
                    }

                }
            }
            int[][] answer = new int[structureCount][3];
            int idx = 0;
            for (int j = 0; j <= n; j++) {
                for (int i = 0; i <= n; i++) {
                    if (column[i][j]) {
                        answer[idx][0] = j;
                        answer[idx][1] = i;
                        answer[idx][2] = 0;
                        idx++;
                    }
                    if (floor[i][j]) {
                        answer[idx][0] = j;
                        answer[idx][1] = i;
                        answer[idx][2] = 1;
                        idx++;
                    }
                }
            }
            System.out.println(Arrays.deepToString(answer));
            return answer;
        }

        boolean canDestroy(boolean[][] column, boolean[][] floor) {
            for (int j = 0; j < column.length; j++) {
                for (int i = 0; i < column.length; i++) {
                    if (column[i][j] && !canConstructColumn(i, j, column, floor)) {
                        return false;
                    }
                    if (floor[i][j] && !canConstructFloor(i, j, column, floor)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean canConstructColumn(int row, int col, boolean[][] column, boolean[][] floor) {
//            기둥 설치 조건
//            1. 바닥 위
//            2. 다른 기둥 위
//            3. 보의 끝쪽 위

            if (row == 0) {
                return true;
            }
            if (row > 0 && column[row - 1][col]) {
                return true;
            }
            if (floor[row][col]) {
                return true;
            }
            if (col > 0 && floor[row][col - 1]) {
                return true;
            }
            return false;
        }

        boolean canConstructFloor(int row, int col, boolean[][] column, boolean[][] floor) {
//          보 설치 조건
//          1. 한쪽끝이 기둥위
//          2. 양쪽끝이 다른 보와 연결되어 있을 때
            if (row > 0 && column[row - 1][col]) {
                return true;
            }
            if (row > 0 && column[row - 1][col + 1]) {
                return true;
            }
            if (0 < col && col < floor.length && floor[row][col - 1] && floor[row][col + 1]) {
                return true;
            }
            return false;
        }
        /*

        기둥과 보를 이용하여 구조물을 생성

        삭제시에도 위의 조건들이 모두 성립되어야 한다 아니면 무시

        작업이 순서대로 담긴 2차원 배열을 받는다.

        5 <= n <= 100
        총 작업의 갯수는 1이상 1000이하
        작업의 형태는 [ x, y, a, b ]
        - x, y는 설치, 삭제할 곳의 좌표
        - a가 0이면 기둥 1이면 보
        - b가 0이면 삭제 1이면 설치

         기둥의 경우는 위쪽 방향, 보의 경우는 오른쪽방향


        -벽을 벗어나게 설치하는 경우는 없다
        -보를 바닥에 설치하는 경우는 없다.
        -구조물이 겹치도록 설치하거나 없는 구조물을 삭제하는 경우는 없다.


        2차원 배열로 출력하며
        [x좌표, y좌표, 0은 기둥 1은 보 ]
        x좌표 기준으로 오름 차순 같을 경우 y 기준으로 같으면 기둥 먼저

         */
    }
}
