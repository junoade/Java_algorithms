package elements_of_Programming.ch04_Primitive_DataTypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <title>CH04.01 패리티 계산하기</title>
 *
 * @def 패리티 : 데이터 통신에서, 송신하고자 하는 데이터의 각 비트에서 1의 개수가 짝수 인지 홀수인지 새서,
 * even parity 면 0
 * odd parity 면 1
 * 로 나타내어, Error Detecting 하는 방법
 * @topic 64비트 숫자 하나의 패리티를 계산을 어떻게 효율적으로 할 수 있을까?
 * @ref 노션_망각주기_비트연산정리
 * @ref 알고리즘 문제 해결 전략
 */

public class Sec01_Parity {

    /* step1) 1로 세팅된 모든 비트의 개수세기, O(N) */
    public static short parity_O_N(long x) {
        short result = 0;
        // x의 LSB가 1인지 검사하고
        // LSB가 1이면, result 는 1, 0 을 반복하게 된다.
        // 결과적으로,
        // 1이 홀수개 이면 result는 1
        // 1이 짝수개가 되면 result는 0
        // 이렇게 반복된다.
        while (x != 0) {
            result ^= (short) (x & 1);
            x >>>= 1; // x를 SHR 하여 맨 끝자리 비교를 이어간다.
        }
        return result;
    }

    /**
     * step2) 하위비트를 한번에 지워, 최선-평균의 경우의 성능 향상시키기, O(k)
     * 예를 들어 x = 1011 0010 이라하면, x-1 = 1011 0001 이다.
     * x & (x-1) 연산을 취하면, 1011 이외의 lower bit 자리는 모두 0으로 된다. (지워진다)
     * 이를 반복하면, 1의 개수 k 만큼의 시간복잡도로 문제를 해결할 수 있게 된다.
     * <p>
     * 최종 parity 결정 역시 1의 개수에 따라 홀수 개면 ->1->0->1 이렇게 홀수를 리턴할 것이다. O(k)
     */
    public static short parity_O_K(long x) {
        // x의 제일 오른쪽에 있는 1비트 이하를 (x-1)과 & 연산함으로서 0으로 지울 수 있다.
        // 결과적으로 x의 이진표현 중 1의 개수 k 만큼의 시간복잡도를 갖게 된다.
        short result = 0;
        while (x != 0) {
            result ^= 1; // 1의 개수만큼 반복하게 됨. 홀수번 반복하면 1, 짝수번 반복하면 0
            x &= (x - 1);
        }
        return result;
    }

    /* step3) 연산 결과를 룩업테이블에 캐싱하여, 매우 큰 수에 대한 패리티도 효율적으로 구하기,  O(N/L) L: 해시 테이블에 사용될 키값의 비트수*/
    public static short parity_O_N_L(long x) {
        // 64비트를 16비트씩 나눈다고 생각해보자
        // 각 16비트의 패리티를 구하고 나서 패리티를 구하게 되어도, 똑같이 64비트의 패리티리를 구하게 된다.
        // 따라서 패리티 구하기는 결합법칙이 성립한다
        // 이를 이용해 lookup table를 구성한다
        return 0;
    }

    /* step4) 결합 법칙 성질을 이용해서 여러 비트를 한번에 수행하도록하기 */
    public static short parity_log(long x) {
        // 64비트는 32비트, 32비트
        // 32비트는 16비트, 16비트
        // 16비트는 8비트, 8비트
        // 8비트는 4비트, 4비트
        // 4비트는 2비트, 2비트
        // 2비트는 1비트, 1비트
        // 예를 들어, 1101 0111 과 같이 8비트가 있을 때, x>>>4 는 0000 1101 이다.
        // 다시 말해 0111 과 1101 를 XOR 연산한 결과는 1010 인데, 1010의 패리티는 전체의 패리티와 같게 된다.
        // 다시 x>>>2하여 10과 10의 XOR한 결과 00을 얻고, 여기서 LSB가 패리티 값이 된다
        for (int i = 5; i > -1; i--) {
            int j = (int) Math.pow(2, i);
            x ^= x >>> j;
        }
        return (short) (x & 0x1); // x의 LSB를 반환함
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        // Long.parseInt(st.nextToken());
        long x = Long.parseLong(br.readLine());
        long start = System.nanoTime();
        System.out.println("패리티 체크 1 - " + parity_O_N(x) + ", 수행 시간 : " + (System.nanoTime() - start) / Math.pow(10, 9));

        start = System.nanoTime();
        System.out.println("패리티 체크 2 - " + parity_O_K(x) + ", 수행 시간 : " + (System.nanoTime() - start) / Math.pow(10, 9));

        // start = System.nanoTime();
        // System.out.println("패리티 체크 3 - " + parity_O_K(x) + ", 수행 시간 : " + (System.nanoTime() - start)/Math.pow(10, 9));

        start = System.nanoTime();
        System.out.println("패리티 체크 4 - " + parity_log(x) + ", 수행 시간 : " + (System.nanoTime() - start) / Math.pow(10, 9));
    }
}
