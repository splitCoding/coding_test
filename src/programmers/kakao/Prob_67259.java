package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/67259

import java.util.*;

public class Prob_67259 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution1(new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {1, 0, 0, 0, 0, 0, 0, 0}}
        ));

        System.out.println(s.solution1(new int[][]{
                        {0, 0, 0, 0, 0, 0},
                        {0, 1, 1, 1, 1, 0},
                        {0, 0, 1, 0, 0, 0},
                        {1, 0, 0, 1, 0, 1},
                        {0, 1, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0}
                }
        ));

        System.out.println(s.solution1(new int[][]{
                        {0, 0, 0},
                        {0, 0, 0},
                        {0, 0, 0}
                }
        ));
    }

    static class Solution {

        public int solution1(int[][] board) {
            //3차 배열로 dfs를 사용하여 문제 풀이

            /*
            dp[a][b][c]  = a, b 로 c 방향으로 들어왔을 때의 비용
            0 = 위에서 , 1 = 아래에서, 2 = 오른쪽에서, 3 = 왼쪽에서

            dp[nRow][nCol][0] 는
                dp[row][col][0] + 100,
                dp[row][col][1] + 100,
                dp[row][col][2] + 600,
                dp[row][col][2] + 600
                중 제일 작은 값을 넣게 되는데 원리 있는 값이 이들 보다 작다면 진행을 멈춘다.

            dp[nRow][nCol][1] 은
                dp[row][col][0] + 100,
                dp[row][col][1] + 100,
                dp[row][col][2] + 600,
                dp[row][col][2] + 600
                중 제일 작은 값을 넣게 되는데 원리 있는 값이 이들 보다 작다면 진행을 멈춘다.

            dp[nRow][nCol][2] 은
                dp[row][col][0] + 600,
                dp[row][col][1] + 600,
                dp[row][col][2] + 100,
                dp[row][col][2] + 100
                중 제일 작은 값을 넣게 되는데 원리 있는 값이 이들 보다 작다면 진행을 멈춘다.

            dp[nRow][nCol][3] 은
                dp[row][col][0] + 600,
                dp[row][col][1] + 600,
                dp[row][col][2] + 100,
                dp[row][col][2] + 100
                중 제일 작은 값을 넣게 되는데 원리 있는 값이 이들 보다 작다면 진행을 멈춘다.

            이동하기전 구역까지의 최소비용에서 이번에 지을 비용을 합하여 해당 방향에 저장한다.
            */
            int[][][] dp = new int[board.length][board.length][4]; //마지막은 들어온 방향 ( 0 = 위, 1 = 아래, 2 = 오른쪽, 3 = 왼쪽 )
            //초기화
            for (int[][] ints : dp) {
                for (int[] anInt : ints) {
                    Arrays.fill(anInt, 25 * 25 * 600);
                }
            }
            dp[0][0][0] = 0;
            dp[0][0][1] = 0;
            dp[0][0][2] = 0;
            dp[0][0][3] = 0;

            int[][] moves = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

            dfs(0, 0, dp, board, moves);

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                answer = Math.min(answer, dp[board.length - 1][board.length - 1][i]);
            }


            return answer;
        }

        void dfs(int row, int col, int[][][] dp, int[][] board, int[][] moves) {
            //탈출 조건
            if (row == board.length - 1 && col == board.length - 1) return;
//            System.out.println("::dfs in:: " + row + " , " + col + " : " + Arrays.toString(dp[row][col]));


            for (int i = 0; i < moves.length; i++) {
                int[] move = moves[i];
                int nRow = row + move[0], nCol = col + move[1];
                if (nRow < 0 || nCol < 0 || nRow >= board.length || nCol >= board.length || board[nRow][nCol] == 1) {
                    continue;
                }

                if (i == 0 || i == 1) {
                    int min = Integer.MAX_VALUE;

                    for (int dir = 0; dir < 4; dir++) {
                        if (dir == 0 || dir == 1) {
                            if (min > dp[row][col][dir] + 100) {
                                min = dp[row][col][dir] + 100;
                            }
                        }
                        if (dir == 2 || dir == 3) {
                            if (min > dp[row][col][dir] + 600) {
                                min = dp[row][col][dir] + 600;
                            }
                        }
                    }
                    if (dp[nRow][nCol][i] <= min) continue;
                    dp[nRow][nCol][i] = Math.min(dp[nRow][nCol][i], min);
                    dfs(nRow, nCol, dp, board, moves);
                }
                if (i == 2 || i == 3) {
                    int min = Integer.MAX_VALUE;

                    for (int dir = 0; dir < 4; dir++) {
                        if (dir == 0 || dir == 1) {
                            if (min > dp[row][col][dir] + 600) {
                                min = dp[row][col][dir] + 600;
                            }
                        }
                        if (dir == 2 || dir == 3) {
                            if (min > dp[row][col][dir] + 100) {
                                min = dp[row][col][dir] + 100;
                            }
                        }
                    }
                    if (dp[nRow][nCol][i] <= min) continue;
                    dp[nRow][nCol][i] = Math.min(dp[nRow][nCol][i], min);
                    dfs(nRow, nCol, dp, board, moves);
                }
            }
        }




        public int solution2(int[][] board) {
            //움직이는 순서를 바꿔서 모두 dfs 진행
            int n = board.length;
            int[][][] dp = new int[4][n][n];
            for (int[][] ints : dp) {
                for (int[] anInt : ints) {
                    Arrays.fill(anInt, Integer.MAX_VALUE);
                }
            }
            dp[0][0][0] = 0;
            dp[1][0][0] = 0;
            dp[2][0][0] = 0;
            dp[3][0][0] = 0;
            int[][][] moves = {
                    {{-1, 0}, {1, 0}, {0, -1}, {0, 1}},
                    {{1, 0}, {0, -1}, {0, 1}, {-1, 0}},
                    {{0, -1}, {0, 1}, {-1, 0}, {1, 0}},
                    {{0, 1}, {-1, 0}, {1, 0}, {0, -1}}
            };

            for (int i = 0; i < 4; i++) {
                recursive(new int[]{0, 0}, 0, 0, board, dp[i], moves[i]);
            }

            int answer = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                answer = Math.min(answer, dp[i][dp[i].length - 1][dp[i].length - 1]);
            }
            return answer;
        }

        void recursive(int[] prev, int row, int col, int[][] board, int[][] dp, int[][] moves) {
            if (row == board.length && col == board.length) return;


            for (int[] move : moves) {
                int nRow = row + move[0];
                int nCol = col + move[1];

                if (nRow < 0 || nCol < 0 || nRow >= board.length || nCol >= board.length) continue;
                if (board[nRow][nCol] == 1) continue;
                if (prev[0] != move[0] && prev[1] != move[1]) {
                    if (dp[nRow][nCol] >= dp[row][col] + 600) {
                        dp[nRow][nCol] = dp[row][col] + 600;
                        recursive(move, nRow, nCol, board, dp, moves);
                    }
                } else {
                    if (dp[nRow][nCol] >= dp[row][col] + 100) {
                        dp[nRow][nCol] = dp[row][col] + 100;
                        recursive(move, nRow, nCol, board, dp, moves);

                    }
                }
            }
        }
    }
}