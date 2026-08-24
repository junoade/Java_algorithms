package daily.y2026;

import java.util.*;

public class Leet_LastStoneWeight {

    /**
     * <a href="https://leetcode.com/problems/last-stone-weight/description/">문제 링크</a>
     * @param stones
     * @return
     */
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(int value : stones) {
            maxHeap.offer(value);
        }

        while(maxHeap.size() >= 2) {
            int left = maxHeap.poll();
            int right = maxHeap.poll();

            if(left == right) {
                continue;
            }

            int next = Math.abs(left-right);
            maxHeap.offer(next);
        }

        if(maxHeap.isEmpty()) {
            return 0;
        } else {
            return maxHeap.peek();
        }
    }
}
