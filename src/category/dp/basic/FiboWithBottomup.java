package category.dp.basic;

public class FiboWithBottomup {
    static long[] mem;

    static long fibo_helper(int n) {
        mem = new long[n + 1];
        mem[0] = 0;
        mem[1] = 1;

        return fibonacci(n);
    }

    static long fibonacci(int n) {
        for(int i = 2; i <= n; i++) {
            mem[i] = mem[i - 1] + mem[i - 2];
        }
        return mem[n];
    }

    public static void main(String[] args) {
        int[] a = {10, 20, 25, 30, 35, 40, 45, 50};
        for(int n : a) {
            System.out.println(fibo_helper(n));
        }
    }
}
