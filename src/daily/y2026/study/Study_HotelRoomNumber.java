package daily.y2026.study;

import java.io.*;

public class Study_HotelRoomNumber {
    static final int MAX = 10_000;

    static boolean evaluate(int n) {
        // int[] digits = new int[5]; 굳이 다 기록할 필요는 없다.
        boolean[] v = new boolean[10];
        while(n > 0) {
            int digit = n % 10;
            n /= 10; // 1의 자리->10의자리로 move
            if(!v[digit]) {
                v[digit] = true;
            } else {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int T = Integer.parseInt(br.readLine());

        int[] sum = new int[MAX + 1]; // sum[i] : 1번방부터 i번방까지 조건을 만족하는 방의 개수

        sum[0] = 0;
        for(int i = 1; i <= MAX; i++) {
            // 현재 숫자가 조건을 만족하면 +1
            int value = 0;
            if(evaluate(i)) {
                value++;
            }
            sum[i] = sum[i - 1] + value;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++) {
            String[] temp = br.readLine().split(" ");
            int s = Integer.parseInt(temp[0]), e = Integer.parseInt(temp[1]);
            int prefixSum = sum[e] - sum[s - 1];
            sb.append(prefixSum).append("\n");
        }

        System.out.print(sb);

    }

}
