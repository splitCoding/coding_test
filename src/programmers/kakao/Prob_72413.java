package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/72413

import java.util.*;


public class Prob_72413 {
    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.solution(6, 4, 6, 2,
                new int[][]{
                        {4, 1, 10}, {3, 5, 24}, {5, 6, 2},
                        {3, 1, 41}, {5, 1, 24}, {4, 6, 50},
                        {2, 4, 66}, {2, 3, 22}, {1, 6, 25}
                }));
        System.out.println(s.solution(7, 3, 4, 1,
                new int[][]{
                        {5, 7, 9}, {4, 6, 4}, {3, 6, 1},
                        {3, 2, 3}, {2, 1, 6}
                }));

        System.out.println(s.solution(6, 4, 5, 6,
                new int[][]{
                        {2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}
                }));
    }

    static class Solution {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.price));

        public int solution(int nodeCount, int start, int aArrive, int bArrive, int[][] prices) {
            int answer = Integer.MAX_VALUE;
            Map<Integer, List<Node>> roads = new HashMap<>();

            for (int[] price : prices) {
                if (!roads.containsKey(price[0])) roads.put(price[0], new ArrayList<>());
                if (!roads.containsKey(price[1])) roads.put(price[1], new ArrayList<>());
                roads.get(price[0]).add(new Node(price[1], price[2]));
                roads.get(price[1]).add(new Node(price[0], price[2]));
            }

            //dp[0]은 start 에서 시작한 각 노드까지의 최소비용
            //dp[1]은 aArrive 에서 시작한 각 노드까지의 최소비용
            //dp[2]은 bArrive 에서 시작한 각 노드까지의 최소비용

            //Integer 최대값으로 모두 초기화
            int[][] dp = new int[3][nodeCount + 1];
            for (int[] ints : dp) Arrays.fill(ints, Integer.MAX_VALUE);

            dijkstra(dp, roads, 0, start);
            dijkstra(dp, roads, 1, aArrive);
            dijkstra(dp, roads, 2, bArrive);

            //i를 기준으로 서로 다른 택시를 탄 비용
            for (int i = 0; i <= nodeCount; i++) answer = Math.min(answer, dp[0][i] + dp[1][i] + dp[2][i]);

            return answer;
        }

        void dijkstra(int[][] dp, Map<Integer, List<Node>> roads, int row, int start) {
            pq.add(new Node(start, 0));
            dp[row][start] = 0;
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                if (dp[row][now.num] < now.price) continue;
                for (Node next : roads.get(now.num)) {
                    //기존보다 더 짧은 경로를 찾은 경우
                    if (dp[row][next.num] > dp[row][now.num] + next.price) {
                        dp[row][next.num] = dp[row][now.num] + next.price;
                        pq.offer(next);
                    }
                }
            }
        }

        class Node {
            int num;
            int price;

            public Node(int num, int price) {
                this.num = num;
                this.price = price;
            }
        }
    }
}
