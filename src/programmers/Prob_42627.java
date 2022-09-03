package programmers;

import java.util.*;

public class Prob_42627 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{0, 3}}));
    }

    static class Solution {
        /*
        �۾��� �Ϸ������ ���Ͽ� �ɸ� �ð��� ����� �ٿ��� ( �۾��� �����ϰ� ���� ���� ������ ���� ���� �۾����� ó�� )
        ��û ~ ���� �ð����� ��� ���ϰ� ��

        �ð����� ��û�� �۾� �з�
        ���� �۾��� �����ð� ������ ��û�� �۾��� ������ ���� ���� ���� �� �ִ� �۾� ����
        ���� �ð����� ��û�� ��� �۾��� Queue�� ���� ���� �� �ɸ��ð��� ���� ���� ������ ������ ù��° �۾�����
         */
        public int solution(int[][] jobs) {
            //��û�ð������� ����
            Arrays.sort(jobs, (o1, o2) -> (o1[0] == o2[0]) ? o1[1] - o2[1] : o1[0] - o2[0]);

            //�۾��ð��� ���� ������ ���� ( �۾��ð��� ��ٴ°� ���� �۾��� ��� �ð��� ��ٴ� �� )
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

            int total = 0, now = 0, idx = 0;

            while (idx < jobs.length) {

                //now ���� ���߿� ��û�� �۾��� �� ��� �۾��� pq�� �ִ´�
                while (idx < jobs.length) {
                    int request = jobs[idx][0];
                    if (request > now) break;
                    pq.offer(jobs[idx++]);
                }

                //��� �۾��� �Ϸ�Ǿ��ų� pq�� �� �����϶�
                if (idx == jobs.length) {
                    while (!pq.isEmpty()) {
                        int[] done = pq.poll();
                        total += now + done[1] - done[0];
                        now += done[1];
                    }
                    break;
                }

                //�ƹ� �۾��� ���� ��
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
