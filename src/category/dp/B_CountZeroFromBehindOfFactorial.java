package category.dp;

import java.io.*;
import java.math.BigInteger;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 팩토리얼 0의 개수 1676 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 큰 수 다루기<br/>
 * 배열에 저장해서 재귀로 못 구현하는 팩토리얼 구현하기<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 14.7MB / 128MB, 실행시간 136ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_CountZeroFromBehindOfFactorial {

    static final int SIZE = 500;
    static BigInteger[] tables;

    static void initFactorialTables() {
        tables[0] = BigInteger.valueOf(1);

        for(int i = 1; i < tables.length; i++) {
            tables[i] = tables[i - 1].multiply(BigInteger.valueOf(i));
        }
    }

    static void solution(int N) {
        char[] values = String.valueOf(tables[N]).toCharArray();
        int count = 0;
        for(int i = values.length - 1; i >= 0; i--) {
            if(values[i] == '0') {
                count++;
            } else {
                break;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        tables = new BigInteger[SIZE + 1];
        initFactorialTables();
        solution(N);


    }
}
