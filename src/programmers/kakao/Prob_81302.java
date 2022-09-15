package programmers.kakao;

import java.util.*;

public class Prob_81302 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(Arrays.toString(s.solution(new String[][]
                {
                        {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                        {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                        {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                        {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                        {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
                })));
    }

    static class Solution {
        /*
           [5][5]의 대기실이 5개 주어진다.
           응시자들끼리는 맨해튼 거 2이하로 앉으면 안된다.
           사이가 파티션으로 막힌경우는 상관없다.
           P = 응시자, X = 파티션, O = 빈 테이블

           각 대기실별로 거리두기가 지켜지면 1 아니면 0을 반환

         */
        public int[] solution(String[][] places) {
            int[] answer = new int[5];
            List<int[]>[] peopleList = new List[5];

            for (int i = 0; i < peopleList.length; i++) {
                peopleList[i] = new ArrayList<>();
                List<int[]> peoples = findPeople(peopleList[i], places[i]);
                answer[i] = (isPerfect(peoples, places[i])) ? 1 : 0;
            }
            return answer;
        }

        //리스트에 사람의 위치를 저장하여 반환
        List<int[]> findPeople(List<int[]> list, String[] place) {
            for (int i = 0; i < place.length; i++) {
                for (int j = 0; j < place[i].length(); j++) {
                    if (place[i].charAt(j) == 'P') {
                        list.add(new int[]{i, j});
                    }
                }
            }
            return list;
        }

        boolean isBlocked(int[] p1, int[] p2, String[] place) {
            int minRow = Math.min(p1[0], p2[0]), maxRow = Math.max(p1[0], p2[0]);
            int minCol = Math.min(p1[1], p2[1]), maxCol = Math.max(p1[1], p2[1]);
            //가로로 1자인 경우
            if (minRow == maxRow) {
                return place[minRow].charAt(minCol + 1) == 'X';
            }
            //세로로 1자인 경우
            if (minCol == maxCol) {
                return place[minRow + 1].charAt(minCol) == 'X';
            }
            //2 * 2 인 경우
            return ( place[minRow].charAt(minCol) == 'X' && place[maxRow].charAt(maxCol) == 'X' )  || (place[minRow].charAt(maxCol) == 'X' && place[maxRow].charAt(minCol) == 'X');
        }

        boolean isPerfect(List<int[]> peoples, String[] place) {
            for (int j = 0; j < peoples.size(); j++) {
                int[] p1 = peoples.get(j);
                for (int k = 0; k < peoples.size(); k++) {
                    //같은 사람인 경우
                    if (j == k) continue;

                    int[] p2 = peoples.get(k);
                    //맨해틀 거리 안이고 중간이 막혀있지 않을 경우
                    if (manhattan(p1, p2) == 1) {
                        return false;
                    }
                    if (manhattan(p1, p2) == 2 && !isBlocked(p1, p2, place)) {
                        return false;
                    }
                }
            }
            return true;
        }

        boolean able(int[] p1, int[] p2) {
            return manhattan(p1, p2) > 2;
        }

        int manhattan(int[] a1, int[] a2) {
            return Math.abs(a2[0] - a1[0]) + Math.abs(a2[1] - a1[1]);
        }
    }
}
