package programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Get_item {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] rectangle = {{1, 1, 7, 4}, {3, 2, 5, 5}, {4, 3, 6, 9}, {2, 6, 8, 8}};
        int characterX = 1;
        int characterY = 3;
        int itemX = 7;
        int itemY = 8;
        System.out.println(s.solution(rectangle, characterX, characterY, itemX, itemY));
    }

    static class Solution {
        public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
            int answer = 0;
            int x_min, x_max, y_min, y_max, x_biggest = 0, y_biggest = 0;
            for (int[] r : rectangle) {
                x_biggest = Math.max(x_biggest, r[2]);
                y_biggest = Math.max(y_biggest, r[3]);
            }
            //사각형 테두리가 몇번 지나가는지 확인
            int[][] check = new int[2 * y_biggest + 1][2 * x_biggest + 1];
            for (int[] r : rectangle) {
                x_min = r[0];
                x_max = r[2];
                y_min = r[1];
                y_max = r[3];
                //가로줄 체크
                for (int x = x_min * 2; x <= x_max * 2; x++) {
                    check[y_min * 2][x]++;
                    check[y_max * 2][x]++;
                }
                //세로줄 체크
                for (int y = y_min * 2; y <= y_max * 2; y++) {
                    if (y == y_min * 2 || y == y_max * 2) continue;
                    check[y][x_min * 2]++;
                    check[y][x_max * 2]++;
                }

            }
            for (int[] r : rectangle) {
                x_min = r[0];
                x_max = r[2];
                y_min = r[1];
                y_max = r[3];
                //사각형에 안에있는 숫자들 다 0으로
                for (int x = x_min * 2 + 1; x < x_max * 2; x++) {
                    for (int y = y_min * 2 + 1; y < y_max * 2; y++) {
                        check[y][x] = 0;
                        check[y][x] = 0;
                    }
                }
            }
            int[][] visited = new int[2 * y_biggest + 1][2 * x_biggest + 1];
            int[][] move = {{1, 0}, {2, 0}, {-1, 0}, {-2, 0}, {0, 1}, {0, 2}, {0, -1}, {0, -2}};
            Queue<Integer> q = new LinkedList<>();
            q.offer(characterY * 2);
            q.offer(characterX * 2);
            visited[characterY * 2][characterX * 2] = 1;
            while (!q.isEmpty()) {
                int r = q.poll();
                int c = q.poll();
                for (int i = 1; i < move.length; i += 2) {
                    int[] m = move[i];
                    int[] pre = move[i - 1];
                    int nr = r + m[0], nc = c + m[1], prer = r + pre[0], prec = c + pre[1];
                    //맵 밖으로 나갔을때
                    if (nr < 0 || nc < 0 || nr >= check.length || nc >= check[0].length) {
//                        System.out.println("Out");
                        continue;
                    }
                    //이미 들린 곳일 때
                    if (visited[nr][nc] != 0) {
//                        System.out.println("Visited");
                        continue;
                    }
                    //갈 수 없는 곳일 때
                    if (check[nr][nc] == 0) {
//                        System.out.println("Disable");
                        continue;
                    }
                    //중간이 비어있는 곳일 때
                    if (check[nr][nc] != 0 && check[prer][prec] == 0) {
//                        System.out.println("Can not Jump");
                        continue;
                    }
                    visited[nr][nc] = visited[r][c] + 1;
                    visited[prer][prec] = visited[r][c] + 1;
                    q.offer(nr);
                    q.offer(nc);
                    if (nr == itemY * 2 && nc == itemX * 2){
                        answer = visited[nr][nc] - 1;
                    }
                }
            }
            return answer;
        }
    }
}
