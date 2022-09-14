package programmers.kakao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prob_60059 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(new int[][]{{1, 1}, {0, 1}}, new int[][]{{1, 0, 1}, {1, 0, 0}, {1, 1, 1}}));

    }

    static class Solution {
        /*
        정사각형 형태의 자물쇠, 키
        키는 회전, 이동이 가능하다
        자물쇠의 범위를 넘어가도 괜찮지만 범위 안은 홈부분에 딱맞게 채워야 한다.
         */

        public boolean solution(int[][] key, int[][] lock) {
            //자물쇠 부분의 빈칸에 키의 한칸씩 넣어보고 가능한지 확인한다.
            // 회전시에 가능성을 따진 후 없으면 다음
            List<int[]> empties = new ArrayList<>();
            List<int[]> filled = new ArrayList<>();
            findLock(lock, empties);
            findKey(key, filled);
            if (filled.size() == 0) return false;
            if (empties.size() == 0) return true;

            for (int[] fill : filled) {
                for (int[] empty : empties) {
                    if (check(0, key, lock, fill, empty) == empties.size()) {
                        return true;
                    }
                    if (check(1, key, lock, fill, empty) == empties.size()) {
                        return true;
                    }
                    if (check(2, key, lock, fill, empty) == empties.size()) {
                        return true;
                    }
                    if (check(3, key, lock, fill, empty) == empties.size()) {
                        return true;
                    }
                }
            }
            return false;
        }

        int check(int spin, int[][] key, int[][] lock, int[] keyStart, int[] lockStart) {
//            System.out.println(spin + " : " + Arrays.toString(keyStart) + " , " + Arrays.toString(lockStart));
            int count = 1;
            int keyRow = keyStart[0], keyCol = keyStart[1];
            int lockRow = lockStart[0], lockCol = lockStart[1];

            for (int row = 0; row < key.length; row++) {
                for (int col = 0; col < key.length; col++) {
                    //시작점과 같은 경우 패스
                    if (row == keyStart[0] && col == keyStart[1]) continue;
                    if (key[row][col] == 1) {
                        int rowGap = 0, colGap = 0;
                        //정방향
                        if (spin == 0) {
                            rowGap = row - keyRow;
                            colGap = col - keyCol;
                        }
                        //90도 회전
                        if (spin == 1) {
                            rowGap = col - keyCol;
                            colGap = (row - keyRow) * -1;
                        }
                        //180도 회전
                        if (spin == 2) {
                            rowGap = (row - keyRow) * -1;
                            colGap = (col - keyCol) * -1;
                        }
                        //270도 회전
                        if (spin == 3) {
                            rowGap = (col - keyCol) * -1;
                            colGap = row - keyRow;
                        }

                        keyRow = row;
                        keyCol = col;
                        lockRow += rowGap;
                        lockCol += colGap;
                        //범위를 나갔을 때
                        if (lockRow < 0 || lockCol < 0 || lockRow >= lock.length || lockCol >= lock.length) {
//                            System.out.printf("key =[%d][%d] lock = [%d][%d]:::범위를 벗어남%n ", keyRow, keyCol, lockRow, lockCol);
                            continue;
                        }
                        //꽂을 수 있을 때
                        if (lock[lockRow][lockCol] == 0) {
//                            System.out.printf("key =[%d][%d] lock = [%d][%d]:::열쇠를 꽂음%n", keyRow, keyCol, lockRow, lockCol);
                            count++;
                        }
                        //꽃을 수 없을 때
                        if (lock[lockRow][lockCol] == 1) {
//                            System.out.printf("key =[%d][%d] lock = [%d][%d]::열쇠를 꽂을 수 없음%n", keyRow, keyCol, lockRow, lockCol);
                            return -1;
                        }
                    }
                }
            }
            return count;
        }

        void findLock(int[][] lock, List<int[]> empties) {
            for (int i = 0; i < lock.length; i++) {
                for (int j = 0; j < lock[i].length; j++) {
                    if (lock[i][j] == 0) {
                        empties.add(new int[]{i, j});
                    }
                }
            }
        }

        void findKey(int[][] key, List<int[]> filled) {
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key[i].length; j++) {
                    if (key[i][j] == 1) {
                        filled.add(new int[]{i, j});
                    }
                }
            }
        }

        boolean able(int spin, int[][] key, int[][] lock, int[] target, List<int[]> empties) {
            int keyRow = 0, keyCol = 0;
            int lockRow = target[0], lockCol = target[1];

            int count = 0;
            if (spin == 0) {
                count = zero(key, lock, keyRow, keyCol, lockRow, lockCol);
            } else if (spin == 1) {
                count = one(key, lock, keyRow, keyCol, lockRow, lockCol);
            } else if (spin == 2) {
                count = two(key, lock, keyRow, keyCol, lockRow, lockCol);
            } else if (spin == 3) {
                count = three(key, lock, keyRow, keyCol, lockRow, lockCol);
            }
            return count == empties.size();
        }

        private int three(int[][] key, int[][] lock, int keyRow, int keyCol, int lockRow, int lockCol) {
//            System.out.println("three");
            int count = 0;

            for (int row = 0; row < key.length; row++) {
                for (int col = 0; col < key.length; col++) {
                    //키의 홈이 아닌 경우
                    if (key[row][col] != 1) continue;

                    //처음으로 자물쇠에 키를 꽂을 때
                    if (count == 0) {
                        keyRow = row;
                        keyCol = col;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂으며 시작%n", keyRow, keyCol, lockRow, lockCol);
                        count++;
                        continue;
                    }
                    //하나를 꽂고 다음 홈을 자물쇠에 끼울 떄
                    if (count > 0) {
                        //이전 홈과의 row, col 차이를 구한다.
                        int rGap = (col - keyCol) * -1, cGap = row - keyRow;
                        keyRow = row;
                        keyCol = col;
                        //처음 꽂은 곳 기준을 똑같은 위치로 움직인다.
                        lockRow += rGap;
                        lockCol += cGap;

                        //자물쇠 범위 밖으로 나갔을 때는 continue
                        if (lockRow < 0 || lockCol < 0 || lockRow >= lock.length || lockCol >= lock.length) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂는 걸 시도 햇으나 밖으로 나갔다 %d , %d%n", i, j, lockRow, lockCol, rGap, cGap);
                            continue;
                        }
                        //만약 다음위치에 키가 들어갈대
                        if (lock[lockRow][lockCol] == 0) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂음 %d , %d %n", i, j, lockRow, lockCol, rGap, cGap);
                            count++;
                            continue;
                        }
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂을 수 없다 %d , %d.%n", i, j, lockRow, lockCol, rGap, cGap);
                        return -1;
                    }
                }
            }
            return count;
        }

        private int two(int[][] key, int[][] lock, int keyRow, int keyCol, int lockRow, int lockCol) {
//            System.out.println("two");
            int count = 0;
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    //키의 홈이 아닌 경우
                    if (key[i][j] != 1) continue;

                    //처음으로 자물쇠에 키를 꽂을 때
                    if (count == 0) {
                        keyRow = i;
                        keyCol = j;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂으며 시작%n", keyRow, keyCol, lockRow, lockCol);
                        count++;
                        continue;
                    }

                    //하나를 꽂고 다음 홈을 자물쇠에 끼울 떄
                    if (count > 0) {
                        //이전 홈과의 row, col 차이를 구한다.
                        int rGap = (i - keyRow) * -1, cGap = (j - keyCol) * -1;
                        keyRow = i;
                        keyCol = j;
                        //처음 꽂은 곳 기준을 똑같은 위치로 움직인다.
                        lockRow += rGap;
                        lockCol += cGap;

                        //자물쇠 범위 밖으로 나갔을 때는 continue
                        if (lockRow < 0 || lockCol < 0 || lockRow >= lock.length || lockCol >= lock.length) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂는 걸 시도 햇으나 밖으로 나갔다 %d , %d%n", i, j, lockRow, lockCol, rGap, cGap);
                            continue;
                        }
                        //만약 다음위치에 키가 들어갈대
                        if (lock[lockRow][lockCol] == 0) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂음 %d , %d %n", i, j, lockRow, lockCol, rGap, cGap);
                            count++;
                            continue;
                        }
                        return -1;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂을 수 없다 %d , %d.%n", i, j, lockRow, lockCol, rGap, cGap);
                    }
                }
            }
            return count;
        }

        private int one(int[][] key, int[][] lock, int keyRow, int keyCol, int lockRow, int lockCol) {
//            System.out.println("one");
            int count = 0;
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    //키의 홈이 아닌 경우
                    if (key[i][j] != 1) continue;

                    //처음으로 자물쇠에 키를 꽂을 때
                    if (count == 0) {
                        keyRow = i;
                        keyCol = j;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂으며 시작%n", keyRow, keyCol, lockRow, lockCol);
                        count++;
                        continue;
                    }

                    //하나를 꽂고 다음 홈을 자물쇠에 끼울 떄
                    if (count > 0) {
                        //이전 홈과의 row, col 차이를 구한다.
                        int rGap = j - keyCol, cGap = (i - keyRow) * -1;
                        keyRow = i;
                        keyCol = j;
                        //처음 꽂은 곳 기준을 똑같은 위치로 움직인다.
                        lockRow += rGap;
                        lockCol += cGap;

                        //자물쇠 범위 밖으로 나갔을 때는 continue
                        if (lockRow < 0 || lockCol < 0 || lockRow >= lock.length || lockCol >= lock.length) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂는 걸 시도 햇으나 밖으로 나갔다 %d , %d%n", i, j, lockRow, lockCol, rGap, cGap);
                            continue;
                        }
                        //만약 다음위치에 키가 들어갈대
                        if (lock[lockRow][lockCol] == 0) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂음 %d , %d %n", i, j, lockRow, lockCol, rGap, cGap);
                            count++;
                            continue;
                        }
                        return -1;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂을 수 없다 %d , %d.%n", i, j, lockRow, lockCol, rGap, cGap);
                    }
                }
            }
            return count;
        }

        private int zero(int[][] key, int[][] lock, int keyRow, int keyCol, int lockRow, int lockCol) {
//            System.out.println("zero");
            int count = 0;
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key.length; j++) {
                    //키의 홈이 아닌 경우
                    if (key[i][j] != 1) continue;

                    //처음으로 자물쇠에 키를 꽂을 때
                    if (count == 0) {
                        keyRow = i;
                        keyCol = j;
//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂으며 시작%n", keyRow, keyCol, lockRow, lockCol);
                        count++;
                        continue;
                    }

                    //하나를 꽂고 다음 홈을 자물쇠에 끼울 떄
                    if (count > 0) {
                        //이전 홈과의 row, col 차이를 구한다.
                        int rGap = i - keyRow, cGap = j - keyCol;
                        keyRow = i;
                        keyCol = j;
                        //처음 꽂은 곳 기준을 똑같은 위치로 움직인다.
                        lockRow += rGap;
                        lockCol += cGap;

                        //자물쇠 범위 밖으로 나갔을 때는 continue
                        if (lockRow < 0 || lockCol < 0 || lockRow >= lock.length || lockCol >= lock.length) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂는 걸 시도 햇으나 밖으로 나갔다 %d , %d%n", i, j, lockRow, lockCol, rGap, cGap);
                            continue;
                        }
                        //만약 다음위치에 키가 들어갈대
                        if (lock[lockRow][lockCol] == 0) {
//                            System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂음 %d , %d %n", i, j, lockRow, lockCol, rGap, cGap);
                            count++;
                            continue;
                        }
                        return -1;

//                        System.out.printf("succes::[%d][%d]를 [%d][%d]에 꽂을 수 없다 %d , %d.%n", i, j, lockRow, lockCol, rGap, cGap);
                    }
                }
            }
            return count;
        }


    }
}
