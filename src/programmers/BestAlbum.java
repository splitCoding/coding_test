package programmers;


//https://school.programmers.co.kr/learn/courses/30/lessons/42579

import java.util.*;

public class BestAlbum {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(Arrays.toString(s.solution(genres, plays)));
    }

    static class Solution {
    /*
    ���� ����
    1. ���� �뷡�� ���� ����� �帣 ����
    2. �帣 ������ ���� ����� �뷡 ����
    3. �帣�� ���Ƚ���� ���� �뷡�߿��� �ε����� ���� �뷡 ����
    4. �帣 ���� �ִ� �ΰ����� ��� �ٹ��� �߰�

    �뷡 - �ε���, ���Ƚ��
    �帣 - �� ���Ƚ��, ���Ե� �뷡

    �帣�� �� ���Ƚ���� ���� �����ؾ���
    �뷡���� ���Ƚ���� ���Ƚ���� ������ �ε�����
     */
        public int[] solution(String[] genres, int[] plays) {
            HashMap<String, Genre> map = new HashMap();

            for (int i = 0; i < genres.length; i++) {

                String g = genres[i];
                Song song = new Song(i, plays[i]);

                if (map.containsKey(g)) {
                    map.get(g).add(song);
                } else {
                    Genre genre = new Genre();
                    genre.add(song);
                    map.put(g, genre);
                }
            }
            List<Genre> list = new ArrayList<>(map.values());
            for (Genre genre : list) {
                genre.sortList();
            }

            list.sort((o1, o2) -> o2.total_play - o1.total_play);
            List<Integer> answer = new ArrayList<>();

            for (Genre g : list) {
                if (g.list.size() == 1) {
                    answer.add(g.list.get(0).idx);
                } else {
                    answer.add(g.list.get(0).idx);
                    answer.add(g.list.get(1).idx);
                }
            }

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }

        class Song {
            int idx;
            int play;

            Song(int idx, int play) {
                this.idx = idx;
                this.play = play;
            }
        }

        class Genre {
            int total_play = 0;
            List<Song> list = new ArrayList<>();

            void add(Song s) {
                list.add(s);
                total_play += s.play;
            }

            void sortList() {
                list.sort((o1, o2) -> {
                    if (o2.play == o1.play) {
                        return o1.idx - o2.idx;
                    } else {
                        return o2.play - o1.play;
                    }
                });
            }
        }
    }
}
