package beakjoon;

import java.util.*;

public class Prob_11652 {
    static int N;
    static Card[] cards_arr;
    static Hashtable<Long, Integer> cards;

    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        cards = new Hashtable<>(N);
        for (int i = 0; i < N; i++) {
            Long now = scan.nextLong();
            cards.merge(now, 1, (v1, v2) -> v1 + 1);
        }
        cards_arr = new Card[cards.size()];
        int idx = 0;
        for(Map.Entry<Long, Integer> e : cards.entrySet()){
            cards_arr[idx] = new Card();
            cards_arr[idx].num = e.getKey();
            cards_arr[idx].total = e.getValue();
            idx++;
        }
    }

    public static void main(String[] args) {
        input();
        Arrays.sort(cards_arr);
        System.out.println(cards_arr[0].num);
    }

    static class Card implements Comparable<Card> {
        long num;
        int total;

        public int compareTo(Card o) {
            if (total == o.total) {
                if(num < o.num){
                    return -1;
                } else {
                    return 1;
                }
            }
            return o.total - total;
        }
    }
}
