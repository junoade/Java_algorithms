package programmers.kakao.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Keypad_2020_old {

    public static List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(",");
        String hand = br.readLine();
        int[] seq = new int[temp.length];
        for(int i = 0; i<seq.length; i++)
            seq[i] = Integer.parseInt(temp[i]);

        System.out.println(solution(seq, hand));
    }

    public static String solution(int[] numbers, String hand) {
        String answer = "";
        int left_idx = -1;
        int right_idx = -1;

        for (int i = 0; i < numbers.length; i++) {

            /* 1, 4, 7 일 때 */
            if (numbers[i] == 1 || numbers[i] == 4 || numbers[i] == 7) {
                answer += "L";
                left_idx = i;
            } else if (numbers[i] == 3 || numbers[i] == 6 || numbers[i] == 9) { /*3 6 9 일 때 */
                answer += "R";
                right_idx = i;
            } else { /* 2, 5, 8, 0 일 때 */
                answer += getLR(hand, numbers, left_idx, right_idx, i);

            }

        }

        return answer;
    }

    public static String getLR(String hand, int[] numbers, int left_idx, int right_idx, int target_idx) {
        String result = "";
        int target = list.indexOf(numbers[target_idx]);
        int left = -1;
        int right = -1;

        if(left_idx == -1)
            left = 9;
        else
            left = list.indexOf(numbers[left_idx]);
        if(right_idx == -1)
            right = 11;
        else
            right = list.indexOf(numbers[right_idx]);
        target /= 3;
        left /= 3;
        right /= 3;

        if (Math.abs(left - target) > Math.abs(right - target)) {
            result = "R";
        } else if (Math.abs(left - target) < Math.abs(right - target)) {
            result = "L";
        } else {
            result = hand.equals("right") ? "R" : "L";
        }
        return result;
    }

}
