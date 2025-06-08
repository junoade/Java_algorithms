package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_boj_2470 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // int[] arr = new int[N];
        // step1
        /*String[] temp = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(temp[i]);
        }*/
        // step2
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(arr);
    }

    static void solution(int[] arr) {
        Arrays.sort(arr);
        int i = 0, j = arr.length - 1;
        int minSum = Integer.MAX_VALUE;
        int[] minArr = new int[2];


        while (i < j) {
            int sum = arr[i] + arr[j];
            int gap = Math.abs(sum);
            if (gap == 0) {
                // 종료
                minArr[0] = arr[i];
                minArr[1] = arr[j];
                break;
            }

            if (gap < minSum) {
                minSum = gap;
                minArr[0] = arr[i];
                minArr[1] = arr[j];
            }

            // 양수
            if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }

        System.out.printf("%d %d\n", minArr[0], minArr[1]);
    }
}
