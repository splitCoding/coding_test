package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/12907

public class Changes {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(11, new int[]{2, 3, 5, 7}));
        System.out.println(s.solution2(11, new int[]{2, 3, 5, 7}));
    }

    static class Solution {
        int MOD = 1000000007;

        int solution(int n, int[] money) {
            int[][] dy = new int[money.length + 1][n + 1];
            for (int i = 0; i < money.length; i++) dy[i + 1][0] = 1;

            for (int i = 0; i < money.length; i++) {
                for (int j = 1; j <= n; j++) {
                    if (j < money[i]) dy[i + 1][j] = dy[i][j];
                    else dy[i + 1][j] = (dy[i][j] + dy[i + 1][j - money[i]]) % MOD;
                }
            }
            return dy[money.length][n];
        }

        int solution2(int n, int[] money) {
            int[] dy = new int[n + 1];
            dy[0] = 1;
            for (int m : money) {
                for (int i = 0; i <= n; i++) {
                    if (i < m) continue;
                    dy[i] = ( dy[i] + dy[i - m] ) % MOD;
                }
            }
            return dy[n];
        }
    }
}
