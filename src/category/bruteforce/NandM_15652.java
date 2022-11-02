package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NandM_15652 {

    static int N, M;
    static int[] numbers;
    static StringBuilder sb;


    static void appendNext(int digit) {
        /* base case */
        if (digit == M + 1) {
            Arrays.stream(numbers).filter(i -> i > 0).forEach(n -> sb.append(n).append(" "));
            sb.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                if (digit >= 2 && numbers[digit - 1] > i) continue;
                numbers[digit] = i;
                appendNext(digit + 1);
                numbers[digit] = 0;
            }
        }
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M + 1];
        sb = new StringBuilder();
    }

    public static void main(String[] args) throws IOException {
        input();
        appendNext(1);
        System.out.println(sb);
    }
}
