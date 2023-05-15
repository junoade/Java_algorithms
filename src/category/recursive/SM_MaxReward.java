package category.recursive;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>삼성 D3 - 최대 상금 복습 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 완전 탐색으로 모든 경우 보기<br/>
 * 그리디로 풀면 오답<br/>
 * - 왜 이번 코드는 더 오래걸릴까?<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  17,000ms / 20,000ms<br/>
 * --------------------------------------------------------------
 */
public class SM_MaxReward {

    static int maxValue;

    static void solution(int[] numbers, int swap) {
        maxValue = -1;
        swap_rec(numbers, 0, swap);
    }

    static void swap_rec(int[] numbers, int startIdx, int swap) {
        if(swap == 0) {
            maxValue = Math.max(maxValue, getNumber(numbers));
            return;
        }

        // 중복 제거 고려
        // 완전 탐색
        for (int i = startIdx; i < numbers.length - 1; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                // swap하고
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                // 다음 재귀 끝 다녀왔다가
                swap_rec(numbers, i, swap - 1);
                // swap 취소
                numbers[j] = numbers[i];
                numbers[i] = temp;
            }
        }
    }

    static int getNumber2(int[] numbers) {
        final int L = numbers.length;
        int number = 0;
        for (int k = 0; k < L; k++) {
            number += Math.pow(10, L - k - 1) * numbers[k];
        }
        return number;
    }

    static int getNumber(int[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int i : numbers) {
            sb.append(i);
        }
        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] inputs = br.readLine().split(" ");
            int[] numbers = Arrays.stream(inputs[0].split("")).mapToInt(Integer::parseInt).toArray();
            int swap = Integer.parseInt(inputs[1]);
            solution(numbers, swap);
            System.out.printf("#%d %d\n", (i + 1), maxValue);
        }
    }
}
