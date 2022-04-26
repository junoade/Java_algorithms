package category.string;

/**
 * Programmers_ 2018 Kakao Blind
 * 다트
 * 풀 수 있는 방법 고안
 * 1. str.charAt(idx); 이용하기, 단 '1', '0'이 연속으로 나오는 경우를 고려해야함.
 * 2. regex 패키지의 Matcher, Pattern 클래스 이용하기
 * 3. StringTokenizer? 하나의 String에 다 합쳐져 있어서 X
 * 4. String.split() ? 구분자 regex가 복잡해지고, *# 가 붙어서 어려워짐
 *
 * https://programmers.co.kr/learn/courses/30/lessons/17682?language=java
 */

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dart_2018_Kakao {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    /* 정규표현식으로, 점수부, 옵션부 나눠서 주어진 규칙에 맞게 계산하자. */
    public static int solution(String s) {

        /* delimeter가 바로 옆에 붙어 있으면 "" 를 반환할 수 있다.*/
        //String[] scores = s.split("[S||D||T||\\*]");
        //String[] options = s.split("\\d");
        //String scores = s.replaceAll("\\D", " ");

        Pattern pattern = Pattern.compile("([0-9]+)([SDT])([*#]?)");
        Matcher matcher = pattern.matcher(s);
        int result = 0;
        // ArrayList<Integer> scores = new ArrayList<>();
        int[] scores = {0,0,0};

        int idx = 0;
        while(matcher.find()){
            int score = Integer.parseInt(matcher.group(1));
            switch (matcher.group(2)) {
                case "S":
                    score = (int)Math.pow(score, 1); // pow()는 double 형 반환해서 (int) casting 해주자
                    break;
                case "D":
                    score = (int)Math.pow(score, 2);
                    break;
                case "T":
                    score = (int)Math.pow(score, 3);
                    break;
            }
            scores[idx] = score; // 여기서 미리 저장

            switch (matcher.group(3)) {
                case "*":
                    scores[idx] *= 2;
                    if(idx >= 1)
                        scores[idx-1] *= 2;
                    break;
                case "#":
                    scores[idx] *= -1;
                    break;
            }

            idx++;
        }

        for(int num : scores)
            result += num;

        return result;
    }

}
