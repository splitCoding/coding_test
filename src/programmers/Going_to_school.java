package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/42898

public class Going_to_school {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, 3, new int[][]{{4, 3}, {2, 1}}));
    }

    static class Solution {
        int MOD = 1000000007;

        public int solution(int m, int n, int[][] puddles) {
            int r = n, c = m;
            int[][] dy = new int[r + 1][c + 1];
            dy[1][1] = 1; //집의 위치
            for (int[] p : puddles) dy[p[1]][p[0]] = -1; //웅덩이는 -1로 표시

            for (int row = 1; row <= r; row++) {
                for (int col = 1; col <= c; col++) {
                    if (row == 1 && col == 1) continue; //집인 경우
                    if (dy[row][col] == -1) continue; //웅덩이인 경우
                    if (dy[row][col - 1] != -1) dy[row][col] += dy[row][col - 1]; //왼쪽에서 온 경우
                    if (dy[row - 1][col] != -1) dy[row][col] += dy[row - 1][col]; //오른쪽에서 온 경우
                    dy[row][col] %= MOD;
                }
            }

            return dy[r][c];
        }
    }
}
