package programmers;

import java.util.*;

public class Prob_42861 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}));
    }

    static class Solution {
        /*
        최소 신장 트리(MST Minimum Spanning Tree)

        1.Kruskal

        2.Prims

        */

        public int solution(int n, int[][] costs) {
            Kruskal k = new Kruskal(n, costs);
            Prims p = new Prims(n, costs);

            System.out.println(k.kruskal());
            System.out.println(p.prims1());
            System.out.println(p.prims2());

            return p.prims2();
        }
    }

    static class Kruskal {
        int nodeCount;
        int[][] edges;
        int[] parent;

        public Kruskal(int nodeCount, int[][] edges) {
            this.nodeCount = nodeCount;
            this.edges = edges;
            parent = new int[nodeCount];
            for (int i = 0; i < parent.length; i++) parent[i] = i;
        }

        int kruskal() {
            int price = 0;
            Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
            int idx = 0;
            while (idx < edges.length) {
                //같은 부모일 경우 싸이클이 생성되기때문에 안됨
                if (findParent(edges[idx][0]) != findParent(edges[idx][1])) {
                    union(edges[idx][0], edges[idx][1]);
                    price += edges[idx][2];
                }
                idx++;
            }
            return price;
        }

        int findParent(int i) {
            return (parent[i] == i) ? i : findParent(parent[i]);
        }

        void union(int a, int b) {
            a = findParent(a);
            b = findParent(b);
            parent[Math.max(a, b)] = Math.min(a, b);
        }
    }

    static class Prims {
        int nodeCount;
        int[][] edges;

        public Prims(int nodeCount, int[][] edges) {
            this.nodeCount = nodeCount;
            this.edges = edges;
        }

        int prims1() {
            int price = 0;
            int idx = 0;
            Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));
            //제일 비용이 적은 엣지를 사용
            List<Integer> v = new ArrayList();
            v.add(edges[0][0]);
            v.add(edges[0][1]);
            price += edges[0][2];

            //v에 포함된 노드에서 포함안된 노드로 가는 엣지중 제일 비용이 적은 엣지 연결
            while (v.size() < nodeCount) {
                for (int[] e : edges) {
                    //e의 시작과 끝이 모두 방문한 노드일 경우 사이클이 생성되기에 continue;
                    if (v.contains(e[0]) && v.contains(e[1])) continue;
                    //e[0]이 방문한 노드일 경우
                    if (v.contains(e[0])) {
                        v.add(e[1]);
                        price += e[2];
                        break;
                    }
                    //e[1]이 방문한 노드일 경우
                    if (v.contains(e[1])) {
                        v.add(e[0]);
                        price += e[2];
                        break;
                    }
                }
            }
            return price;
        }

        int prims2() {
            int price = 0;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparing(o -> o[1]));
            List<Integer> visited = new ArrayList();

            //모든 엣지 정리
            Map<Integer, List<int[]>> edgeMap = new HashMap<>();

            for (int[] e : edges) {
                if (!edgeMap.containsKey(e[0])) edgeMap.put(e[0], new ArrayList<>());
                if (!edgeMap.containsKey(e[1])) edgeMap.put(e[1], new ArrayList<>());
                edgeMap.get(e[0]).add(new int[]{e[1], e[2]});
                edgeMap.get(e[1]).add(new int[]{e[0], e[2]});
            }

            //가장 비용이 작은 엣지를 pq에 넣기위해 정렬
            Arrays.sort(edges, Comparator.comparingInt(o -> o[2]));

            //{ 가장 비용이 적은 엣지의 한 정점, 0 } pq에 offer
            pq.offer(new int[]{edges[0][0], 0});

            while (!pq.isEmpty()) {
                int[] e = pq.poll();

                //이미 사용한 엣지인 경우
                if (visited.contains(e[0])) continue;

                //엣지 사용
                visited.add(e[0]);
                price += e[1];

                //목적지와 연결된 엣지 pq에 넣기 ( 사이클이 생길 엣지 제외 )
                for (int[] n : edgeMap.get(e[0])) {
                    if (visited.contains(n[0])) continue;
                    pq.offer(n);
                }
            }

            return price;
        }
    }
}
