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

        예외케이스 존재함 ( 실패 )

        제일 가깝지 않은 곳으로 갔다와야하는 경우도 잇음 ( ABABAAAAABA )

         */
        public int failure(String name) {
            //커서가 들려야하는 곳
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
                //가장 적은 조작으로 이동할 수 있는 곳으로 이동
                int minMove = moveNearest(cursor, needVisit);

                total += Math.abs(minMove); //역순일 시 음수이기 때문에;
                cursor += minMove;

                //커서가 0보다 작을 경우 문자길이를 더해준다.
                if (cursor < 0) cursor = name.length() + cursor;

                //해당 커서에 알파벳을 가장 적은 조작으로 생성
                total += makeAlphabet(name.charAt(cursor));
                needVisit[cursor] = false;
                notA--;
            }

            return total;
        }

        public int success(String name) {
            //방문이 필요한 곳 확인용
            boolean[] needVisit = new boolean[name.length()];
            //수정이 필요한 갯수
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

            //순서
            int inOrder = findInOrder(cursor, needVisit);
            if(inOrder != -1){
                int newCursor = (cursor + inOrder >= name.length()) ? cursor + inOrder - name.length() : cursor + inOrder;
                needVisit[newCursor] = false;
                recursive(newCursor, total + inOrder + makeAlphabet(name.charAt(newCursor)), notA - 1, needVisit, name);
                needVisit[newCursor] = true;
            }

            //역순
            int reverse = findReverse(cursor, needVisit);
            if(reverse != 1){
                int newCursor = (cursor + reverse < 0) ? cursor + reverse + name.length() : cursor + reverse;
                needVisit[newCursor] = false;
                recursive(newCursor, total + Math.abs(reverse) +  makeAlphabet(name.charAt(newCursor)), notA - 1, needVisit, name);
                needVisit[newCursor] = true;
            }
        }

        //몇번째 뒤에 변경해야할 문자가 있는지 ( 순서대로 )
        int findInOrder(int i, boolean[] needVisit) {
            int move = 0;
            int len = needVisit.length;

            //문자길이의 반보다 많이 움직이면 역순이 빠름
            while (move < len) {
                //인덱스가 최대값을 넘을 경우 i에서 len 을 뺀다
                if (needVisit[(i >= len) ? i - len : i]) {
                    return move;
                }
                i++;
                move++;
            }

            //바꿔야할 문자를 찾지 못한경우
            return -1;
        }

        //몇번째 뒤에 변경해야할 문자가 있는지 ( 역순으로 )
        int findReverse(int i, boolean[] needVisit) {
            int move = 0;
            int len = needVisit.length;

            //문자길이의 반보다 많이 움직이면 역순이 빠름
            while (move < len) {
                //인덱스가 0 보다 작을 경우 i에 len 을 더한다.
                if (needVisit[(i < 0) ? len + i : i]) return move * -1;
                i--;
                move++;
            }

            //바꿔야할 문자를 찾지 못한경우
            return 1;
        }

        //제일 빠르게 문자 만들때 필요한 횟수
        int makeAlphabet(char c) {
            int inOrder = c - 'A';
            return (inOrder > 12) ? 26 - inOrder : inOrder;
        }



        //순서와 역순중 더 빨리 도착가능한 곳
        int moveNearest(int cursor, boolean[] needVisit) {
            int moveOrder = findInOrder(cursor, needVisit);
            int moveReverse = findReverse(cursor, needVisit);

            if (moveOrder == 0 && moveReverse == 0) {
                //더 가야할 곳이 없는 경우
                return 0;
            } else {
                return (moveOrder < Math.abs(moveReverse)) ? moveOrder : moveReverse;
            }
        }


    }

}
