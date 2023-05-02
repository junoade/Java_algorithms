package category.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class P_TruckPassingBridge_F1 {
    Queue<Integer> queue;

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        final int L = truck_weights.length; // 트럭의 갯수
        int T = 0, availWeight = weight;

        queue = initQueue(bridge_length);

        for (int i = 0; i < L; i++) {
            if(!queue.isEmpty()) {
                availWeight += queue.poll();
            }
            int currentWeight = truck_weights[i];
            // 현재 queue(다리에 존재하는 요소들의 남은 무게들과
            // peek 한 요소의 무게값을 비교
            if (availWeight >= currentWeight) {
                queue.add(currentWeight);
                availWeight -= currentWeight;
            } else {
                // 0을 넣어줌
                queue.add(0);
            }
            T++;
        }

        // 마지막 queue에 있는 요소 정리
        while(!queue.isEmpty() && availWeight != weight) {
            availWeight -= queue.poll();
            T++;
        }

        return T;
    }

    private Queue<Integer> initQueue(int bridge_length) {
        Queue<Integer> temp = new ArrayDeque<>();
        for (int i = 0; i < bridge_length; i++) {
            temp.offer(0);
        }
        return temp;
    }

    public static void main(String[] args) {
        P_TruckPassingBridge_F1 test = new P_TruckPassingBridge_F1();
        // System.out.println(test.solution(2, 10, new int[]{7, 4, 5, 6}));

        System.out.println(test.solution(10, 12, new int[]{4, 4, 4, 2, 2, 1, 1, 1, 1}));
    }
}
