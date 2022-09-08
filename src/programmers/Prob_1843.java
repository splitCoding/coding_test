package programmers;

import java.util.Arrays;

public class Prob_1843 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new String[]{"1", "-", "3", "+", "5", "-", "8"}));
        System.out.println(s.solution(new String[]{"5", "-", "3", "+", "1", "+", "2", "-", "4"}));
    }

    static class Solution {
        public int solution(String arr[]) {
            //피연산자 모임
            int[] num = new int[arr.length / 2 + 1];
            //연산자 모임
            char[] ops = new char[arr.length / 2];


            //연산자, 피연산자 배열에 찾아서 삽입
            int numIdx = 0, opsIdx = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i % 2 == 0) num[numIdx++] = Integer.parseInt(arr[i]);
                else ops[opsIdx++] = arr[i].charAt(0);
            }


            //minDp[i][j] = i부터 j번째 숫자까지 연산한 결과의 최솟값을 가진다.
            int[][] minDp = new int[num.length][num.length];
            //maxDp[i][j] = i부터 j번째 숫자까지 연산한 결과의 최대값을 가진다.
            int[][] maxDp = new int[num.length][num.length]; 


            //minDp를 최대정수로 모두 채움
            Arrays.stream(minDp).iterator().forEachRemaining(o -> Arrays.fill(o, Integer.MAX_VALUE));
            //maxDp에 최소정수로 모두 채움
            Arrays.stream(maxDp).iterator().forEachRemaining(o -> Arrays.fill(o, Integer.MIN_VALUE));


            //아무런 연산이 안된 상태를 두 dp에 대입
            for (int i = 0; i < num.length; i++) {
                maxDp[i][i] = Integer.parseInt(arr[i * 2]);
                minDp[i][i] = Integer.parseInt(arr[i * 2]);
            }


            // 점화식 시작

            //cnt = 추가로 연산할 숫자의 갯수
            for (int cnt = 1; cnt < num.length; cnt++) {
                //st = 시작하는 숫자의 인덱스
                for (int st = 0; st + cnt < num.length; st++) {
                    //end = 연산이 끝나는 숫자의 인덱스
                    int end = st + cnt;
                    /*
                    div = st ~ end 까지 모든 경우를 연산하기위에 중간를 나뉘어주는 기준점
                    [st ~ div] 와 [div + 1 ~ end] 를 연산한다.
                     */
                    for (int div = st; div < end; div++) {
                        char op = ops[div];
                        //사이에 있는 연산자가 "+" 일경우
                        if (op == '+') {
                            //st ~ div 까지 연산결과의 최댓값과 div+1 ~ end 까지 연산결과의 최댓값을 더한뒤 기존 값보다 크면 넣는다.
                            maxDp[st][end] = Math.max(maxDp[st][end], maxDp[st][div] + maxDp[div+1][end]);
                            //st ~ div 까지 연산결과의 최솟값과 div+1 ~ end 까지 연산결과의 최솟값을 더한뒤 기존 값보다 작으면 넣는다.
                            minDp[st][end] = Math.min(minDp[st][end], minDp[st][div] + minDp[div+1][end]);
                        }
                        //사이에 있는 연산자가 "-" 일 경우
                        if (op == '-') {
                            //st ~ div 까지 연산결과의 최댓값에 div+1 ~ end 까지 연산결과의 최솟값을 뺀 값이 기존 값보다 크면 넣는다.
                            maxDp[st][end] = Math.max(maxDp[st][end], maxDp[st][div] - minDp[div+1][end]);
                            //st ~ div 까지 연산결과의 최솟값에 div+1 ~ end 까지 연산결과의 최솟값을 뺀 값이 기존 값보다 작으면 넣는다.
                            minDp[st][end] = Math.min(minDp[st][end], minDp[st][div] - maxDp[div+1][end]);
                        }
                    }
                }
            }

            //모든 숫자를 연산한 최대값을 반환한다.
            return maxDp[0][num.length - 1];
        }
    }
}
