package programmers.kakao;

import java.util.*;

public class Prob_17680 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(0, new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
    }

    static class Solution {
        public int solution(int cacheSize, String[] cities) {
            //캐쉬의 최대크기가 0일 경우 도시의 수 * 5 를 반환한다.
            if(cacheSize == 0) return cities.length * 5;
            int answer = 0;
            Queue<String> cache = new LinkedList<>();

            for (int i = 0; i < cities.length; i++) {
                cities[i] = cities[i].toUpperCase();
            }

            for (String city : cities) {
                //캐쉬에 존재할 경우
                if(cache.contains(city)){
                    answer++;
                    //큐에서 지우고 다시 넣는다 ( 제일 최근에 사용되었기 때문에 제일 나중에 제거되야 하기 때문에 )
                    cache.remove(city);
                    cache.offer(city);
                }
                //캐쉬에 없을 경우
                else {
                    //캐쉬가 꽉찼을 때 제일 오래동안 쓰지 않은 것을 뺀다.
                    if(cache.size() == cacheSize){
                        cache.poll();
                        cache.offer(city);
                        answer += 5;
                    }
                    //캐쉬가 꽉 차지 않았을 때
                    else {
                        cache.offer(city);
                        answer += 5;
                    }
                }
            }
            return answer;
        }
    }
}
