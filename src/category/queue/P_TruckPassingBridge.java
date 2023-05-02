package category.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class P_TruckPassingBridge {

    Queue<Integer> queue;

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        queue = initBridge(bridge_length);
        int availWeight = weight, T = 0;
        int tIdx = 0;

        while(tIdx < truck_weights.length) {
            assert !queue.isEmpty();
            availWeight += queue.poll();

            int curWeight = truck_weights[tIdx];
            // 현재 다리에 올라갈 수 있는 무게보다 트럭의 무게가 크다면
            // 올라갈 수 없는 상태
            if (availWeight < curWeight) {
                queue.offer(0);
            } else { // 올라갈 수 있는 상태
                queue.offer(curWeight);
                availWeight -= curWeight;
                tIdx++;
            }

            T++;
        }

        System.out.println(T + ", " + queue);
        // 마지막에 다리에 있는 트럭들을 옮겨 줌
        // 이때 상태는 다리의 올라간 트럭의 무게는 전체 다리의 무게보단 작을 것
        while(availWeight != weight) {
            availWeight += queue.remove();
            T++;
        }

        return T;
    }

    private Queue<Integer> initBridge(int L) {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < L; i++) {
            queue.offer(0);
        }
        return queue;
    }

    public static void main(String[] args) {
        P_TruckPassingBridge test = new P_TruckPassingBridge();
        // System.out.println(test.solution(2, 10, new int[]{7, 4, 5, 6}));
        // System.out.println(test.solution(100, 100, new int[]{10}));

        // System.out.println(test.solution(10, 12, new int[]{4, 4, 4, 2, 2, 1, 1, 1, 1}));
        System.out.println(test.solution(10, 12, new int[]{1, 1, 1, 1, 2, 2, 4, 4, 4}));
    }
}
