package category.dp;

public class P_FibonacciAndMod {
    final int MAX = 100_000;
    final int DIV = 1234567;
    int[] mem;

    public int solution(int n) {
        mem = new int[MAX + 1];
        mem[0] = 0;
        mem[1] = 1;

        for(int i = 2; i <= n; i++) {
            mem[i] = (mem[i - 1] + mem[i - 2]) % DIV;
        }

        return mem[n];
    }
}
