package elements_of_Programming.ch04_Primitive_DataTypes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @def 패리티 : 데이터 통신에서, 송신하고자 하는 데이터의 각 비트에서 1의 개수가 짝수 인지 홀수인지 새서,
 * even parity 면 0
 * odd parity 면 1
 * 로 나타내어, Error Detecting 하는 방법
 * @topic 64비트 숫자 하나의 패리티를 계산을 어떻게 효율적으로 할 수 있을까?
 * @ref 노션_망각주기_비트연산정리
 * @ref 알고리즘 문제 해결 전략
 */

public class Ch04_Parity {

    /**
     * step1) naive하게, O(N) 시간 복잡도
     *
     * @param x
     * @return
     */
    public static short naive_parity_check(long x) {
        short result = 0;
        while (x != 0) {
            // 현재 long 타입의 변수 x의 끝 부분의 비트와 1를 비트 AND 연산 후 XOR을 통해 even-parity 인지 odd parity인지 저장.
            result = (short) (result ^ (x & 1));
            x = x >>> 1; // 빈자리는 0으로 채우며, SHR하여 반복
        }
        return result; // 최종 parity 반환
    }

    /**
     * ## 예를 들어 x = 1011 0010 이라하면,
     * x-1 = 1011 0001 이다.
     * x & (x-1) 연산을 취하면, 1011 이외의 lower bit 자리는 모두 0으로 된다. (지워진다)
     * 이를 반복하면, 1의 개수 k 만큼의 시간복잡도로 문제를 해결할 수 있게 된다.
     * <p>
     * ## 최종 parity 결정 역시 1의 개수에 따라 홀수 개면 ->1->0->1 이렇게 홀수를 리턴할 것이다. O(k)
     *
     * @param x
     * @return
     */
    public static short drop_lower_parity_check(long x) {
        short result = 0;
        while (x != 0) {
            result ^= 1;
            x &= (x - 1); // x & (x-1)의 연산을 취해, x의 비트 중 lowest 1과 그 다음 1 사이에 간격에 대해, 빠르게 하위 비트들을 0으로 만들 수 있다.
        }
        return result;
    }

    /**
     * ## 좀 더 최적화
     * 다수의 비트를 한번에 처리하거나, lookup table 구성하여 캐싱하기
     * @param x
     * @return
     */
    public static short improved_parity_check(long x){
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        long N = Long.parseLong(st.nextToken());

        /* step1 체크*/
        double start = System.nanoTime();
        System.out.println(naive_parity_check(N) + " time : " + (System.nanoTime() - start) * Math.pow(10, 9)); // convert nano second to second

        /* step2 체크*/
        double start2 = System.nanoTime();
        System.out.println(naive_parity_check(N) + " time : " + (System.nanoTime() - start2) * Math.pow(10, 9));
    }
}
