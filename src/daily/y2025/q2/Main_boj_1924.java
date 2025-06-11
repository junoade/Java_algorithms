package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_boj_1924 {

    enum Weekdays {
        SUN, MON, TUE, WED, THU, FRI, SAT
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(inputs);
    }

    static void solution(int[] inputs) {

        int x = inputs[0], y = inputs[1];

        int sum_days = 0;
        for (int i = 1; i < x; i++) {
            if(i == 2) {
                sum_days += 28;
            } else if(i == 4 || i == 6 || i == 9 || i == 11) {
                sum_days += 30;
            } else {
                sum_days += 31;
            }
        }

        sum_days += y;
        Weekdays[] weekdays = Weekdays.values();
        System.out.println(weekdays[sum_days % 7]);
    }
}
