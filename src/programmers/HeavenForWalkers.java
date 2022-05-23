package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/1832?language=java

public class HeavenForWalkers {
    public static void main(String[] args) {
        int m = 3, n = 3;
        int[][] cityMap = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        Solution s = new Solution();
        System.out.println(s.solution(m, n, cityMap));
        m = 3;
        n = 6;
        cityMap = new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
        System.out.println(s.solution(m, n, cityMap));
    }

    static class Solution {
        int MOD = 20170805;

        public int solution(int m, int n, int[][] cityMap) {
            int[][] dy = new int[m][n];
            dy[0][0] = 1; //위에서 아래
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    //통행불가, 직진만 가능 구간인 경우
                    if (cityMap[row][col] == 1 || cityMap[row][col] == 2) {
                        dy[row][col] = 0;
                        continue;
                    }
                    //통행이 모두 가능한 경우
                    if (cityMap[row][col] == 0) {
                        //왼쪽의 값이 0일 때
                        if (col > 0 && dy[row][col - 1] == 0) {
                            //통행금지 구역인 경우
                            if (cityMap[row][col - 1] == 1) dy[row][col] += dy[row][col - 1];
                            //직진가능 구역인 경우
                            if (cityMap[row][col - 1] == 2) {
                                //진입가능한 구간을 찾는다
                                int n_col = col - 1;
                                while (cityMap[row][n_col] == 2) {
                                    if (n_col == 0) break;
                                    n_col--;
                                }
                                dy[row][col] = (dy[row][col] + dy[row][n_col]) % MOD;
                            }
                        } else {
                            //왼쪽의 값이 0이 아닐 경우
                            if (col > 0) dy[row][col] = (dy[row][col] + dy[row][col - 1]) % MOD;
                        }
                        //위쪽 값이 0일 때
                        if (row > 0 && dy[row - 1][col] == 0) {
                            //통행금지 구역인 경우
                            if (cityMap[row - 1][col] == 1) dy[row][col] += dy[row - 1][col];
                            //직진가능 구역인 경우
                            if (cityMap[row - 1][col] == 2) {
                                //진입가능한 구간을 찾는다
                                int n_row = row - 1;
                                while (cityMap[n_row][col] == 2) {
                                    if (n_row == 0) break;
                                    n_row--;
                                }
                                dy[row][col] = (dy[row][col] + dy[n_row][col]) % MOD;
                            }
                        } else {
                            //위쪽의 값이 0이 아닐 경우
                            if (row > 0) dy[row][col] = (dy[row][col] + dy[row - 1][col]) % MOD;
                        }
                    }
                }
            }
            return dy[m - 1][n - 1];
        }
    }
}
