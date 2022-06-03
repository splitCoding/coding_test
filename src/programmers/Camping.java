package programmers;

import java.util.*;

public class Camping {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] data = {{0, 0}, {1, 1}, {0, 2}, {2, 0}};
        System.out.println(s.solution(4, data));
    }

    static class Solution {
        public int solution(int n, int[][] data) {
            int answer = 0;

            //row를 기준으로 오름차순 정렬
            Arrays.sort(data, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    if (o1[0] == o2[0]) return o1[1] - o2[1];
                    return o1[0] - o2[0];
                }
            });
//            System.out.println(Arrays.deepToString(data));

            for (int i = 0; i < data.length; i++) {
                int row1 = data[i][0], col1 = data[i][1];
                for (int j = i + 1; j < data.length; j++) {
                    int row2 = data[j][0], col2 = data[j][1];
                    //넓이가 0인 경우
                    if (row1 == row2 || col1 == col2) continue;
                    boolean able = true;
                    for (int k = i + 1; k < data.length; k++) {
                        if (k == i && k == j) continue;
                        int row3 = data[k][0], col3 = data[k][1];
                        if ((row1 < row3 && row3 < row2) && (Math.min(col1, col2) < col3 && col3 < Math.max(col1, col2))) {
                            able = false;
                            break;
                        }
                    }
                    if (able) answer++;
//                        System.out.println(Arrays.toString(data[i]) + Arrays.toString(data[j]));
                }
            }
            return answer;
        }
    }
}
