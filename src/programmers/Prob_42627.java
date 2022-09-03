package programmers;

import java.util.*;

public class Prob_42627 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{0, 3}}));
    }

    static class Solution {
        /*
        작업의 완료순서를 정하여 걸린 시간의 평균을 줄여라 ( 작업을 수행하고 있지 않을 때에는 먼저 들어온 작업부터 처리 )
        요청 ~ 종료 시간들을 모두 합하게 됨

        시간별로 요청된 작업 분류
        이전 작업이 끝난시간 전으로 요청된 작업중 총합이 제일 적게 나올 수 있는 작업 진행
        지정 시간전에 요청된 모든 작업을 Queue에 넣은 이후 총 걸릴시간이 제일 적은 순서로 정렬후 첫번째 작업진행
         */
        public int solution(int[][] jobs) {
            //요청시간순으로 정렬
            Arrays.sort(jobs, (o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);

            //작업시간이 적은 순으로 정렬 ( 작업시간이 길다는건 다음 작업의 대기 시간이 길다는 뜻 )
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            int total = 0, now = 0, idx = 0;

            while (idx < jobs.length) {

                //now 보다 나중에 요청된 작업을 뺀 모든 작업을 pq에 넣는다
                while (idx < jobs.length) {
                    int request = jobs[idx][0];
                    if (request > now) break;
                    pq.offer(jobs[idx++]);
                }

                //모든 작업이 완료되었거나 pq에 들어간 상태일때
                if (idx == jobs.length) {
                    while (!pq.isEmpty()) {
                        int[] done = pq.poll();
                        total += now + done[1] - done[0];
                        now += done[1];
                    }
                    break;
                }

                //아무 작업도 없을 때
                if (pq.size() == 0) {
                    int request = jobs[idx][0];
                    now = request;
                    pq.offer(jobs[idx++]);
                }

                int[] done = pq.poll();
                total += now + done[1] - done[0];
                now += done[1];
            }
            return total / jobs.length;
        }
    }
}
