package programmers.kakao;

public class Prob_17681 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28});
    }

    static class Solution {
        public String[] solution(int n, int[] arr1, int[] arr2) {
            String[] totalMap = new String[n];
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n; i++) {
                for (int j = n - 1; j >= 0; j--) {
                    int divide = 1 << j;
                    //arr1에 벽이 존재하는 경우
                    if (arr1[i] / (divide) > 0) {
                        sb.append("#");
                    }
                    //arr2에 벽이 존재하는 경우
                    else if (arr2[i] / (divide) > 0) {
                        sb.append("#");
                    }
                    //어디에도 벽이 존재하지 않는 경우
                    else {
                        sb.append(" ");
                    }
                    //다음을 비교하기 위해 divide로 나눈 나머지를 저장한다.
                    arr1[i] %= (divide);
                    arr2[i] %= (divide);
                }
                totalMap[i] = sb.toString();
                sb.setLength(0);
            }
            return totalMap;
        }
    }
}
