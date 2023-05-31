package category.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_CarrySugar {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int[] UNIT = {5, 3};

        int answer = 0;
        int count = 0;

        count = N / UNIT[0];
        int next = N - UNIT[0] * count;
        while(count != 0 && next % UNIT[1] != 0) {
            count -= 1;
            next = N - UNIT[0] * count; // 단위 5를 한번 줄여줌
        }

        N -= UNIT[0] * count;
        answer += count;

        count = N / UNIT[1];
        if(N % UNIT[1] != 0) {
            answer = -1;
        } else {
            answer += count;
        }

        System.out.println(answer);

    }
}