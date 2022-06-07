package programmers;


public class Multiply_matrix {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{5, 3}, {3, 10}, {10, 6}}));
    }

    static class Solution {
        public int solution(int[][] m) {
            int len = m.length;
            int[][] dy = new int[len][len];

            for (int st = 0; st < len; st++) {
                int[] st_arr = m[st];
                //다음 행렬들과 계속 차례대로 곱하기
                for (int end = 1; st + end < len; end++) {
                    int last = st + end;
                    int[] last_arr = m[last];
                    dy[st][last] = dy[st][last - 1] + (st_arr[0] * last_arr[0] * last_arr[1]);
                }
                //이전 행렬들과 계속 차례대로 곱하기
                for (int end = 1; st - end >= 0; end++) {
                    int prev = st - end;
                    int[] prev_arr = m[prev];
                    dy[st][prev] = dy[st][prev + 1] + (prev_arr[0] * prev_arr[1] * st_arr[1]);
                }
            }

            for (int l = 2; l < len; l++) {
                for (int st = 0; st + l < len; st++) {
                    int end = st + l;
                    for (int divide = 0; divide < l; divide++) {
                        //걸린시간 = dy[st][st + divide] + dy[st + divide + 1][end] + 두 행렬을 곱하는 시간
                        int time = dy[st][st + divide] + dy[st + divide + 1][end];
                        time += m[st][0] * m[st + divide][1] * m[end][1];
                        dy[st][end] = Math.min(dy[st][end], time);
                    }
                }
            }

            return Math.min(dy[0][len - 1], dy[len - 1][0]);
        }
    }
}
