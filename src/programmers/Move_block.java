package programmers;

import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/60063

public class Move_block {
    public static void main(String[] args) {
        Solution_modified s = new Solution_modified();
        System.out.println(s.solution(new int[][]{{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}));
    }

    static class Solution_modified {
        class Robot {
            Point pivot, p;
            int dir, time;

            Robot(Point pivot, Point p, int dir, int time) {
                this.pivot = pivot;
                this.p = p;
                this.dir = dir; // 0 : pivot 왼쪽에 p , 1 : pivot 위에 p , 2 : pivot 오른쪽에 p, 3 : pivot 아래쪽에 p
                this.time = time;
            }
        }

        class Point {
            int r, c;

            Point(int i, int j) {
                r = i;
                c = j;
            }
        }

        int n, answer = Integer.MAX_VALUE;
        int[][] board;
        boolean[][][] is_visited;

        boolean is_able(int r, int c) {
            return (r < 0 || c < 0 || r >= n || c >= n || board[r][c] == 1) ? false : true;
        }

        void pivot_base_spin(Robot R, Queue<Robot> q) {
            int new_dir, check_r, check_c, new_r, new_c;
            //시계방향
            new_dir = (R.dir + 1) % 4;
            check_r = R.p.r + check[new_dir][0];
            check_c = R.p.c + check[new_dir][1];
            new_r = R.p.r + spin1[new_dir][0];
            new_c = R.p.c + spin1[new_dir][1];
            if (is_able(check_r, check_c) && is_able(new_r, new_c) && !is_visited[R.pivot.r][R.pivot.c][new_dir]) {
                is_visited[R.pivot.r][R.pivot.c][new_dir] = true;
                q.offer(new Robot(R.pivot, new Point(new_r, new_c), new_dir, R.time + 1));
            }
            //반시계방향
            new_dir = (R.dir - 1 == -1) ? 3 : R.dir - 1;
            check_r = R.p.r + check[new_dir][0];
            check_c = R.p.c + check[new_dir][1];
            new_r = R.p.r + spin2[new_dir][0];
            new_c = R.p.c + spin2[new_dir][1];
            if (is_able(check_r, check_c) && is_able(new_r, new_c) && !is_visited[R.pivot.r][R.pivot.c][new_dir]) {
                is_visited[R.pivot.r][R.pivot.c][new_dir] = true;
                q.offer(new Robot(R.pivot, new Point(new_r, new_c), new_dir, R.time + 1));
            }
        }

        void p_base_spin(Robot R, Queue<Robot> q) {
            int new_dir, check_r, check_c, new_r, new_c;
            //반대인 경우
            int reversed_dir = (R.dir + 2) % 4;
            is_visited[R.p.r][R.p.c][reversed_dir] = true;
            //시계방향
            new_dir = (reversed_dir + 1) % 4;
            check_r = R.pivot.r + check[new_dir][0];
            check_c = R.pivot.c + check[new_dir][1];
            new_r = R.pivot.r + spin1[new_dir][0];
            new_c = R.pivot.c + spin1[new_dir][1];
            if (is_able(check_r, check_c) && is_able(new_r, new_c) && !is_visited[R.p.r][R.p.c][new_dir]) {
                is_visited[R.p.r][R.p.c][new_dir] = true;
                q.offer(new Robot(R.p, new Point(new_r, new_c), new_dir, R.time + 1));
            }
            //반시계방향
            new_dir = (reversed_dir - 1 == -1) ? 3 : reversed_dir - 1;
            check_r = R.pivot.r + check[new_dir][0];
            check_c = R.pivot.c + check[new_dir][1];
            new_r = R.pivot.r + spin2[new_dir][0];
            new_c = R.pivot.c + spin2[new_dir][1];
            if (is_able(check_r, check_c) && is_able(new_r, new_c) && !is_visited[R.p.r][R.p.c][new_dir]) {
                is_visited[R.p.r][R.p.c][new_dir] = true;
                q.offer(new Robot(R.p, new Point(new_r, new_c), new_dir, R.time + 1));
            }
        }

        void move(Robot R, Queue<Robot> q) {
            for (int i = 0; i < 4; i++) {
                int pivot_new_r = R.pivot.r + move[i][0], pivot_new_c = R.pivot.c + move[i][1];
                int p_new_r = R.p.r + move[i][0], p_new_c = R.p.c + move[i][1];
                if (is_able(pivot_new_r, pivot_new_c) && is_able(p_new_r, p_new_c) && !is_visited[pivot_new_r][pivot_new_c][R.dir]) {
                    is_visited[pivot_new_r][pivot_new_c][R.dir] = true;
                    q.offer(new Robot(new Point(pivot_new_r, pivot_new_c), new Point(p_new_r, p_new_c), R.dir, R.time + 1));
                }
            }
        }

        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동
        int[][] spin1 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}; // 시계방향 회전시 해당 인덱스일때 얼마를 더해야하는지
        int[][] spin2 = {{1, -1}, {-1, -1}, {-1, 1}, {1, 1}}; // 반시계방향 회전시 해당 인덱스일때 얼마를 더해야하는지
        int[][] check = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; // 회전시 해당 인덱스일 때 거쳐야하는 위치

        public int solution(int[][] board) {
            this.board = board;
            n = board.length;
            is_visited = new boolean[n][n][4]; // [ pivot.r ] [ pivot.c ] [ 방향 ] 일때
            Queue<Robot> q = new LinkedList<>();
            is_visited[0][0][2] = true;
            q.offer(new Robot(new Point(0, 0), new Point(0, 1), 2, 0));
            while (!q.isEmpty()) {
                Robot R = q.poll();
                if (R.pivot.r == n - 1 && R.pivot.c == n - 1) answer = Math.min(answer, R.time);
                if (R.p.r == n - 1 && R.p.c == n - 1) answer = Math.min(answer, R.time);
                move(R, q);
                pivot_base_spin(R, q);
                p_base_spin(R, q);
            }
            return answer;
        }
    }

