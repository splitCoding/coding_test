package programmers;

import java.util.*;

//https://programmers.co.kr/learn/courses/30/lessons/43164

public class Travel_path {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        System.out.println(Arrays.toString(s.solution(tickets)));
    }

    static class Solution {
        class Ticket implements Comparable<Ticket> {
            String to;
            int idx;

            Ticket(String s, int i) {
                to = s;
                idx = i;
            }

            @Override
            public int compareTo(Ticket t) {
                return to.compareTo(t.to);
            }
        }

        HashMap<String, List<Ticket>> map = new HashMap<>();
        String[] root;
        boolean[] is_used;
        boolean find;

        String[] solution(String[][] tickets) {
            for (String[] ticket : tickets) map.put(ticket[0], new ArrayList<>());
            for (int i = 0; i < tickets.length; i++) {
                map.get(tickets[i][0]).add(new Ticket(tickets[i][1], i));
            }
            for (List l : map.values()) Collections.sort(l);
            root = new String[tickets.length + 1];
            is_used = new boolean[tickets.length];
            root[0] = "ICN";
            dfs(0, "ICN");
            return root;
        }

        void dfs(int depth, String start) {
            if (find) return;
            if (depth == is_used.length) {
                find = true;
                return;
            }
            if (!map.containsKey(start)) return;
            for (Ticket t : map.get(start)) {
                if (is_used[t.idx]) continue;
                root[depth + 1] = t.to;
                is_used[t.idx] = true;
                dfs(depth + 1, t.to);
                if (find) return;
                is_used[t.idx] = false;
            }
        }
    }
}
