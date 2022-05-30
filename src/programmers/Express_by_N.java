package programmers;

import java.util.HashSet;

public class Express_by_N {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(5, 12));
        System.out.println(s.solution(2, 11));
    }

    static class Solution {
        public int solution(int N, int number) {
            HashSet<Integer>[] dy = new HashSet[9];
            for (int i = 0; i < 9; i++) dy[i] = new HashSet<>();
            //�������� ���� ���� �߰�
            for (int i = 1; i < 9; i++) {
                String num = "";
                for (int j = 1; j <= i; j++) num += N;
                dy[i].add(Integer.parseInt(num));
            }
            //1���� ���� �� �մ� ���
            if (number == N) return 1;

            //8�� �̳��� ����� �ִ� ���
            for (int i = 2; i <= 8; i++) {
                //dy[4] = dy[1] �� dy[3]�� ����
                //dy[4] = dy[2] �� dy[2]�� ����
                //dy[4] = dy[3] �� dy[1]�� ����
                for (int pre = 1; pre < i; pre++) {
                    for (int num : dy[pre]) {
                        for (int num2 : dy[i - pre]) {
                            dy[i].add(num + num2);
                            dy[i].add(num - num2);
                            dy[i].add(num * num2);
                            if (num2 != 0) dy[i].add(num / num2);
                            if (dy[i].contains(number)) return i;
                        }
                    }
                }
            }

            //8�� �̳��� ���� �� ���� ���
            return -1;
        }
    }
}


