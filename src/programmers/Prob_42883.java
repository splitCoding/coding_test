package programmers;

public class Prob_42883 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.success("1924", 3));
        System.out.println(s.success("1231234", 3));
        System.out.println(s.success("4177252841", 4));
    }

    static class Solution {
        /*

        i 번째 숫자를 쓸때 안쓸때로 재귀

        number가 100만 자리 까지 가능하기에 숫자로 처리하면 안됨 ( 자료형 범위 초과 )

         */
        int result = 0;

        /*
        실패원인

        number가 100만 자리 까지 가능하기에 숫자로 처리하면 안됨 ( 자료형 범위 초과 )
        재귀도 stackOverFlow 발생

         */
        public String failure(String number, int k) {
            StringBuilder sb = new StringBuilder();
            recursive(0, sb, number, number.length() - k);
            return result + "";
        }

        void recursive(int idx, StringBuilder now, String number, int k) {
            //숫자가 완성됐을 때
            if (now.length() == k) {
                result = Math.max(result, Integer.parseInt(now.toString()));
                return;
            }

            //숫자가 완성되지 않았는데 마지막 숫자까지 도달했을 때
            if (idx == number.length()) return;

            now.append(number.charAt(idx));
            recursive(idx + 1, now, number, k);
            now.deleteCharAt(now.length() - 1);
            recursive(idx + 1, now, number, k);
        }


        public String success(String number, int k) {
            StringBuilder sb = new StringBuilder();

            /*
            남겨야하는 숫자갯수 남겨두고 를 뺸나머지에서 제일 큰 수를 택한다.
             */

            //뽑아야하는 숫자의 갯수
            int needToLeft = number.length() - k;

            int start = 0;
            int end = number.length() - needToLeft + 1;

            while(sb.length() < number.length() - k){
                int max = -1, idx = -1;
                for(int i=start; i < end;i++){
                    if(max < number.charAt(i) - '0'){
                        max = number.charAt(i) - '0';
                        idx = i;
                    }
                }
                needToLeft--;
                start = idx + 1;
                end = number.length() - needToLeft + 1;
                sb.append(max);
            }

            return sb.toString();
        }

    }
}
