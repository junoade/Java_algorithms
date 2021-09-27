package baekjoon.problems.silver.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하라
 * 입력 : 10,000이하의 자연수
 * 시간 제한: 1초, 메모리 제한 : 128MB
 * 1st : 0927
 * - 132ms , 0.132s
 * - 조건문 블록 실행후 반복문내 index i 를 i=2로 했더니 증감식을 이후에 만나서 i=3 부터 시작해버림
 * - 그래서 i=1로 재설정함
 */
public class GCDandLCM2609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        //System.out.println(GCD(A, B));
        //System.out.println(LCM(A, B));
        int GCD = GCD(A,B);
        System.out.println(GCD);
        int LCM = A*B/GCD;
        System.out.println(LCM);

    }

    // 최대 공약수 : 두 수의 공약수 중에서 가장 큰 수,
    public static int GCD(int x, int y) {
        int mul = 1;
        for (int i = 2; i <= x; i++) {
            // 나뉘는 숫자일 때만
            if (x % i == 0 && y % i == 0) {
                x = x / i;
                y = y / i;
                mul *= i;
                i = 1; // 2부터 시작하게끔
            }
        }
        return mul;
    }
    //최소 공배수 GCD * x' * y' = LCM, x'와 y'는 서로소
    //사실 x*y = GCD * LCM 성질을 이용하면 아래는 필요가 없다.
    /*public static int LCM(int x, int y) {
        int LCM = 1;
        for (int i = 2; i <= x; i++) {
            if (x % i == 0 && y % i == 0) {
                x = x / i;
                y = y / i;
                LCM *= i;
                if (x <= 0 || y <= 0) {
                    break;
                }
            }
        }
        return LCM * x * y;
    }*/
}
