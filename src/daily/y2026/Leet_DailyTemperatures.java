package daily.y2026;

import java.util.*;

public class Leet_DailyTemperatures {
    /**
     * <a href="https://leetcode.com/problems/daily-temperatures/?envType=problem-list-v2&envId=dsa-linear-shoal-monotonic-stack">문제 링크</a>
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        final int L = temperatures.length;

        int[] answer = new int[L];

        for(int j = 0; j < L; j++) {

            // j번째 요소 전 stack에 담긴 요소들중 온 t[i] < t[j]인게 있으면 pop하고 갱신
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[j] ) {
                int prevIdx = stack.pop();
                answer[prevIdx] = j - prevIdx;
            }

            stack.push(j);
        }

        while(!stack.isEmpty()) {
            int prevIdx = stack.pop();
            answer[prevIdx] = 0;
        }

        return answer;
    }
}
