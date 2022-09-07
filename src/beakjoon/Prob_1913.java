package beakjoon;

import java.util.*;

public class Prob_1913 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int target = scan.nextInt();

        /*

        채워지는 순서의 사이클이 존재한다.

        1. 위로 n칸
        2. 오른쪽으로 n칸
        여기서 n이 1 증가
        3. 아래로 n칸
        4. 왼쪽으로 n칸
        여기서 n이 1 증가

        위 과정을 반복하게 되고 마지막에는 위로 채워지면서 끝난다.

         */
        int num = 1;
        int move = 1;
        int[][] arr = new int[n][n];
        int stRow = n / 2, stCol = n / 2;
        int targetRow = n / 2, targetCol = n / 2;

        All : while (true) {
            if(num == 1){
                arr[stRow][stCol] = 1;
                num++;
                continue;
            }

            //위로
            for (int i = 0; i < move; i++) {
                stRow -= 1;
                arr[stRow][stCol] = num++;

                //완료시
                if (stRow == 0 && stCol == 0) {
                    //여기서 break All 말고 출력값을 출력한뒤 return; 으로 마무리해도 된다.
                    break All;
                }
            }

            //오른쪽
            for (int i = 0; i < move; i++) {
                stCol += 1;
                arr[stRow][stCol] = num++;
            }

            move++;

            //아래로
            for (int i = 0; i < move; i++) {
                stRow += 1;
                arr[stRow][stCol] = num++;
            }

            //왼쪽으로
            for (int i = 0; i < move; i++) {
                stCol -= 1;
                arr[stRow][stCol] = num++;
            }
            move++;
        }

        //StringBuilder 에 출력값 저장
        for (int l = 0; l < arr.length; l++) {
            for (int j = 0; j < arr[l].length; j++) {
                int k = arr[l][j];
                if( k == target){
                    targetRow = l;
                    targetCol = j;
                }
                sb.append(k + " ");
            }
            sb.append("\n");
        }
        sb.append((targetRow + 1) + " " + (targetCol + 1));
        System.out.println(sb);
    }
}
