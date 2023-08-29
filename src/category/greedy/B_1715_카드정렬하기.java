package category.greedy;

import java.io.*;
import java.util.*;

public class B_1715_카드정렬하기 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            pq.offer(arr[i]);
        }

        int sum = 0;

        while(pq.size() > 1) {
            int x = pq.poll();
            int y = pq.poll();

            int temp = x + y;
            sum += temp;
            pq.offer(temp);
        }

        System.out.println(sum);
    }
}
