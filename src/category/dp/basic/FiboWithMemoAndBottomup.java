package category.dp.basic;

import java.util.Arrays;

public class FiboWithMemoAndBottomup {
    static long[] mem;
    static long calls;


    // 재귀함수의 depth 개선
    static long fibo_helper(int n) {
        mem = new long[n + 1];
        calls = 0;
        Arrays.fill(mem, -1);
        for (int i = 0; i < n; i++) {
            fibonacci(i);
        }
        return fibonacci(n);
    }

    static long fibonacci(int n) {
        calls++;
        // base case : 이미 해결한 부분 문제인지 확인
        if (mem[n] != -1) {
            return mem[n];
        }

        // base case : 종료 조건
        if (n == 0 || n == 1) {
            return n;
        }
        mem[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return mem[n];
    }


    public static void main(String[] args) {
        System.out.println("그냥 메모이제이션 기법 DP : " + FiboWithMemoization.fibo_helper(100_000));
        System.out.println("재귀함수 깊이를 얕게 : " + FiboWithMemoAndBottomup.fibo_helper(100_000));
    }
}
