package category.math;

import java.io.*;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 1003번 피보나치 함수 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 피보나치 수 N에 쓰인 0과 1의 개수세기
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * - N이 40까지 가능하므로 재귀를 쓰면 StackOverFlow 예외 발생할 것으로 생각
 * - fiboZero 배열, fiboOne 배열을 만들어 바텀업 방식으로 N=2인 경우부터 바텀업 방식으로 필요한 개수를 캐싱함
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  14584KB / 128MB , 실행시간  164 ms / 250ms<br/>
 * --------------------------------------------------------------
 */
public class B_Fibonacci {
    static final int SIZE = 40;
    static long[] fiboZero = new long[SIZE + 1];
    static long[] fiboOne = new long[SIZE + 1];

    public static void main(String[] args) throws IOException {
        initFibos();
        solution();
    }

    static void initFibos() {
        fiboZero[0] = 1;
        fiboOne[1] = 1;

        for (int i = 2; i < SIZE + 1; i++) {
            long zero = fiboZero[i - 2] + fiboZero[i - 1];
            long one = fiboOne[i - 2] + fiboOne[i - 1];
            fiboZero[i] = zero;
            fiboOne[i] = one;
        }
    }

    static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            System.out.printf("%d %d\n", fiboZero[N], fiboOne[N]);
        }
    }
}
