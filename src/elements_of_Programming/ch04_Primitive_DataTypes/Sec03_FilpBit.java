package elements_of_Programming.ch04_Primitive_DataTypes;

/**
 * <title>CH04.03 비트 뒤집기</title>
 * 64비트 숫자가 주어졌을 때, 이를 역순으로 재구성한 숫자를 반환하는 코드를 작성하라.
 * <p>
 * 고려할 점
 * - 이 연산을 한번만 쓰는가? 반복하는가?
 * - 한번만 쓰면 무식하게 해도 ㄱㅊ
 * - 반복하면 캐싱을 염두하여 룩업테이블을 구성하자
 */

import java.io.*;

public class Sec03_FilpBit {

    public static short[] table = new short[(int)Math.pow(2,16)];

    /**
     * step1) 무식하게
     */
    public static long flipBit_myway(long x) {
        for (int i = 0; i < 32; i++) {
            x = swapBit(x, i, 63 - i);
        }
        return x;
    }

    public static long swapBit(long x, int i, int j) {
        if (((x >>> i) & 1) != ((x >>> j) & 1)) {
            long bitMask = (1L << i) | (1L << j);
            x ^= bitMask;
        }
        return x;
    }

    /* step2) lookup table 이용하자. */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long x = Long.parseLong(br.readLine());
        System.out.printf("Flip Bit! <before : %d> <after : %d>", x, flipBit_myway(x));

    }

}
