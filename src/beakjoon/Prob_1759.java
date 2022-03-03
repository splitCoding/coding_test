package beakjoon;

import java.util.*;

public class Prob_1759 {
    static int L, C;
    static char[] letters;
    static char[] pwd;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        L = scan.nextInt();
        C = scan.nextInt();
        letters = new char[C];
        pwd = new char[L];
        for (int i = 0; i < C; i++) letters[i] = scan.next().charAt(0);
    }

    static boolean able(char[] arr) {
        boolean ret = false;
        int vowel = 0;
        int consonant = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
                vowel++;
            } else {
                consonant++;
            }
        }
        if (vowel >= 1 && consonant >= 2) ret = true;
        return ret;
    }

    static void rec_func(int k) {
        if (k == L) {
            if (able(pwd)) {
                for (int i = 0; i < L; i++) sb.append(pwd[i]);
                sb.append('\n');
            }
        } else {
            for (int i = 0; i < C; i++) {
                if (k == 0) {
                    pwd[k] = letters[i];
                    rec_func(k + 1);
                } else {
                    if (pwd[k - 1] < letters[i]) {
                        pwd[k] = letters[i];
                        rec_func(k + 1);
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        input();
        Arrays.sort(letters);
        rec_func(0);
        System.out.println(sb.toString());
    }
}
