package programmers.kakao;

import java.util.*;

public class Prob_64065 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}")));
        System.out.println(Arrays.toString(s.solution("{{1,2,3},{2,1},{1,2,4,3},{2}}")));
        System.out.println(Arrays.toString(s.solution("{{20,111},{111}}")));
        System.out.println(Arrays.toString(s.solution("{{123}}")));
        System.out.println(Arrays.toString(s.solution("{{4,2,3},{3},{2,3,4,1},{2,3}}")));
    }

    static class Solution {
        public int[] solution(String s) {
            StringBuilder sb = new StringBuilder();

            //예시 : {{2},{2,1},{2,1,3},{2,1,3,4}}

            String s1 = s.substring(1, s.length() - 1);
            /*
                맨처음  "{"  와 맨마지막 "}" 을 삭제해준다
                결과 :  {2},{2,1},{2,1,3},{2,1,3,4}
            */

            String[] split = s1.split("},\\{");
            /*
                " },{ " 기준으로 나누어준다
                결과 : {2  /  2,1  /  2,1,3  /  2,1,3,4}

                첫번째에는 "{" , 마지막에는 "}"가 포함되어 있다.
            */

            List<Integer>[] tuples = new List[split.length];
            for (int i = 0; i < tuples.length; i++) {
                tuples[i] = new ArrayList<>();
            }

            for (int i = 0; i < split.length; i++) {
                //StringBuilder 초기화 이후 삽입
                sb.setLength(0);
                sb.append(split[i]);
                //첫번재 일때 "{"를 지워주기 위해 제일 앞에있는 문자를 삭제
                if (i == 0) {
                    sb.delete(0, 1);
                }
                //마지막 일때 마지막 "}" 를 지워주기 위해 제일 뒤에 있는 문자를 삭제
                if (i == split.length - 1) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                //"," 기준으로 잘라서 얻은 숫자를 tuples 에 얻는다

                for (String num : sb.toString().split(",")) {
                    tuples[i].add(Integer.parseInt(num));
                }
            }

            //key =  숫자, value = 숫자와 갯수를 가지고 있는 Number객체
            Map<Integer, Number> map = new HashMap<>();

            //튜플들을 돌아다니며 모두 삽입하여 해당숫자가 몇개가 사용되었는지를 확인한다.
            for (List<Integer> tuple : tuples) {
                for (Integer integer : tuple) {
                    if (!map.containsKey(integer)) map.put(integer, new Number(integer, 0));
                    map.get(integer).count++;
                }
            }

            //Number 객체를 가지는 리스트에 map 의 value 를 모두 넣어준다.
            List<Number> numberList = new ArrayList<>();
            map.values().iterator().forEachRemaining(o -> numberList.add(o));

            //객체의 갯수를 기준으로 내림차순 정렬해준다.
            numberList.sort((Number1, Number2) -> Number2.count - Number1.count);

            //Number.count 를 int 로 변화하여 만든 배열을 반환
            return numberList.stream().mapToInt(Number -> Number.num).toArray();

        }

        //숫자와 숫자가 등장한 갯수를 가지고 있는 Number 객체
        class Number {
            int num;
            int count;

            public Number(int num, int count) {
                this.num = num;
                this.count = count;
            }

            public String toString() {
                return num + " : " + count;
            }
        }

    }
}
