package beakjoon;

import java.util.Scanner;

public class Prob_1244 {
    static int[] arr;
    static Student[] students;

    public static void main(String[] args) {
        input();

        StringBuilder sb = new StringBuilder();

        for (Student student : students) {
            if (student.gender == 1) {
                for (int i = 1; i < arr.length; i++) {
                    if (i % student.num != 0) continue;
                    arr[i] = (arr[i] == 1) ? 0 : 1;
                }
            } else {
                arr[student.num] = (arr[student.num] == 1) ? 0 : 1;
                int k = 1;
                int num = student.num;

                while (1 <= num - k && num + k < arr.length) {
                    if (arr[num - k] != arr[num + k]) break;
                    arr[num - k] = (arr[num - k] == 1) ? 0 : 1;
                    arr[num + k] = (arr[num + k] == 1) ? 0 : 1;
                    k++;
                }
            }
        }
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i] + " ");
            if (i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb);
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        arr = new int[Integer.parseInt(scan.nextLine()) + 1];

        int arrIdx = 1;
        while (arrIdx < arr.length) {
            String[] sSplit = scan.nextLine().split("\\s+");
            for (int i = 0; i < sSplit.length; i++) {
                arr[arrIdx++] = Integer.parseInt(sSplit[i]);
            }
        }

        students = new Student[scan.nextInt()];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student(scan.nextInt(), scan.nextInt());
        }

    }

    static class Student {
        int gender;
        int num;

        public Student(int gender, int num) {
            this.gender = gender;
            this.num = num;
        }

        public String toString() {
            return gender + " , " + num;
        }
    }
}
