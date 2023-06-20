package category.dp.basic;

// 피보나치 수열의 값, 재귀 함수 호출 개수 계산
public class FibonacciOnlyRecursion {
    static long calls;

    static long fibo_helper(int n) {
        calls = 0;
        return fibonacci(n);
    }

    static long fibonacci(int n) {
        calls++;
        // base case;
        if(n == 0 || n == 1) {
            return n; // f(0) = 0, f(1) = 1
        }

        return fibonacci(n-1) + fibonacci(n-2);
    }

    public static void main(String[] args) {
        int[] a = {10, 20, 25, 30, 35, 40, 45, 50};
        for(int n : a) {
            System.out.println(fibo_helper(n) + " " + calls);
        }
    }
}
