package beakjoon;

import java.util.Scanner;

public class Prob_1508 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();

        int[] arr = new int[k];
        for (int i = 0; i < k; i++) {
            arr[i] = sc.nextInt();
        }

        int left = 1, right = n + 1;
        StringBuilder answer = new StringBuilder();
        while (left <= right) {
            StringBuilder sb = new StringBuilder();
            int mid = (left + right) / 2;
            int prev = 0;
            int count = 1;
            sb.append("1");
            for (int i = 1; i < arr.length; i++) {
                //심판배치 가능
                if (arr[i] - arr[prev] >= mid) {
                    sb.append("1");
                    count++;
                    prev = i;
                    if (count == m) {
                        while (sb.length() < k) {
                            sb.append("0");
                        }
                        answer.setLength(0);
                        answer.append(sb);
                        break;
                    }
                } else {
                    sb.append("0");
                }
            }

            //심판을 m명 이상으로 배치 할 수 있는 경우
            if(count == m){
                //간격을 더 넓힌다.
                left = mid + 1;
            } else {
                //간격을 더 좁힌다.
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}