    static class Solution_before_modified {
        class Robot {
            Point pivot;
            Point p;
            int dir, time;

            Robot(Point p1, Point p2, int dir, int time) {
                pivot = p1;
                p = p2;
                this.dir = dir;
                this.time = time;
            }
        }

        class Point {
            int r, c;

            Point(int i, int j) {
                r = i;
                c = j;
            }
        }

        int n, answer = Integer.MAX_VALUE;
        int[][] board;
        boolean[][][] is_visited;

        boolean is_able(int r, int c) {
            if (r < 0 || c < 0 || r >= n || c >= n || board[r][c] == 1) {
                return false;
            }
            return true;
        }

        void pivot_base_spin_clockwise(Robot R, Queue<Robot> q) {
            int new_dir = (R.dir + 1) % 4;
            int check_r = R.p.r + check[new_dir][0];
            int check_c = R.p.c + check[new_dir][1];
            int new_r = R.p.r + spin1[new_dir][0];
            int new_c = R.p.c + spin1[new_dir][1];
//            System.out.printf("[%d][%d][%d] - [%d][%d] clockwise [%d][%d][%d]", R.pivot.r, R.pivot.c, R.dir, R.p.r, R.p.c, R.pivot.r, R.pivot.c, new_dir);
            if (!is_able(check_r, check_c)) {
//                System.out.println(" 1 Check Fail");
                return;
            }
            if (!is_able(new_r, new_c)) {
//                System.out.println(" 2 Spin Fail");
                return;
            }
            if (is_visited[R.pivot.r][R.pivot.c][new_dir]) {
//                System.out.println(" 3 Already Find");
                return;
            }
//            System.out.println(" 4 Success");
            is_visited[R.pivot.r][R.pivot.c][new_dir] = true;
            q.offer(new Robot(R.pivot, new Point(new_r, new_c), new_dir, R.time + 1));
//            System.out.printf("1 offer [%d][%d][%d]\n", R.pivot.r, R.pivot.c, new_dir);
        }

        void pivot_base_spin_counterclockwise(Robot R, Queue<Robot> q) {
            //R.pivot 기준
            int new_dir = R.dir - 1;
            if (new_dir == -1) new_dir = 3;
            int check_r = R.p.r + check[new_dir][0];
            int check_c = R.p.c + check[new_dir][1];
            int new_r = R.p.r + spin2[new_dir][0];
            int new_c = R.p.c + spin2[new_dir][1];
//            System.out.printf("[%d][%d][%d] - [%d][%d] c_clockwise [%d][%d][%d]", R.pivot.r, R.pivot.c, R.dir, R.p.r, R.p.c, R.pivot.r, R.pivot.c, new_dir);
            if (!is_able(check_r, check_c)) {
//                System.out.println(" 1 Check Fail");
                return;
            }
            if (!is_able(new_r, new_c)) {
//                System.out.println(" 2 Spin Fail");
                return;
            }
            if (is_visited[R.pivot.r][R.pivot.c][new_dir]) {
//                System.out.println(" 3 Already Find");
                return;
            }
//            System.out.println(" 4 Success");
            is_visited[R.pivot.r][R.pivot.c][new_dir] = true;
            q.offer(new Robot(R.pivot, new Point(new_r, new_c), new_dir, R.time + 1));
//            System.out.printf("1 offer [%d][%d][%d]\n", R.pivot.r, R.pivot.c, new_dir);
        }

