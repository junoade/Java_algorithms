package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>SE D2 - 1959 두개의 문자열</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 길이가 짧은 쪽이 한 칸 씩 슬라이딩 하면서 같은 위치의 고정 배열의 요소와 곱셈 연산 이후 누적합<br/>
 * 그 중 max가 되는 경우를 완전탐색<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 별도의 함수를 만들어서 slidingArr, fixedArr 를 재사용하도록 작성<br/>
 * 인덱스 범위 주의하기<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class SE_FindMaxInTwoSeqSliding {

    static int solution(int N, int M, int[] A, int[] B) {
        if (N < M) {
            // N을 이동시키며 요소들의 곱의 누적합을 비교
            return calculate(A, B);
        } else {
            return calculate(B, A);
        }
    }

    static int calculate(int[] slidingArr, int[] fixedArr) {
        // 두 배열의 길이가 같으면 한번 반복
        final int L = fixedArr.length - slidingArr.length;
        int max = Integer.MIN_VALUE;
        for (int startIdx = 0; startIdx <= L; startIdx++) {
            int sum = 0;
            for (int i = 0; i < slidingArr.length; i++) {
                sum += slidingArr[i] * fixedArr[startIdx + i]; // 중요
            }
            max = Math.max(max, sum);
        }
        return max;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int[] inputs = processInput(br);
            int N = inputs[0], M = inputs[1];
            int[] A = processInput(br);
            int[] B = processInput(br);
            System.out.printf("#%d %d\n", t + 1, solution(N, M, A, B));
        }
    }

    static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
