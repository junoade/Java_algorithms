package Simulation.programmers.level1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Programmers_Level1_신규아이디
 * 구현 (정규식 표현 이용)
 * 1차 풀이 22.02.11
 *
 * > String class에 정의된 replaceAll 메소드를 활용하여 정규식과 함께 풀 수 있었다.
 * >
 *
 * 출처 : 2021 KAKAO BLIND RECRUITMENT
 *
 */
public class NewID_Recommend_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(solution(br.readLine()));
    }

    public static String solution(String new_id){
        /* 1단계 */
        new_id = new_id.toLowerCase();

        /* 2단계 */
        new_id = new_id.replaceAll("[^a-z0-9-_.]", "");

        /* 3단계 */
        new_id = new_id.replaceAll("[.]{2,}", ".");

        /* 4단계 */
        new_id = new_id.replaceAll("^[.]|[.]$", "");

        /* 5단계 */
        if(new_id.length() == 0)
            new_id += 'a';

        /* 6단계 */
        if(new_id.length() >= 16){
            new_id = new_id.substring(0,15);
            new_id = new_id.replace("[.]$", "");
        }
        /* 7단계 */
        StringBuilder sb = new StringBuilder(new_id);
        while(sb.length() <= 2){
            sb.append(sb.charAt(sb.length() - 1));
        }
        new_id = sb.toString();

        return new_id;
    }
}
