package daily.y2026;

import java.util.*;

public class Leet_FinalPricesDiscount {
    /**
     * <a href = "https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/?envType=problem-list-v2&envId=dsa-linear-shoal-monotonic-stack">
     *     문제 링크</a>
     * @param prices
     * @return
     */
    public int[] finalPrices(int[] prices) {
        int[] answer = new int[prices.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < prices.length; i++) {

            while(!stack.isEmpty() && cond(stack.peek(), i, prices)) {
                int prev = stack.pop();
                answer[prev] = prices[prev] - prices[i];
            }

            stack.push(i);
        }

        while(!stack.isEmpty()) {
            int prev = stack.pop();
            answer[prev] = prices[prev];
        }

        return answer;
    }

    boolean cond(int i, int j, int[] arr) {
        final int L = arr.length;
        if(i < 0 || j < 0) {
            return false;
        }

        if(i > L || j > L) {
            return false;
        }

        return (i < j) && (arr[i] >= arr[j]);
    }
}
