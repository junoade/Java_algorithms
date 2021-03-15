package baekjoon.problems.bronze;

import java.util.Scanner;

public class TestCaseEx1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = 0;
        int x = 0, y = 0;
        T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            System.out.println(x + y);
        }
    }
}
