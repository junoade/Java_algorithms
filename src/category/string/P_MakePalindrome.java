package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * --------------------------------------------------------------<br/>
 * <b> 백준 1254 팰린드롬 만들기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 부분 문자열 curStr과 그 역순 문자열 reverseStr을 비교하며 같아질 때까지
 * <br/> 문자열을 쪼갠다. 그리고 같아질 때 기존 문자열 길이 + i를 더한다
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 처음엔 문자열 str과 그 역순 문자열 reverseStr의 서로 다른 문자들을 더하면 되나 했는데 그렇지 않았다.
 * 예를 들어 str = "abab"라 하면 reverseStr은 "baba"일 것이다. 이 때 서로 다른 문자의 위치는 총 4개이다.
 * 그러나 최소한의 문자를 더하는 방식으로 팰린드롬 수를 만드는 것이 핵심이므로
 * abab와 baba 비교
 * bab 와 bab 비교
 * ab와 ba 비교
 * b와 b 비교하며 서로 팰린드롬 문자열이 아닌 부분 문자열의 개수를 인덱스 i가 세게 된다.
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_MakePalindrome {
    static String input;
    static int output;

    static void processInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine();
        output = input.length();
    }

    static void findPalindrome(String s) {
        for(int i = 0; i < s.length(); i++) {
            String curStr = s.substring(i);
            String reverseStr = getReverseStr(curStr);
            if(reverseStr.equals(curStr)) {
                output += i;
                break;
            }
        }
    }

    static String getReverseStr(String s) {
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        processInput();
        findPalindrome(input);
        System.out.println(output);
    }
}
