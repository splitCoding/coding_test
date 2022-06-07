package programmers;

//https://programmers.co.kr/learn/courses/30/lessons/12979

public class build_5g_base_station {
    public static void main(String[] args) {
        Solution_no_ceil s1 = new Solution_no_ceil();
        Solution_using_ceil s2 = new Solution_using_ceil();
        System.out.println(s1.solution(16, new int[]{9}, 2));
        System.out.println(s2.solution(16, new int[]{9}, 2));
    }

    static class Solution_no_ceil {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;
            int range = 2 * w + 1;
            //전파가 안터지는 구간의 start, end, len
            int start = 1, end = stations[0] - w - 1, len = end - start + 1;
            //checked 아파트까지는 모두 전파가 통하게 설정됨
            int checked = stations[0] + w;
            answer += (len > 0) ? (len % range == 0) ? len / range : len / range + 1 : 0;

            for (int i = 1; i < stations.length; i++) {
                start = checked + 1;
                end = stations[i] - w - 1;
                len = end - start + 1;
                checked = stations[i] + w;
                if (len <= 0) continue;
                answer += (len % range == 0) ? len / range : len / range + 1;
            }

            if (checked < n) {
                start = checked + 1;
                len = n - start + 1;
                if (len > 0) {
                    answer += (len % range == 0) ? len / range : len / range + 1;
                }
            }
            return answer;
        }
    }

    //시간이 더 걸림
    static class Solution_using_ceil {
        public int solution(int n, int[] stations, int w) {
            float range = 2 * w + 1;
            int answer = 0;
            //전파가 안터지는 구간의 start, end, len
            int start = 1, end = stations[0] - w - 1, len = end - start + 1;
            //모든전파가 터지는 구간의 끝
            int checked = stations[0] + w;

            answer += (len > 0) ? (int) Math.ceil(len / range) : 0;

            for (int i = 1; i < stations.length; i++) {
                start = checked + 1;
                end = stations[i] - w - 1;
                len = end - start + 1;
                if (len > 0) answer += Math.ceil(len / range);
                checked = stations[i] + w;
            }

            if (checked < n) {
                len = n - (checked + 1) + 1;
                if (len > 0) answer += Math.ceil(len / range);
            }

            return answer;
        }
    }
}
