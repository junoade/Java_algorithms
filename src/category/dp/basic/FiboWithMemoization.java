package category.dp.basic;

import java.util.Arrays;

public class FiboWithMemoization {
    static long[] mem;
    static long calls;

    static long fibo_helper(int n) {
        mem = new long[n + 1];
        calls = 0;

        Arrays.fill(mem, -1);

        return fibonacci(n);
    }

    static long fibonacci(int n) {
        calls++;
        // base case : 이미 해결한 부분 문제인지 확인
        if(mem[n] != -1) {
            return mem[n];
        }

        // base case : 종료 조건
        if(n == 0 || n == 1) {
            return n;
        }
        mem[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return mem[n];
    }


    public static void main(String[] args) {
        int[] a = {10, 20, 25, 30, 35, 40, 45, 50};
        for(int n : a) {
            System.out.println(fibo_helper(n) + " " + calls);
        }
    }
}
