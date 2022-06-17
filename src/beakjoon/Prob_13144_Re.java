package beakjoon;

import java.io.*;
import java.util.stream.Stream;

public class Prob_13144_Re {
    static int N, max, arr[], check[];
    static long answer;

    static void input() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        arr = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i : arr) max = Math.max(max, i);
    }

    static void solution() {
        check = new int[max + 1];
        int L = 0, R = 0;
        while (L < N) {
            while (R < N && check[arr[R]] == 0) check[arr[R++]]++; //중복이 없는 부분배열의 최대길이를 찾는다.
            answer += R - L; //L로 시작하는 연속되는 부분배열의 갯수를 더한다.
            check[arr[L++]]--; //L을 부분배열에서 제거하고 L++.
        }
    }

    static void show_result() {
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solution();
        show_result();
    }
}
