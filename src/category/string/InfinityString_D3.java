package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * --------------------------------------------------------------<br/>
 * <b> 무한 문자열 삼성 SW 15758</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * f(S) = f(T) 의 의미 이해하기
 * S != T 이더라도 주어진 문제 상황에 의해 f(S) = f(T) 일 수 있음
 * i.g) S="abab", T="ab"
 * -> 서로 문자열의 길이를 같게 만들고 (충분히 반복하고) 비교한다면?
 * -> 다시 말해 두 문자열 길이의 최소 공배수 만큼 반복한 문자열을 비교한다면?
 *
 * --------------------------------------------------------------<br/>
 * <b> 채점 PASS </b><br/>g
 * <p> 메모리  24,188KB / 256MB , 실행시간  111ms/ 2000ms<br/>
 * --------------------------------------------------------------
 */
public class InfinityString_D3 {
    // 문자열 S와 문자열 T를 무한히 반복할 때 서로 같은 지 확인
    // 문자열 S의 길이를 l, 문자열 T의 길이를 m이라 할 때,
    // 문자열 S는 m번 반복, 문자열 T는 l번 반복하게 해서 서로의 문자열 길이를 같게 함
    // 서로 같은지 비교 후 결과 반환
    static void solution(String s, String t, int test_case) {
        StringBuilder result = new StringBuilder().append("#").append(test_case).append(" ");
        int l = s.length();
        int m = t.length();

        String newS = repeatParamTimes(m, s);
        String newT = repeatParamTimes(l, t);
        appendResult(result, newS, newT);
        System.out.println(result);
    }

    static String repeatParamTimes(int param, String s) {
        return s.repeat(param);
    }

    static void appendResult(StringBuilder sb, String s1, String s2) {
        if(s1.equals(s2)) sb.append("yes");
        else sb.append("no");
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            String[] inputs = br.readLine().split(" ");
            String s = inputs[0];
            String t = inputs[1];
            solution(s, t, test_case);
        }
    }
}
