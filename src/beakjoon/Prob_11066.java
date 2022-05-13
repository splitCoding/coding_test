package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_11066 {
    static int K;
    static int[] num;
    static int[][] sum, result;

    static FastReader scan = new FastReader();

    static void input() {
        K = scan.nextInt(); //3 ≤ K ≤ 500
        num = new int[K + 1]; //size[i] = 10,000 이하
        sum = new int[K + 1][K + 1];
        result = new int[K + 1][K + 1];
        for (int i = 1; i <= K; i++) num[i] = scan.nextInt();
    }

    static void preprocess() {
        //누적합들을 모두 구해준다
        for (int i = 1; i <= K; i++) {
            for (int j = i; j <= K; j++) {
                sum[i][j] = sum[i][j - 1] + num[j];
            }
        }
    }

    //처음으로 구현한 코드
    static void first_solution() {
        preprocess();

        //start부터 K까지 연속으로 합하였을때 걸리는 시간을 저장
        for (int start = 1; start <= K; start++) {
            //아무것도 합치지 않은 경우
            result[start][start] = num[start];
            //다음 파일과 합친경우
            if (start < K) result[start][start + 1] = num[start] + num[start + 1];
            //2번째 이후 파일 이상까지 합친 경우
            for (int end = start + 2; end <= K; end++)
                result[start][end] = result[start][end - 1] + sum[start][end];
        }

        //start부터 end까지 합하였을때 최솟값 구하기
        for (int start = K; start > 0; start--) {
            //3개 이상의 파일들을 합쳤을 시
            for (int end = start + 2; end <= K; end++) {
                //start부터 end까지 파일들을 모두 합친 총 크기
                int sum_start_to_end = sum[start][end];
                for (int divide = start; divide < end; divide++) {
                    //start부터 divide까지 파일을 모두 합했을 때 걸린 시간의 최솟값
                    int min_start_to_divide = result[start][divide];
                    //나머지 파일을 모두 합했을 때 걸린 시간의 최솟값
                    int min_else = result[divide + 1][end];
                    int new_value = min_start_to_divide + min_else + sum_start_to_end;
                    //첫 파일을 다믕 파일과 합하지 않은 경우 (합하는 시간이 걸리지 않음)
                    if (start == divide) new_value -= min_start_to_divide;
                    //마지막 파일을 그전 파일과 합하지 않은 경우 (합하는 시간이 걸리지 않음)
                    if (divide + 1 == end) new_value -= min_else;
                    result[start][end] = Math.min(result[start][end], new_value);
                }
            }
        }
    }

    //두번째로 구현한 코드 ( 처음에 모든 경우를 구하지 않고 뒤에서부터 진행하여 필요한 경우의 수들만 구하고 진행 )
    static void solution2() {
        preprocess();
        for (int start = K; start > 0; start--) {
            for (int end = start; end <= K; end++) {
                if (end <= start + 1) {
                    result[start][end] = result[start][end - 1] + num[end];
                } else {
                    result[start][end] = Integer.MAX_VALUE;
                    for (int divide = start; divide < end; divide++) {
                        int new_value = result[start][divide] + result[divide + 1][end] + sum[start][end];
                        if (start == divide) new_value -= result[start][divide];
                        if (divide + 1 == end) new_value -= result[divide + 1][end];
                        result[start][end] = Math.min(result[start][end], new_value);
                    }
                }
            }
        }
    }

    //두번째로 구현한 코드 정리
    static void solution2_remake() {
        preprocess();
        for (int start = K; start > 0; start--) {
            for (int end = start + 1; end <= K; end++) {
                result[start][end] = Integer.MAX_VALUE;
                for (int divide = start; divide < end; divide++) {
                    int new_value = result[start][divide] + result[divide + 1][end] + sum[start][end];
                    result[start][end] = Math.min(result[start][end], new_value);
                }
            }
        }
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int T = scan.nextInt();
        for (int i = 0; i < T; i++) {
            input();
            solution2_remake();
            sb.append(result[1][K]).append('\n');
        }
        System.out.println(sb);
    }

    static class FastReader {
        BufferedReader bf;
        StringTokenizer st;

        FastReader() {
            bf = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(bf.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
