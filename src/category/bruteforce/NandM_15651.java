package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 N과 M(3) </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class NandM_15651 {

    static int N, M;
    static StringBuilder answer_buffer;
    static int[] numbers;

    public static void appendLowerDigits(int digit) {
        if (digit == M + 1) {
            for(int i = 1; i < numbers.length; i++) answer_buffer.append(numbers[i]).append(" ");
            answer_buffer.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                numbers[digit] = i;
                appendLowerDigits(digit + 1); // 재귀 호출 시작
                numbers[digit] = 0; // 현재 자릿수를 명시적으로 0으로 초기화
            }
        }
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        numbers = new int[M+1];
    }

    public static void main(String[] args) throws IOException {
        input();
        answer_buffer = new StringBuilder();
        appendLowerDigits(1);
        System.out.println(answer_buffer);
    }
}
