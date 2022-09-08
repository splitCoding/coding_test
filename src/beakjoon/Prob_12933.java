package beakjoon;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Prob_12933 {

    static boolean check(int j, int[] count) {
        for (int i = 0; i < j; i++) {
            if (count[i] < count[j]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();

        // 가능 여부 확인

        //모든 알파벳의 숫자가 같은지 확인
        int[] count = new int[5];

        for (int i = 0; i < s.length(); i++) {
            int idx = -1;
            if (s.charAt(i) == 'q') idx = 0;
            if (s.charAt(i) == 'u') idx = 1;
            if (s.charAt(i) == 'a') idx = 2;
            if (s.charAt(i) == 'c') idx = 3;
            if (s.charAt(i) == 'k') idx = 4;
            count[idx]++;

            //먼저나와야할 알파벳보다 먼저 나온 경우
            if (!check(idx, count)) {
//                System.out.println("먼저나와야할 알파벳보다 먼저 나온 경우");
                System.out.println(-1);
                return;
            }
        }

        Set<Integer> set = new HashSet();
        for (int i : count) set.add(i);

        if( set.size() == 1){

            int max = 0;
            int crying = 0;

            for (int i = 0; i < s.length(); i++) {
                //q가 등장하면 crying + 1, k가 등장하면 duck - 1
                if(s.charAt(i) == 'q') crying++;
                //k가 등장한 순간 현재 울고있는 오리의 마릿수 저장
                if(s.charAt(i) == 'k') {
                    max = Math.max(max, crying);
                    crying--;
                }
            }
            System.out.println(max);

        } else {
//            System.out.println("울음소리가 모두 완성되지 않은 경우");
            System.out.println(-1);
        }
    }

}
