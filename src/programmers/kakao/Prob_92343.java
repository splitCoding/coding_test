package programmers.kakao;

import java.util.*;

public class Prob_92343 {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{
                {0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});

        /**
         * 2진 트리
         * 노드를 방문할떄마다 해당 노드에 있는 동물이 따라온다
         * 양보다 늑대의 수가 적지 않으면 모든 양을 잡아 먹는다
         * 완전탐색으로 모든 경우를 찾는다.
         *
         * 나의 자식 노드 하나, 형제 노드 쭉
         *
         */
    }

    static class Solution {
        final int SHEEP = 0;
        final int WOLF = 1;
        int sheep = 1;
        int wolf = 0;
        int maxSheep = 0;

        public int solution(int[] info, int[][] edges) {
            Map<Integer, List<Node>> tree = new HashMap<>();

            for (int i = 0; i < info.length; i++) {
                tree.put(i, new ArrayList<>());
            }

            for (int[] edge : edges) {
                tree.get(edge[0]).add(new Node(edge[1], info[edge[1]]));
            }

            recursive(0, tree, Arrays.asList());

            return maxSheep;
        }

        void recursive(int now, Map<Integer, List<Node>> tree, List<Node> connected) {
            maxSheep = Math.max(maxSheep, sheep);
            List<Node> connectedList = makeConnectedList(now, tree, connected);

            for (Node node : connectedList) {
                if (node.visited) continue;
                visit(node, tree, connectedList);
            }
        }

        List makeConnectedList(int now, Map<Integer, List<Node>> tree, List<Node> connected) {
            List<Node> need = new ArrayList<>(connected);
            need.addAll(tree.get(now));
            maxSheep = Math.max(maxSheep, sheep);
            return need;
        }

        void visit(Node n, Map<Integer, List<Node>> tree, List<Node> need) {
            if (n.animal == SHEEP) {
                n.visited = true;
                sheep++;
                recursive(n.num, tree, need);
                sheep--;
                n.visited = false;
            }

            if (n.animal == WOLF) {
                wolf++;
                if (wolf < sheep) {
                    n.visited = true;
                    recursive(n.num, tree, need);
                    n.visited = false;
                }
                wolf--;
            }
        }

        private class Node {
            int num;
            int animal;
            boolean visited = false;

            public Node(int child, int animal) {
                this.num = child;
                this.animal = animal;
            }

            public String toString() {
                return this.num + " 번 노드";
            }
        }
    }
}
