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

        i ��° ���ڸ� ���� �Ⱦ����� ���

        number�� 100�� �ڸ� ���� �����ϱ⿡ ���ڷ� ó���ϸ� �ȵ� ( �ڷ��� ���� �ʰ� )

         */
        int result = 0;

        /*
        ���п���

        number�� 100�� �ڸ� ���� �����ϱ⿡ ���ڷ� ó���ϸ� �ȵ� ( �ڷ��� ���� �ʰ� )
        ��͵� stackOverFlow �߻�

         */
        public String failure(String number, int k) {
            StringBuilder sb = new StringBuilder();
            recursive(0, sb, number, number.length() - k);
            return result + "";
        }

        void recursive(int idx, StringBuilder now, String number, int k) {
            //���ڰ� �ϼ����� ��
            if (now.length() == k) {
                result = Math.max(result, Integer.parseInt(now.toString()));
                return;
            }

            //���ڰ� �ϼ����� �ʾҴµ� ������ ���ڱ��� �������� ��
            if (idx == number.length()) return;

            now.append(number.charAt(idx));
            recursive(idx + 1, now, number, k);
            now.deleteCharAt(now.length() - 1);
            recursive(idx + 1, now, number, k);
        }


        public String success(String number, int k) {
            StringBuilder sb = new StringBuilder();

            /*
            ���ܾ��ϴ� ���ڰ��� ���ܵΰ� �� �A���������� ���� ū ���� ���Ѵ�.
             */

            //�̾ƾ��ϴ� ������ ����
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
