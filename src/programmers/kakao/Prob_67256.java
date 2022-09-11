package programmers.kakao;

//https://school.programmers.co.kr/learn/courses/30/lessons/67256

public class Prob_67256 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }

    static class Solution {
        StringBuilder sb = new StringBuilder();
        //왼손의 시작 좌표
        int lRow = 3, lCol = 0;
        //오른손의 시작 좌표
        int rRow = 3, rCol = 2;

        public String solution(int[] numbers, String hand) {
            int[][] keypad = {
                    {1, 2, 3},
                    {4, 5, 6},
                    {7, 8, 9},
                    {-1, 0, -1}
            };

            for (int n : numbers) {
                //n이 있는 좌표
                int[] dest = findTarget(n);

                if (n == 1 || n == 4 || n == 7) {
                    //1, 4, 7 일 때 왼손이 움직여야 함
                    lMoving(dest);
                } else if (n == 3 || n == 6 || n == 9) {
                    //3, 6, 9 일 때 오른손이 움직여야 함
                    rMoving(dest);
                } else {
                    //더까운 손이 움직이여함
                    int lMoves = countMoves(lRow, lCol, n);
                    int rMoves = countMoves(rRow, rCol, n);

                    //둘다 같은 거리에 있는경우 자기의 주손을 사용해야함
                    if (rMoves == lMoves) {
                        if (hand.equals("left")) {
                            lMoving(dest);
                        } else {
                            rMoving(dest);
                        }
                    } else {
                        if (lMoves > rMoves) {
                            rMoving(dest);
                        } else {
                            lMoving(dest);
                        }
                    }
                }
            }

            return sb.toString();
        }

        //왼손이 움직인다.
        void lMoving(int[] dest) {
            sb.append("L");
            lRow = dest[0];
            lCol = dest[1];
        }

        //오른손이 움직인다.
        void rMoving(int[] dest) {
            sb.append("R");
            rRow = dest[0];
            rCol = dest[1];
        }

        //row, col 에서 시작해서 target 까지 얼마나 움직여야하는지 리턴
        int countMoves(int row, int col, int target) {
            int moves = 0;
            int[] dest = findTarget(target);

            //row 를 목적지까지 이동
            while (row != dest[0]) {
                row += (row > dest[0]) ? -1 : 1;
                moves++;
            }
            //col 를 목적지까지 이동
            while (col != dest[1]) {
                col += (col > dest[1]) ? -1 : 1;
                moves++;
            }

            return moves;
        }

        //타겟의 좌표를 리턴
        int[] findTarget(int target) {
            int row = 0, col = 0;

            if (target == 1) {
                row = 0;
                col = 0;
            } else if (target == 2) {
                row = 0;
                col = 1;
            } else if (target == 3) {
                row = 0;
                col = 2;
            } else if (target == 4) {
                row = 1;
                col = 0;
            } else if (target == 5) {
                row = 1;
                col = 1;
            } else if (target == 6) {
                row = 1;
                col = 2;
            } else if (target == 7) {
                row = 2;
                col = 0;
            } else if (target == 8) {
                row = 2;
                col = 1;
            } else if (target == 9) {
                row = 2;
                col = 2;
            } else if (target == 0) {
                row = 3;
                col = 1;
            }

            return new int[]{row, col};
        }
    }
}
