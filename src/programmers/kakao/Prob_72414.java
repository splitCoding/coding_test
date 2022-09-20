package programmers.kakao;

import java.util.ArrayList;
import java.util.List;

public class Prob_72414 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        System.out.println(s.solution("02:03:55", "00:14:15", new String[]{
//                "01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"
//        }));
        System.out.println(s.solution("99:59:59", "25:00:00", new String[]{
                "69:59:59-89:59:59", "01:00:00-21:00:00", "79:59:59-99:59:59", "11:00:00-31:00:00"
        }));
        System.out.println(s.solution("50:00:00", "50:00:00", new String[]{
                "15:36:51-38:21:49", "10:14:18-15:36:51", "38:21:49-42:51:45"
        }));
    }

    //누적합 알고리즘
    static class Solution2 {
        public String solution(String play_time, String adv_time, String[] logs) {
            int totalPlayTime = changeIntoSecond(play_time);
            int totalAdvTime = changeIntoSecond(adv_time);

            List<int[]> logList = new ArrayList<>();
            for (int i = 0; i < logs.length; i++) {
                String[] split = logs[i].split("-");
                logList.add(new int[]{changeIntoSecond(split[0]), changeIntoSecond(split[1])});
            }

            long[] arr = new long[changeIntoSecond(play_time) + 1];
            for (int[] ints : logList) {
                for (int i = ints[0]; i < ints[1]; i++) {
                    arr[i]++;
                }
            }

            long sum = 0;
            for (int i = 0; i < totalAdvTime; i++) {
                sum += arr[i];
            }

            int start = 0;
            int end = totalAdvTime;
            long max = sum;
            int where = 0;
            while (end <= totalPlayTime) {
                sum -= arr[start];
                sum += arr[end];
                if (sum > max) {
                    max = sum;
                    where = start + 1;
                }
                start++;
                end++;
            }

            return changeIntoString(where);
        }
    }

    static int changeIntoSecond(String playTime) {
        String[] split = playTime.split(":");
        int second = 0;
        second += Integer.valueOf(split[0]) * 3600;
        second += Integer.valueOf(split[1]) * 60;
        second += Integer.valueOf(split[2]) * 1;
        return second;
    }

    static String changeIntoString(int time) {
        StringBuilder sb = new StringBuilder();
        int second = time % 60;
        time /= 60;
        int minute = time % 60;
        int hour = time / 60;

        sb.append((hour < 10) ? "0" + hour : hour).append(":");
        sb.append((minute < 10) ? "0" + minute : minute).append(":");
        sb.append((second < 10) ? "0" + second : second);

        return sb.toString();
    }
}
