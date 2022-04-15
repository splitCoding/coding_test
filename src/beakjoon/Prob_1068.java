package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob_1068 {
    static int N, parent[], remove, root, count;
    static List<Integer>[] node_list;


    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        node_list = new List[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = scan.nextInt();
        remove = scan.nextInt();
    }

    static void create_node_list() {
        //노드들의 자식 노드들을 가지고 있는 node_list 생성
        for (int i = 0; i < N; i++) node_list[i] = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (parent[i] == -1) root = i;
            else node_list[parent[i]].add(i);
        }
    }

    static void remove_node() {
        //지울 노드의 자식노드의 모든 자식들 삭제
        Queue<Integer> q = new LinkedList<>();
        for (List l : node_list) if (l.contains(remove)) l.remove(l.indexOf(remove));
        for (int i : node_list[remove]) node_list[i].clear();
    }

    static void find_leaf_node() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.poll();
            if (node_list[n].isEmpty()) count++;
            else for (int i : node_list[n]) q.offer(i);
        }
    }

    static void solution() {
        create_node_list();
        remove_node();
        if (root == remove) {
            System.out.println(0);
        } else {
            find_leaf_node();
            System.out.println(count);
        }
    }

    public static void main(String[] args) {
        input();
        solution();
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

        Integer nextInt() {
            return Integer.parseInt(next());
        }

        Long nextLong() {
            return Long.parseLong(next());
        }

    }
}
