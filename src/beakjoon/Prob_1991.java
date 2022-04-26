package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_1991 {
    static class Node {
        String left, right;

        Node(String i2, String i3) {
            left = i2;
            right = i3;
        }
    }

    static int N;
    static Map<String, Node> nodes;
    static StringBuilder sb = new StringBuilder();

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        nodes = new HashMap();
        for (int i = 0; i < N; i++) {
            nodes.put(scan.next(), new Node(scan.next(), scan.next()));
        }
    }

    static void pre_order(String k) {
        if (k.equals(".")) return;
        sb.append(k);
        pre_order(nodes.get(k).left);
        pre_order(nodes.get(k).right);
    }

    static void in_order(String k) {
        if (k.equals(".")) return;
        in_order(nodes.get(k).left);
        sb.append(k);
        in_order(nodes.get(k).right);
    }

    static void post_order(String k) {
        if (k.equals(".")) return;
        post_order(nodes.get(k).left);
        post_order(nodes.get(k).right);
        sb.append(k);
    }

    static void solution() {
        pre_order("A");
        sb.append("\n");
        in_order("A");
        sb.append("\n");
        post_order("A");
    }

    public static void main(String[] args) {
        input();
        solution();
        System.out.println(sb.toString());
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
