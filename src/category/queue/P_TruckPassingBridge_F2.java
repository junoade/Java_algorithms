package category.queue;

import java.util.ArrayDeque;
import java.util.Queue;

public class P_TruckPassingBridge_F2 {
    Queue<Integer> queue;

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        final int L = bridge_length;
        queue = initBridge(L);
        int availWeight = weight, T = 0;

        for(int truck_weight : truck_weights) {
            int hasInternalLoop = 0;

            // 만약 queue(다리)에 넣을 수 없다면
            // 가능해질 때까지 poll하고 0을 채워 넣어준다.
            while(availWeight - truck_weight < 0) {
                assert !queue.isEmpty();
                int temp = queue.poll();
                queue.offer(0);
                availWeight += temp;
                T++;
                hasInternalLoop++;
            }

            if(hasInternalLoop > 1) {
                T -= 1;
            }

            // 넣을 수 있다면
            // 넣어준다
            // 큐가 꽉 찼을 땐 빼고나서 넣어준다
            if(queue.size() == L) {
                availWeight += queue.remove();
            }
            queue.offer(truck_weight);
            availWeight -= truck_weight;
            T++;
        }
        System.out.println(T + " " +queue);

        // 마지막에 남은 queue 중 0이 아닌 값의 갯수만큼
        // 처리 시간을 추가해준다.
        while(availWeight != weight) {
            assert !queue.isEmpty();
            availWeight += queue.poll();
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
        P_TruckPassingBridge_F2 test = new P_TruckPassingBridge_F2();
        // System.out.println(test.solution(2, 10, new int[]{7, 4, 5, 6}));
        // System.out.println(test.solution(100, 100, new int[]{10}));

        // System.out.println(test.solution(10, 12, new int[]{4, 4, 4, 2, 2, 1, 1, 1, 1}));
        System.out.println(test.solution(10, 12, new int[]{1, 1, 1, 1, 2, 2, 4, 4, 4}));
    }
}
