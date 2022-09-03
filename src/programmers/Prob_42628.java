package programmers;

import java.util.*;

public class Prob_42628 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[]{"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"})));
        System.out.println(Arrays.toString(s.solution(new String[]{"I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"})));
    }

    static class Solution {
        /*
        I ���� = ť�� ���� ����
        D 1 ť���� �ִ� ����
        D -1 ť���� �ּڰ� ����
         */
        public int[] solution(String[] operations) {
            //�ּ�, �ִ븦 �� ���ϱ� ���ؼ� ���ϴ� �ε����� ���ٰ����� ArrayList ���
            List<Integer> list = new LinkedList<>();
            for (String operation : operations) {
                String op = operation.split(" ")[0];
                int num = Integer.parseInt(operation.split(" ")[1]);
                if (op.equals("I")) {
                    list.add(num);
                } else if (op.equals("D")) {
                    if (list.isEmpty()) continue;
                    //�ִ�, �ּҸ� ���ϱ����� ����
                    list.sort(Comparator.comparingInt(o -> o));
                    if (num == 1) {
                        list.remove(list.size() - 1);
                    } else {
                        list.remove(0);
                    }
                }
            }
            if (!list.isEmpty()) {
                list.sort(Comparator.comparingInt(o -> o));
                return new int[]{list.get(list.size() - 1), list.get(0)};
            }
            return new int[]{0,0};
        }
    }
}
