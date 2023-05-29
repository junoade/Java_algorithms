package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_LostParenthesis_1541 {

    static final String REGEX = "[+|-]";
    static int MIN;
    static boolean[] visited;

    static void solution(String s) {
        int[] numbers = Arrays.stream(s.split(REGEX)).mapToInt(Integer::parseInt).toArray();
        String[] ops = Arrays.stream(s.split("(\\d)+")).skip(1).toArray(String[]::new);
        // System.out.println(Arrays.toString(numbers));
        // System.out.println(Arrays.toString(ops));

        MIN = Integer.MAX_VALUE;
        visited = new boolean[ops.length];

        rec(numbers, ops, 0, 0);
        System.out.println(MIN);
    }

    static void rec(int[] numbers, String[] ops, int depth, int value) {
        // base case;
        if (depth == ops.length) {
            MIN = Math.min(MIN, value);
            System.out.println("EXIT");
            return;
        }

        for (int i = 0; i < ops.length; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            if (depth == 0) {
                value = numbers[i];
            }

            if (ops[i].equals("+")) {
                System.out.printf("expr{%d %s %d = ", value, "+", numbers[i + 1]);
                value = value + numbers[i + 1];
                System.out.printf("%d}\n", value);
                rec(numbers, ops, depth + 1, value);
                visited[i] = false;
                value -= numbers[i + 1];
            } else if (ops[i].equals("-")) {
                System.out.printf("expr{%d %s %d = ", value, "-", numbers[i + 1]);
                value = value - numbers[i + 1];
                System.out.printf("%d}\n", value);
                rec(numbers, ops, depth + 1, value);
                visited[i] = false;
                value += numbers[i + 1];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        solution(s);
    }
}
