package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * --------------------------------------------------------------<br/>
 * <b> 삼성 원재의 메모리 복구하기 - D3 1289 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * - 1 또는 0으로 특정 비트를 세팅하면 그 하위 비트는 세팅된 비트값으로 바뀌는 상황
 * - 어떻게 최소로 0000...000 (최대 50자리) 에서 원래 메모리 값으로 바꿀 것인가?
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * - 1로 세팅된 최상위 비트의 인덱스 위치를 찾고 해당 자리부터 연속된 1 또는 0을 고려하여
 *   구분되는 부분 집합의 개수를 반환한다.
 * --------------------------------------------------------------<br/>
 * <b> 채점 : PASS </b><br/>
 * <p> 정적/힙 메모리  24,968 kb / 256MB, 스택 메모리 /1MB, 실행시간 106 ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class MemoryRecover {
    // 1과 0으로 이루어진 부분 집합의 갯수를 세서 반환한다.
    static void solution(String input, int test_case) {
        // 1로 세팅된 최상위 비트 찾고
        int leftMostOneBitIdx = input.indexOf("1");
        int L = input.length();
        int count = 0;

        boolean found = leftMostOneBitIdx != -1;
        if (found) {
            // 다음 하위 비트의 1 또는 0과 비교하며
            // 구분되는 부분 집합의 개수를 반환
            for (int i = leftMostOneBitIdx; i < L - 1; i++) {
                char leftVal = input.charAt(i);
                char rightVal = input.charAt(i + 1);
                if (leftVal != rightVal) {
                    count++;
                }
            }
            // 최초에 1로 바꿨으므로 1을 추가해줌
            count += 1;
        }
        System.out.printf("#%d %d\n", test_case, count);
    }

    public static void main(String args[]) throws IOException {
        //System.setIn(new FileInputStream("res/input.txt"));
		/* 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다. */
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            String input = br.readLine();
            solution(input, test_case);
            /////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}
