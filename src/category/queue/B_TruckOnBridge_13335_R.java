package category.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class B_TruckOnBridge_13335_R {

    static int N, w, L;

    static int solution(int[] arr) {

        Queue<Integer> queue = new ArrayDeque<>(w);
        for (int i = 0; i < w; i++) {
            queue.offer(0);
        }

        int cursor = 0, weight = 0, time = 0;
        while (!queue.isEmpty() && cursor < arr.length) {
            // System.out.println(queue + " " +time);
            // System.out.println(cursor);
            weight -= queue.poll();
            if (weight < 0) {
                weight = 0;
            }


            int nextWeight = arr[cursor];
            // 넣을 수 없을 때
            if (weight + nextWeight > L) {
                nextWeight = 0;
            } else {
                // 넣을 수 있을 때
                weight += nextWeight;
                cursor++;
            }

            queue.offer(nextWeight);
            time++;
        }

        // 큐에 남은 0들과 마지막 트럭이 존재할 때;
        // 위에선 마지막 트럭이 들어오면 종료되게 됨
        while (!queue.isEmpty()) {
            queue.poll();
            time++;
        }

        return time;
    }


    public static void main(String[] args) throws IOException {

        // System.setIn(new FileInputStream("res/2307/0712-B-13335-input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = processInput(br);

        N = inputs[0];
        w = inputs[1];
        L = inputs[2];

        System.out.println(solution(processInput(br)));
    }

    private static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
