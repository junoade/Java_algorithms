package daily.y2026;

import java.util.*;

public class P_PassingTruckOnBridge {

    /**
     * @see category.queue.P_TruckPassingBridge
     * @param bridge_length
     * @param weight
     * @param truck_weights
     * @return
     */
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> queue = new ArrayDeque<>(); // 트럭 무게 저장을 위한 queue
        for(int i = 0; i < bridge_length; i++) {
            queue.offer(0);
        }

        int availWeight = weight, time = 0;
        int tIdx = 0;

        while(!queue.isEmpty() && tIdx < truck_weights.length) {
            availWeight += queue.poll(); // 다리에서 빠지는 트럭 무게 감소
            int curWeight = truck_weights[tIdx]; // 다리에 올라갈 트럭 무게 확인

            if(availWeight < curWeight) {
                // 대기
                queue.offer(0);
            } else {
                queue.offer(curWeight);
                availWeight -= curWeight;
                tIdx++;
            }
            time++;
        }

        // 트럭이 다리위에 다 올라간 상태일 수 있으므로,
        while(!queue.isEmpty()) {
            queue.poll();
            time++;
        }

        return time;
    }
}
