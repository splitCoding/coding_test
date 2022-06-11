package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2110_Re {
    static int N, C, x[], result;

    static void input() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        C = sc.nextInt();
        x = new int[N];
        for (int i = 0; i < x.length; i++) x[i] = sc.nextInt();
    }

    static void solution() {
        //좌표를 오름차순으로 정렬
        Arrays.sort(x);
        int st = 0, end = x[x.length - 1];

        //mid 간격으로 와이파이를 설치할때 가능할 경우 더 큰간격으로 해보기위해 st를 mid+1로 불가능할 경우 end를 mid-1로
        while (st <= end) {
            int count = 1, now = x[0];
            int mid = (st + end) / 2;

            for (int i = 1; i < x.length; i++) {
                if (x[i] - now >= mid) {
                    count++;
                    now = x[i];
                }
            }

            if (count >= C) {
                result = mid;
                st = mid + 1;
            } else {
                end = mid - 1;
            }
        }
    }

    static void show_result() {
        System.out.println(result);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
