package category.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 주식 가격</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * prior 값 vs 현재 값은 이전 주식 가격 - 현재 주식 가격을 의미한다.<br/>
 * 만약 priorValue > nowValue라면 주식 가격은 현재 더 떨어진 것을 의미한다.<br/>
 * 배열의 요소에는 인덱스(시간)의 주식 가격이 적혀있고<br/>
 * 정답 배열에는 해당 인덱스(시간)으로부터 가격이 안 떨어진 시간을 요소로 갖도록 한다.<br/>
 * <p>
 * 이를 위해 스택 구조를 이용해서,<br/>
 * priorValue > nowValue면 <br/>
 * nowValue와 쌍을 이루는 priorValue를 찾을 때 까지 pop하면서 가격이 떨어졌음을 갱신한다.<br/>
 * --------------------------------------------------------------<br/>
 * <b>입/출력 조건 분석</b>
 * N = 100,000 이라 시간 복잡도 O(N^2)면 시간 초과 된다.<br/>
 * 각 price의 크기는 [1, 10,000] -> int 형<br/>
 * 현재 시간 복잡도 O(N * k); k: 가격이 떨어진 횟수<br/>
 * - 만약 k=0이라면 O(N)<br/>
 * - 만약 k = N 이라면 매번 떨어진 것으로 이때 한번만 while문 돌아서 O(N)이 됨<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 90MB 정확성 실행시간 0.8ms 효율성 실행시간 33ms<br/>
 * --------------------------------------------------------------
 */
public class P_CheckStockPriceDecrease {
    Deque<Integer> stack;

    public int[] solution(int[] prices) {
        final int L = prices.length;
        int[] answer = new int[L];

        // stack에는 idx를 저장한다.
        // 저장 도중 가격이 떨어진 경우 가격이 떨어지지 않았던 시점까지 되돌아가며 값을 갱신해준다.
        stack = new ArrayDeque<>();
        for (int idx = 0; idx < L; idx++) {
            while (!stack.isEmpty()) {
                int priorIdx = stack.peekFirst();
                int priorValue = prices[priorIdx], nowValue = prices[idx];

                // 가격이 떨어진 경우
                if (priorValue > nowValue) {
                    stack.removeFirst();
                    answer[priorIdx] = idx - priorIdx; // 가격이 떨어진 시간을 갱신
                } else { // 가격이 떨어지지 않은 경우
                    break;
                }
            }
            stack.addFirst(idx);
        }

        // 한번도 가격이 떨어지지 않은 요소들을 갱신
        while(!stack.isEmpty()) {
            int idx = stack.removeFirst();
            answer[idx] = L - 1 - idx; // idx초 부터 L 초 까지 가격이 prices[i] 가격 미만으로 떨어지지 않았으므로
        }

        return answer;
    }

    public static void main(String[] args) {
        P_CheckStockPriceDecrease test = new P_CheckStockPriceDecrease();
        System.out.println(Arrays.toString(test.solution(new int[]{4, 3, 2, 1, 0})));
    }
}
