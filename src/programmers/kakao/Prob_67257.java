package programmers.kakao;


//https://school.programmers.co.kr/learn/courses/30/lessons/67257


import java.util.*;

public class Prob_67257 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution("100-200*300-500+20"));
    }

    static class Solution {
        /*
        숫자들, 3개의 연산자 + - * 로 이루어진 수식이 전달
        연산자의 우선순위를 재정의하여 만들 수 있는 가장 큰 숫자를 제출
        결과가 음수일 시ㅏ 절댓값으로 변환하여 제출 우승자가 제출한 숫자를 상금으로 지급한다.
         */
        Long answer = 0L;
        public long solution(String expression) {
            List<Long> numbers = new ArrayList();
            List<Character> ops = new ArrayList();
            seperate(expression, numbers, ops);

            int[] where = new int[3];
            char[] opArr = {'+', '-', '*'};
            Arrays.fill(where, -1);
            dfs(0, 0, where, opArr, numbers, ops);
            return answer;
        }

        //큐를 사용하여 숫자, 연산자 순으로 넣다가 *가 발견되면 숫자를 빼서 그다음 숫자와 곱한 값으로 넣는다.
        Long calculate(int[] where, char[] opArr, List<Long> numbers, List<Character> ops) {
            List<Long> numList = new ArrayList(numbers);

            List list = new ArrayList();
            int idx = 0;

            while (idx < numbers.size()) {
                list.add(numbers.get(idx));
                if (idx == numbers.size() - 1) break;
                list.add(ops.get(idx));
                idx++;
            }

            for (int i = 0; i < where.length; i++) {
                char op = opArr[where[i]];

                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j) instanceof Character) {
                        if (op == (char) list.get(j)) {
                            if (op == '*') {
                                Long result = (Long) list.get(j - 1) * (Long) list.get(j + 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.add(j - 1, result);
                                j = 0;
                            }
                            if (op == '+') {
                                Long result = (Long) list.get(j - 1) + (Long) list.get(j + 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.add(j - 1, result);
                                j =0;
                            }
                            if (op == '-') {
                                Long result = (Long) list.get(j - 1) - (Long) list.get(j + 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.remove(j - 1);
                                list.add(j - 1, result);
                                j = 0;
                            }
                        }
                    }
                }
            }
            return (Long)list.get(0);
        }

        void dfs(int depth, int used, int[] where, char[] opArr, List<Long> numbers, List<Character> ops) {
            if (depth == where.length) {
                answer = Math.max(answer,  Math.abs(calculate(where, opArr, numbers, ops)));
                return;
            }
            for (int i = 0; i < where.length; i++) {
                //이미 사용한 연산자
                if (where[i] != -1) continue;
                where[i] = depth;
                dfs(depth + 1, used + 1, where, opArr, numbers, ops);
                where[i] = -1;
            }
        }

        void seperate(String s, List<Long> numbers, List<Character> ops) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char now = s.charAt(i);
                if ('0' <= now && now <= '9') {
                    sb.append(now);
                } else {
                    numbers.add(Long.parseLong(sb.toString()));
                    sb.setLength(0);
                    ops.add(now);
                }
            }
            numbers.add(Long.parseLong(sb.toString()));
        }
    }
}





/*


static class Solution {
    /*
    숫자들, 3개의 연산자 + - * 로 이루어진 수식이 전달
    연산자의 우선순위를 재정의하여 만들 수 있는 가장 큰 숫자를 제출
    결과가 음수일 시ㅏ 절댓값으로 변환하여 제출 우승자가 제출한 숫자를 상금으로 지급한다.
     *|/
    public long solution(String expression) {
        StringBuilder sb = new StringBuilder();
        List<Integer> numbers = new ArrayList<>();
        List<Character> ops = new ArrayList<>();

        //1. 숫자와 연산자 분리
        seperate(expression, sb, numbers, ops);

        //2. 연산자의 우선순위를 재정의 하여 음수일 경우 가장 작은, 양수일 경우 가장 큰 숫자를 만든다.
        boolean[] isUsed = new boolean[ops.size()];
        recursive(0, 0, ops, numbers, isUsed, "");

        long answer = 0;
        return answer;
    }


        int calculate(String s, List<Integer> numbers) {
            List<Integer> nums = new ArrayList<>(numbers);
            int sum = 0;

            for (int i = 0; i < s.length(); i++) {
                char op = s.charAt(i);
                if (op == '*') {
                    int put = nums.get(i) * nums.get(i + 1);
                    nums.add(i, put);
                    nums.remove(i + 1);
                    nums.remove(i + 1);
                }
            }

            int idx = 0;
            for (int i = 0; i < s.length(); i++) {
                char op = s.charAt(i);
                if (op == '*') continue;
                if( op == '+'){
                    sum += nums.get(idx) + nums.get(idx + 1);
                } else {
                    sum += nums.get(idx) + nums.get(idx + 1);
                }
            }

            System.out.println(nums);
            return 1;
        }


        //우선 순위를 적용 시키는 법 : 나중에 한번에 계산할때 * 를 찾아서 먼저 계산한다.
        void recursive(int depth, int used, List<Character> ops, List<Integer> numbers, boolean[] isUsed, String s) {
            if (depth == ops.size()) {
                if (used == ops.size()) {
                    calculate(s, numbers);
                }
                return;
            }

            String preS = s;

            for (int i = 0; i < ops.size(); i++) {
                if (isUsed[i]) continue;
                s += ops.get(i);
                isUsed[i] = true;
                recursive(depth + 1, used + 1, ops, numbers, isUsed, s);
                isUsed[i] = false;
                s = preS;
            }

        }

        void seperate(String expression, StringBuilder sb, List<Integer> numbers, List<Character> ops) {
            for (int i = 0; i < expression.length(); i++) {
                char now = expression.charAt(i);
                //숫자일 경우
                if ('0' <= now && now <= '9') {
                    sb.append(now);
                } else {
                    numbers.add(Integer.parseInt(sb.toString()));
                    sb.setLength(0);
                    ops.add(now);
                }
            }
            numbers.add(Integer.parseInt(sb.toString()));
        }
    }
}
 */