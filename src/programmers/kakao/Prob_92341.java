package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/92341

import java.util.*;

public class Prob_92341 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(
                new int[]{180, 5000, 10, 600},
                new String[]{
                        "05:34 5961 IN",
                        "06:00 0000 IN",
                        "06:34 0000 OUT",
                        "07:59 5961 OUT",
                        "07:59 0148 IN",
                        "18:59 0000 IN",
                        "19:09 0148 OUT",
                        "22:59 5961 IN",
                        "23:00 5961 OUT"
                }
        )));
    }

    static class Solution {
        public int[] solution(int[] fees, String[] records) {
            int startTime = fees[0], startPrice = fees[1], time = fees[2], price = fees[3];
            HashMap<String, Car> map = new HashMap<>();

            for (String record : records) {
                String[] split = record.split("\\s");
                //map.key 에 차량이 없으면 삽입
                if (!map.containsKey(split[1])) map.put(split[1], new Car(split[1]));
                Car car = map.get(split[1]);
                if (split[2].equals("IN")) {
                    car.in = split[0];
                } else {
                    //출차시간이 정해졋을 때 시간 추가
                    car.out = split[0];
                    car.totalTime += car.inOut();
                    car.in = null;
                    car.out = null;
                }
            }

            //입차시간은 있는데 출차시간이 없을 경우 출차를 23:59로 설정 후 시간 ㅊ추가
            for (Car car : map.values()) {
                if (car.notExit()) {
                    car.out = "23:59";
                    car.totalTime += car.inOut();
                }
                car.calculate(startTime, startPrice, time, price);
            }

            //carList 를 차량번호로 정렬
            List<Car> carList = new ArrayList<>(map.values());
            carList.sort(Comparator.comparingInt(o -> o.num));

            int[] answer = new int[carList.size()];
            for (int i = 0; i < answer.length; i++) answer[i] = carList.get(i).totalPrice;
            return answer;
        }

        class Car {
            int totalTime = 0;
            int totalPrice = 0;
            int num;
            String in;
            String out;

            Car(String num) {
                this.num = Integer.parseInt(num);
            }

            boolean notExit() {
                return (in != null && out == null);
            }

            int inOut() {
                return timeToNum(out) - timeToNum(in);
            }

            int timeToNum(String num) {
                int ret = 0;
                String[] split = num.split(":");
                ret += Integer.parseInt(split[0]) * 60;
                ret += Integer.parseInt(split[1]);
                return ret;
            }

            void calculate(int startTime, int startPrice, int time, int price) {
                totalPrice = startPrice;
                if (totalTime > startTime) {
                    int left = totalTime - startTime;
                    int divide = (left % time == 0) ? left / time : left / time + 1;
                    totalPrice += divide * price;
                }
            }
        }
    }
}
