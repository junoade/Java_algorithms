package daily.y2025.q4;

import java.util.*;

public class Solution_P_택배배달과수거 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        ArrayDeque<int[]> dStack = new ArrayDeque<>();
        ArrayDeque<int[]> pStack = new ArrayDeque<>();

        // init
        for(int i = 0; i < n; i++) {
            if(deliveries[i] > 0) {
                dStack.push(new int[]{i, deliveries[i]});
            }

            if(pickups[i] > 0) {
                pStack.push(new int[]{i, pickups[i]});
            }
        }


        // do
        long answer = 0;

        while(!pStack.isEmpty() || !dStack.isEmpty()) {
            int localDist = -1;

            if(!dStack.isEmpty()) {
                localDist = Math.max(localDist, dStack.peek()[0]);
            }

            if(!pStack.isEmpty()) {
                localDist = Math.max(localDist, pStack.peek()[0]);
            }


            answer += 2L * (localDist + 1);

            // 배달
            int remain = cap;
            move(dStack, remain);

            // 수거
            remain = cap;
            move(pStack, remain);
        }

        return answer;
    }

    private void move(ArrayDeque<int[]> stack, int remain) {
        while(remain > 0 && !stack.isEmpty()) {
            int[] top = stack.peek();
            if(top[1] <= remain) {
                remain -= top[1];
                stack.pop();
            } else {
                top[1] -= remain;
                remain = 0;
            }
        }
    }
}
