package programmers;

import java.util.Arrays;

//https://programmers.co.kr/learn/courses/30/lessons/17676

public class Thanksgiving {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution(new String[]{"2016-09-15 01:00:04.001 2.0s",
                "2016-09-15 01:00:07.000 2s"});
    }

    static class Solution {
        class Log {
            long log_start = 0, log_end = 0;

            Log(String end, String time) {
                int hour, minute, second, millisecond;
                hour = Integer.parseInt(end.split(":")[0]);
                minute = Integer.parseInt(end.split(":")[1]);
                second = Integer.parseInt(end.split(":")[2].split("\\.")[0]);
                millisecond = Integer.parseInt(end.split(":")[2].split("\\.")[1]);
                log_end += hour * 60 * 60 * 1000;
                log_end += minute * 60 * 1000;
                log_end += second * 1000;
                log_end += millisecond;

                log_start = log_end;
                //초
                String seconds = time.split("s")[0];
                if (seconds.contains(".")) {
                    log_start -= Integer.parseInt(seconds.split("\\.")[0]) * 1000;
                    log_start -= Integer.parseInt(seconds.split("\\.")[1]);
                } else {
                    log_start -= Integer.parseInt(seconds) * 1000;
                }
            }

            void show() {
                System.out.println(log_start + " ~ " + log_end);
            }
        }

        public int solution(String[] lines) {
            int answer = 1;
            Log[] logs = new Log[lines.length];
            for (int i = 0; i < logs.length; i++) {
                logs[i] = new Log(lines[i].split(" ")[1], lines[i].split(" ")[2]);
                logs[i].show();
            }
            for (int i = 0; i < logs.length; i++) {
                int count = 1;
                for (int j = i + 1; j < logs.length; j++) {
                    //앞작없이 끝난시간 + 999밀리초
                    if (logs[j].log_start < logs[i].log_end + 999) {
                        count++;
                    }
                }
                answer = Math.max(answer, count);
            }
            return answer;
        }
    }
}
