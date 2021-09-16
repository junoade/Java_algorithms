package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 0909 - 1차 시간초과 - DP 연산량을 줄이기 위해서는 메모제이션을 써야 한다.
 */
public class Fibonacci1003 {
    public static int X;
    public static int Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < T; i++) {
            fibonacci(arr[i]);
            System.out.println(X + " " + Y);
            X = 0;
            Y = 0;
        }

    }
    public static int fibonacci(int n) {
        if (n == 0) { //basecase 1
            //System.out.println("0");
            X++;
            return 0;
        } else if (n == 1) { //base case 2
            //System.out.println("1");
            Y++;
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
}
