package beakjoon;

import java.io.*;
import java.util.*;


public class Prob_5639_Re {
    static List<Integer> list = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();

    static void input() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        try {
            while (true) {
                sb.setLength(0);
                String s = bf.readLine();
                if (s == null || s.equals("")) break;
                sb.append(s);
                list.add(Integer.parseInt(sb.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void dfs(List<Integer> l) {
        //전위 순회이기 때문에 middle / left / right 순서로 존재
        if (l.size() == 0) return;
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int middle = l.remove(0);
        for (Integer num : l) {
            //middle보다 작으면 left에 추가
            if (num < middle) left.add(num);
                //middle보다 작으면 right에 추가
            else if (num > middle) right.add(num);
        }
        //후위 순회로 바꾸기 위해 left, right를 재귀하고 middle은 큐에 삽입
        dfs(left);
        dfs(right);
        q.offer(middle);
    }

    static void solution() {
        dfs(list);
    }

    static void show_result() {
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }
    }

    public static void main(String[] args) {
        input();
        solution();
        show_result();
    }
}
