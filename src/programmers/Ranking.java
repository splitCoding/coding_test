package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/49191

import java.util.*;

public class Ranking {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] results = {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}};
        System.out.println(s.solution(5, results));
    }

    static class Solution {
        class Player {
            int N, connected = 0;

            Player(int i) {
                N = i;
            }

            HashSet<Integer> up = new HashSet<>(), down = new HashSet<>();

            void addUp(int i) {
                up.add(i);
                update();
            }

            void addUp(HashSet<Integer> hs) {
                up.addAll(hs);
                update();
            }

            void addDown(int i) {
                down.add(i);
                update();
            }

            void addDown(HashSet<Integer> hs) {
                down.addAll(hs);
                update();
            }

            void update() {
                connected = up.size() + down.size();
            }

            boolean fixed() {
                return connected == (N - 1);
            }
        }

        public int solution(int n, int[][] results) {
            int answer = 0;

            Player[] players = new Player[n + 1];
            for (int i = 0; i <= n; i++) players[i] = new Player(n);
            for (int[] result : results) {
                players[result[0]].addDown(result[1]);
                players[result[1]].addUp(result[0]);
            }

            for (int i = 1; i <= n; i++) {
                Player p = players[i];
                //자신보다 순위가 낮은 선수의 Up에 자신보다 순위가 높은 선수를 넣는다.
                for (int d : p.down) {
                    if (players[d].fixed()) continue;
                    players[d].addUp(p.up);
                }
                //자신보다 순위가 높은 선수의 Down에 자신보다 순위가 낮은 선수를 넣는다.
                for (int u : p.up) {
                    if (players[u].fixed()) continue;
                    players[u].addDown(p.down);
                }
            }

            for (Player p : players) if (p.fixed()) answer++;
            return answer;
        }
    }
}
