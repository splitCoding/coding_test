package beakjoon;

//https://www.acmicpc.net/problem/18312

import java.util.Scanner;

public class Prob_18312 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        //00시00분00초부터 N시 59분 59초까지 시간중 k가 하나라도 포함된 모든 시작을 세야한다.
        Time t = new Time(0, 0, 0);
        int count = 0;

        while (t.hour < N + 1) {
            if (t.contains(K)) count++;
            t.progress();
        }
        System.out.println(count);
    }

    static class Time {
        int hour;
        int minute;
        int second;

        public Time(int hour, int minute, int second) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
        }

        void progress() {
            second++;
            if (second == 60) {
                second = 0;
                minute++;
                if (minute == 60) {
                    minute = 0;
                    hour++;
                }
            }
        }

        boolean contains(int k) {
            //0일때는 1자리수이거나 0이 들어가면 true 를 반환해야 한다.
            String hourS = hour + "";
            String minuteS = minute + "";
            String secondS = second + "";

            if (k == 0) {
                if (hourS.length() == 1 || minuteS.length() == 1 || secondS.length() == 1) {
                    return true;
                }
            }
            return hourS.contains(k + "") || minuteS.contains(k + "") || secondS.contains(k + "");
        }
    }
}