        void p_base_spin_clockwise(Robot R, Queue<Robot> q) {
            int reversed_dir = (R.dir + 2) % 4;
            is_visited[R.p.r][R.p.c][reversed_dir] = true;
            int new_dir = (reversed_dir + 1) % 4;
            int check_r = R.pivot.r + check[new_dir][0];
            int check_c = R.pivot.c + check[new_dir][1];
            int new_r = R.pivot.r + spin1[new_dir][0];
            int new_c = R.pivot.c + spin1[new_dir][1];
//            System.out.printf("reverse [%d][%d][%d] clockwise [%d][%d][%d]", R.p.r, R.p.c, reversed_dir, R.p.r, R.p.c, new_dir);
            if (!is_able(check_r, check_c)) {
//                System.out.println(" 1 Check Fail");
                return;
            }
            if (!is_able(new_r, new_c)) {
//                System.out.println(" 2 Spin Fail");
                return;
            }
            if (is_visited[R.p.r][R.p.c][new_dir]) {
//                System.out.println(" 3 Already Find");
                return;
            }
//            System.out.println(" 4 Success");
            is_visited[R.p.r][R.p.c][new_dir] = true;
            q.offer(new Robot(R.p, new Point(new_r, new_c), new_dir, R.time + 1));
//            System.out.printf("1 offer [%d][%d][%d]\n", new_r,  new_c, new_dir);
        }

        void p_base_spin_counterclockwise(Robot R, Queue<Robot> q) {
            int reversed_dir = (R.dir + 2) % 4;
            is_visited[R.p.r][R.p.c][reversed_dir] = true;
            int new_dir = reversed_dir - 1;
            if (new_dir == -1) new_dir = 3;
            int check_r = R.pivot.r + check[new_dir][0];
            int check_c = R.pivot.c + check[new_dir][1];
            int new_r = R.pivot.r + spin2[new_dir][0];
            int new_c = R.pivot.c + spin2[new_dir][1];
//            System.out.printf("reverse [%d][%d][%d] - [%d][%d] c_clockwise [%d][%d][%d]", R.p.r, R.p.c, reversed_dir, R.pivot.r, R.pivot.c, R.p.r, R.p.c, new_dir);
            if (!is_able(check_r, check_c)) {
//                System.out.println(" 1 Check Fail");
                return;
            }
            if (!is_able(new_r, new_c)) {
//                System.out.println(" 2 Spin Fail");
                return;
            }
            if (is_visited[R.p.r][R.p.c][new_dir]) {
//                System.out.println(" 3 Already Find");
                return;
            }
//            System.out.println(" 4 Success");
            is_visited[R.p.r][R.p.c][new_dir] = true;
            q.offer(new Robot(R.p, new Point(new_r, new_c), new_dir, R.time + 1));
//            System.out.printf("1 offer [%d][%d][%d]\n", new_r,  new_c, new_dir);
        }

        int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 이동
        int[][] spin1 = {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}}; //시계방향 회전
        int[][] spin2 = {{1, -1}, {-1, -1}, {-1, 1}, {1, 1}}; //반시계방향 회전
        int[][] check = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}}; //회전전에 확인 해야할 위치

        public int solution(int[][] board) {
            this.board = board;
            n = board.length;
            is_visited = new boolean[n][n][4]; //[ row ] [ col ] [ 방향 ] 일때
            Queue<Robot> q = new LinkedList<>();
            is_visited[0][0][2] = true;
            q.offer(new Robot(new Point(0, 0), new Point(0, 1), 2, 0));
            while (!q.isEmpty()) {
                Robot R = q.poll();
                if (R.pivot.r == n - 1 && R.pivot.c == n - 1) answer = Math.min(answer, R.time);
                if (R.p.r == n - 1 && R.p.c == n - 1) answer = Math.min(answer, R.time);
                for (int i = 0; i < 4; i++) {
                    int pivot_new_r = R.pivot.r + move[i][0];
                    int pivot_new_c = R.pivot.c + move[i][1];
                    int p_new_r = R.p.r + move[i][0];
                    int p_new_c = R.p.c + move[i][1];
                    if (!is_able(pivot_new_r, pivot_new_c) || !is_able(p_new_r, p_new_c)) continue;
                    if (is_visited[pivot_new_r][pivot_new_c][R.dir]) continue;
                    is_visited[pivot_new_r][pivot_new_c][R.dir] = true;
                    q.offer(new Robot(new Point(pivot_new_r, pivot_new_c), new Point(p_new_r, p_new_c), R.dir, R.time + 1));
                }
                pivot_base_spin_clockwise(R, q);
                pivot_base_spin_counterclockwise(R, q);
                p_base_spin_clockwise(R, q);
                p_base_spin_counterclockwise(R, q);
            }
            return answer;
        }
    }
}
