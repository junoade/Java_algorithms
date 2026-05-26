package daily.y2026;

import category.stack.P_CheckStockPriceDecrease;

import java.util.*;

public class P_StockPrice {
    /**
     * 예전 풀이 {@link P_CheckStockPriceDecrease}
     * @see P_CheckStockPriceDecrease
     *  @implNote 시간 복잡도 O(N) — 단조 스택.
     *  각 인덱스는 최대 1번 push, 최대 1번 pop 되므로
     *  외부 루프 N회 + 내부 while 의 누적 pop ≤ N → O(N + N) = O(N).
     *  공간 복잡도 O(N) (스택 + 결과 배열).
     * @param prices
     * @return
     */
    public int[] solution(int[] prices) {
        final int N = prices.length;
        int[] answer = new int[N];

        // 아직 가격이 떨어지지 않은 인덱스들을 저장.
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            // 현재 가격이 이전 가격보다 떨어졌다면, 가격이 떨어지지 않았던 시점까지 되돌아가며 값을 확정 지음
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                int j = stack.pop();
                answer[j] = i - j; // 가격 확정
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            answer[j] = (N - 1) - j;
        }

        return answer;
    }
}

