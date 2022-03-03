package beakjoon;

import java.io.*;
import java.util.*;

public class Prob_10825 {
    static int N;
    static Student[] students;
    static StringBuilder sb = new StringBuilder();


    static void input() {
        FastReader scan = new FastReader();
        N = scan.nextInt();
        students = new Student[N];
        for (int i = 0; i < N; i++) {
            students[i] = new Student();
            students[i].name = scan.next();
            students[i].korean = scan.nextInt();
            students[i].english = scan.nextInt();
            students[i].math = scan.nextInt();
        }
    }

    public static void main(String[] args) {
        input();
        Arrays.sort(students);
        for (Student s : students) sb.append(s.name).append('\n');
        System.out.println(sb.toString());
    }

    static class Student implements Comparable<Student> {
        String name;
        int korean, english, math;

        @Override
        public int compareTo(Student o) {
            if (korean != o.korean) return o.korean - korean;
            if (english != o.english) return english - o.english;
            if (math != o.math) return o.math - math;
            return name.compareTo(o.name);
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String ret = "";
            try {
                ret = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ret;
        }
    }
}
