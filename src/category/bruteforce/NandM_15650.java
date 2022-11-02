package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NandM_15650 {

    static int N, M;
    static int[] numbers, used;
    static StringBuilder sb;

    static void appendNext(int digit) {
        if (digit == M + 1) {
            Arrays.stream(numbers).filter(n -> n > 0).forEach(n -> sb.append(n).append(" "));
            sb.append("\n");
        } else {
            for(int i = 1; i <= N; i++){
                if(used[i] == 1) continue;
                if(digit > 1 && numbers[digit - 1] >= i) continue;
                // 갱신
                numbers[digit] = i;
                used[i] = 1;

                // 재귀 호출
                appendNext(digit + 1);

                // 재귀 호출 후 다시 update 꼭!
                numbers[digit] = 0;
                used[i] = 0;
            }
        }
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M + 1];
        used = new int[N + 1];
        sb = new StringBuilder();
    }

    public static void main(String[] args) throws IOException {
        input();
        appendNext(1);
        System.out.print(sb);
    }
}
