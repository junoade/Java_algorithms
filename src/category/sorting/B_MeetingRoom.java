package category.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_MeetingRoom {

    // 정렬 후
    // 작은 순서부터 greedy하게
    // 선형 탐색
    static void solution(int[][] times) {
       // System.out.println(Arrays.deepToString(times));
        // 1) 끝나는 시간이 이른 순서
        // 2) 시작 시간이 이른 순서
        // 3) 시간 텀이 짧은 순서
       Arrays.sort(times, (r1, r2) -> {
           int period1 = Math.abs(r1[1] - r1[0]);
           int period2 = Math.abs(r2[1] - r2[0]);
           if(r1[1] != r2[1]) return Integer.compare(r1[1], r2[1]);
           if(r1[0] != r2[0]) return Integer.compare(r1[0], r2[0]);
           return Integer.compare(period1, period2);
       });

        // System.out.println(Arrays.deepToString(times));

        int count = 0;
        int prevStart = 0, prevEnd = 0;
        for(int[] time : times) {
            int nowStart = time[0], nowEnd = time[1];
            if (prevEnd <= nowStart) {
                count++;
                prevStart = nowStart;
                prevEnd = nowEnd;
            }
        }

        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] times = new int[N][2];
        for(int i = 0; i < N; i++) {
            times[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solution(times);
    }
}
