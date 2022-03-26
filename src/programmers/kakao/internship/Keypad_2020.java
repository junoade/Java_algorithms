package programmers.kakao.internship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Keypad_2020 {
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
        StringBuilder answer = new StringBuilder();
        int left_idx = 10; // * 치환
        int right_idx = 12; // # 치환

        for (int num : numbers){

            /* 1, 4, 7 일 때 */
            if(num == 1 || num == 4 || num == 7){
                answer.append("L");
                left_idx = num;
            }
            /* 3 6 9 일 때 */
            else if (num == 3 || num == 6 || num == 9){
                answer.append("R");
                right_idx = num;
            }
            /* 2, 5, 8, 0 일 때 */
            else{
                int leftDistance = getDistance(left_idx, num);
                int rightDistance = getDistance(right_idx, num);
                if(leftDistance > rightDistance) {
                    answer.append("R");
                    right_idx = num;
                }else if(leftDistance < rightDistance){
                    answer.append("L");
                    left_idx = num;
                }else{
                    String temp = hand.equals("right") ? "R" : "L";
                    if(temp.equals("R")){
                        right_idx = num;
                    }else{
                        left_idx = num;
                    }
                    answer.append(temp);
                }
            }
        }

        return answer.toString();
    }

    // 좌표 평면화
    // (x1, y1)  (x2, y2) 사이의 거리
    // 간단하게 Manhattan Distance
    public static int getDistance(int lastIndex, int number) {
        // 0 을 11으로 치환
        lastIndex = (lastIndex == 0) ? 11 : lastIndex;
        number = (number == 0) ? 11 : number;

        int x1 = (lastIndex-1) % 3;
        int y1 = (lastIndex-1) / 3;
        int x2 = (number-1) % 3;
        int y2 = (number-1) / 3;
        return Math.abs(x1-x2) + Math.abs(y1-y2);
    }

}
