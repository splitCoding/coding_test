package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_2011 {
    static String N;
    static int[] dy;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.next();
    }

    static void solution() {
        //맨처음이 0일 경우 잘못 된 암호
        if (N.substring(0, 1).equals("0")) {
            sb.append(0);
        } else {
            dy = new int[N.length() + 1];
            dy[0] = 1;
            dy[1] = 1;
            //반복문의 i의 자리수
            for (int i = 2; i <= N.length(); i++) {
                //한자리 전까지 만든 조합의 뒤에 한자리를 붙힌 경우 ( 뒤에 숫자 0이 아닌 경우 )
                if (!N.substring(i - 1, i).equals("0")) dy[i] = dy[i - 1];
                //두자리 전까지 만든 조합의 뒤에 두자리를 붙힌 경우 ( 뒤에 숫자가 26이하 인 경우 )
                if (Integer.parseInt(N.substring(i - 2, i)) <= 26) {
                    //두자리의 앞이 0이 아닌 경우
                    if (!N.substring(i - 2, i - 1).equals("0")) {
                        dy[i] += dy[i - 2];
                        dy[i] %= 1000000;
                    }
                }
            }
            sb.append(dy[N.length()]);
        }
    }

    static void show_result() {
        System.out.println(sb);
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
