package beakjoon;

import java.util.Scanner;

public class Prob_2613 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] numArr = new int[N];
        for (int i = 0; i < numArr.length; i++) {
            numArr[i] = sc.nextInt();
        }
        int left = 1, right = 50;
        int answer = 0;


        while (left <= right) {
            //left와 right의 중간값

            int mid = (left + right) / 2; // 그룹의 합의 최대치

            if (able(numArr, M, mid)) {
                //최소값은 아니더라도
                right = mid - 1;
                answer = mid;
            } else {
                left = mid + 1;
            }
        }

        print(answer, numArr, M);
    }

    static void print(int answer, int[] numArr, int M) {
        StringBuilder sb = new StringBuilder();
        sb.append(answer).append("\n");

        //만들어야 하는 그룹의 갯수랑 남의 숫자의 갯수가 같이 질 경우 1개씩 그룹한다.
        int groupLeft = M - 1;
        int numberLeft = numArr.length;
        if (groupLeft == numberLeft) {
            for (int i = 0; i < groupLeft; i++) {
                sb.append("1 ");
            }
        }

        int sum = 0;
        int count = 0;
        for (int idx = 0; idx < numArr.length; idx++) {
            if (groupLeft == numberLeft) {
                sum = 0;
                sb.append(count + " ");
                for (int i = 0; i < groupLeft; i++) {
                    sb.append("1 ");
                }
                break;
            }
            count++;
            numberLeft--;
            sum += numArr[idx];
            if (sum > answer) {
                //그룹하나 생성
                groupLeft--;
                sb.append((count - 1) + " ");
                sum = numArr[idx];
                count = 1;
            }
        }
        if (sum > 0) sb.append(count);
        System.out.println(sb);
    }

    //구슬들을 M개의 그룹으로 나누는데 그룹들의 합이 max를 넘으면 안된다.
    static boolean able(int[] numArr, int M, int max) {
        //max 보다 숫자가 큰 경우 절대 안되기 때문에 분가능
        for (int i : numArr) {
            if (i > max) return false;
        }
        int start = 0;

        //그룹의 합이 max 보다 크지 않을때까지 더해준다.

        int idx = 0;
        int sum = 0;
        int group = 0;

        while (idx < numArr.length) {
            if (sum + numArr[idx] > max) {
                //다음그룹이 시작된다.
                group++;
                sum = numArr[idx];
            } else {
                sum += numArr[idx];
            }
            idx++;
        }

        //더 많은 그룹을 만들어야하는 이유는 max가 작아서
        return group < M;

    }
}