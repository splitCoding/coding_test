package programmers;

public class Prob_42860 {

    //https://school.programmers.co.kr/learn/courses/30/lessons/42860

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.success("JEROEN"));
        System.out.println(s.success("JAN"));
    }

    static class Solution {

        /*
          1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5
        a b c d e f g h i j k l m n o p q r s t u v w x y z
        a z y x w v u t s r q p o n m l k j i h g f e d c b
         */

        static int result = Integer.MAX_VALUE;

        /*

        �������̽� ������ ( ���� )

        ���� ������ ���� ������ ���ٿ;��ϴ� ��쵵 ���� ( ABABAAAAABA )

         */
        public int failure(String name) {
            //Ŀ���� ������ϴ� ��
            boolean[] needVisit = new boolean[name.length()];
            int notA = 0;
            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != 'A') {
                    needVisit[i] = true;
                    notA++;
                }
            }
            int cursor = 0, total = 0;

            while (notA > 0) {
                //���� ���� �������� �̵��� �� �ִ� ������ �̵�
                int minMove = moveNearest(cursor, needVisit);

                total += Math.abs(minMove); //������ �� �����̱� ������;
                cursor += minMove;

                //Ŀ���� 0���� ���� ��� ���ڱ��̸� �����ش�.
                if (cursor < 0) cursor = name.length() + cursor;

                //�ش� Ŀ���� ���ĺ��� ���� ���� �������� ����
                total += makeAlphabet(name.charAt(cursor));
                needVisit[cursor] = false;
                notA--;
            }

            return total;
        }

        public int success(String name) {
            //�湮�� �ʿ��� �� Ȯ�ο�
            boolean[] needVisit = new boolean[name.length()];
            //������ �ʿ��� ����
            int notA = 0;

            for (int i = 0; i < name.length(); i++) {
                if (name.charAt(i) != 'A') {
                    needVisit[i] = true;
                    notA++;
                }
            }

            recursive(0,0,notA, needVisit, name);

            return result;
        }


        void recursive(int cursor, int total, int notA, boolean[] needVisit, String name) {
            if(notA == 0){
                result = Math.min(result, total);
                return;
            }

            //����
            int inOrder = findInOrder(cursor, needVisit);
            if(inOrder != -1){
                int newCursor = (cursor + inOrder >= name.length()) ? cursor + inOrder - name.length() : cursor + inOrder;
                needVisit[newCursor] = false;
                recursive(newCursor, total + inOrder + makeAlphabet(name.charAt(newCursor)), notA - 1, needVisit, name);
                needVisit[newCursor] = true;
            }

            //����
            int reverse = findReverse(cursor, needVisit);
            if(reverse != 1){
                int newCursor = (cursor + reverse < 0) ? cursor + reverse + name.length() : cursor + reverse;
                needVisit[newCursor] = false;
                recursive(newCursor, total + Math.abs(reverse) +  makeAlphabet(name.charAt(newCursor)), notA - 1, needVisit, name);
                needVisit[newCursor] = true;
            }
        }

        //���° �ڿ� �����ؾ��� ���ڰ� �ִ��� ( ������� )
        int findInOrder(int i, boolean[] needVisit) {
            int move = 0;
            int len = needVisit.length;

            //���ڱ����� �ݺ��� ���� �����̸� ������ ����
            while (move < len) {
                //�ε����� �ִ밪�� ���� ��� i���� len �� ����
                if (needVisit[(i >= len) ? i - len : i]) {
                    return move;
                }
                i++;
                move++;
            }

            //�ٲ���� ���ڸ� ã�� ���Ѱ��
            return -1;
        }

        //���° �ڿ� �����ؾ��� ���ڰ� �ִ��� ( �������� )
        int findReverse(int i, boolean[] needVisit) {
            int move = 0;
            int len = needVisit.length;

            //���ڱ����� �ݺ��� ���� �����̸� ������ ����
            while (move < len) {
                //�ε����� 0 ���� ���� ��� i�� len �� ���Ѵ�.
                if (needVisit[(i < 0) ? len + i : i]) return move * -1;
                i--;
                move++;
            }

            //�ٲ���� ���ڸ� ã�� ���Ѱ��
            return 1;
        }

        //���� ������ ���� ���鶧 �ʿ��� Ƚ��
        int makeAlphabet(char c) {
            int inOrder = c - 'A';
            return (inOrder > 12) ? 26 - inOrder : inOrder;
        }



        //������ ������ �� ���� ���������� ��
        int moveNearest(int cursor, boolean[] needVisit) {
            int moveOrder = findInOrder(cursor, needVisit);
            int moveReverse = findReverse(cursor, needVisit);

            if (moveOrder == 0 && moveReverse == 0) {
                //�� ������ ���� ���� ���
                return 0;
            } else {
                return (moveOrder < Math.abs(moveReverse)) ? moveOrder : moveReverse;
            }
        }


    }

}
