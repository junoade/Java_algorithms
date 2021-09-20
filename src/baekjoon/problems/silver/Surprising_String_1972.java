package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 놀라운 문자열 0-쌍부터 (N-2)쌍 까지 정의하면서 모두 유일하면 놀라운 문자열이라 출력
 * 1차 풀이 : ArrayList에 유일한 문자열만 add하기 그렇지 않다면 break해서 시간 단축을 노림
 *           또한 유일한 문자열의 개수와 해당 D-k 쌍까지 가능한 문자열이 같다면 Surprising 문자열 출력하고
 *           만약 하나라도 유일한 문자열이 없어서, cnt가 다르다면, NOT Surprsing 출력하게함
 * PASS 0.312s
 */
public class Surprising_String_1972 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        while (true) {
            input = br.readLine();
            if (input.equals("*")) {
                break;
            }
            solution(input);
        }
    }

    public static void solution(String input) {
        int D = 0;
        int N = input.length();
        int cnt = 0;
        for (; D <= N - 2; D++) {
            ArrayList<String> arr = new ArrayList<>();
            String temp = "";
            for (int i = 0; i < N - D - 1; i++) {
                temp += input.charAt(i);
                temp += input.charAt(i + D + 1);
                if (!arr.contains(temp)) {
                    arr.add(temp);
                    temp = "";
                } else { // 이미 가지고 있다면 break
                    break;
                }
            }
            if (arr.size() == N - D - 1)
                cnt++;
        }
        if (cnt == N - 1) { //D-0 D-2 까지 는 N-1 과 같음
            System.out.println(input + " is surprising.");
        } else {
            System.out.println(input + " is NOT surprising.");
        }
    }
}
