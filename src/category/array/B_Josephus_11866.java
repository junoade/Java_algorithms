package category.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Josephus_11866 {

    static void solution(int N, int K) {
        StringBuilder sb = new StringBuilder();
        int[] arr = new int[N + 1]; // 1부터 시작해서
        for(int i = 1; i < arr.length; i++) {
            arr[i] = i;
        }

        int people = N;
        sb.append("<");
        // O(KN)
        while(people > 0) {
            for(int i = 1; i < arr.length; i++) {
                int seq = arr[i];
                if(seq == 0) { // 이미 죽은 사람
                    continue;
                }

                // 죽이는 조건
                if(seq % K == 0) {
                    arr[i] = 0;
                    people--;
                    sb.append(i);
                    if(people >= 1) {
                        sb.append(", ");
                    } else {
                        sb.append(">");
                    }

                } else {
                    arr[i] += people; // 남은 수 사람만큼 더해줌
                }

            }
        }

        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]), K = Integer.parseInt(inputs[1]);

        solution(N, K);
    }
}
