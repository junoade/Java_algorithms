package category.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * --------------------------------------------------------------<br/>
 * <b>P 뒤에 있는 큰 수 찾기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * O(N^2)보다 효율적인 풀이 고안하기<br/>
 * - 스택 이용; 공간 효율성 trade-off<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  200MB, 실행시간 : 156ms<br/>
 * --------------------------------------------------------------
 */
public class P_FindBiggerFromBehind {
    public int[] solution(int[] numbers) {
        final int L = numbers.length;
        int[] answer = new int[L];

        // idx 정보를 저장하는 stack
        Stack<Integer> stack = new Stack<>();

        // 스택의 top(인덱스)에 해당하는 배열 값과; arr[top]
        // 현재 탐색 인덱스에 해당하는 배열의 값을 비교; arr[i]
        for (int i = 0; i < L; i++) {
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.peek()] = numbers[i];
                stack.pop();
            }
            stack.push(i);
        }

        // 탐색 종료 후에도 stack에 인덱스들이 있는 경우
        // 조건을 만족하는 경우가 없다는 뜻
        while (!stack.isEmpty()) {
            answer[stack.peek()] = -1;
            stack.pop();
        }

        return answer;
    }

    public static void main(String[] args) {
        P_FindBiggerFromBehind test = new P_FindBiggerFromBehind();
        int[] arr1 = new int[]{2, 3, 3, 5};
        int[] arr2 = new int[]{9, 1, 5, 3, 6, 2};
        int[] arr3 = new int[]{2, 1, 3, 1, 4, 5, 6};
        System.out.println(Arrays.toString(test.solution(arr2)));
    }
}
