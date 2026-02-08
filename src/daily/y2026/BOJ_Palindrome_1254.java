package daily.y2026;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_Palindrome_1254 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(solution(s));
    }

    private static int solution(String input) {
        int N = input.length();
        int answer = input.length();

        // s[l, r] 범위의 문자열이 palindrome 인지 확인
        // 아니면 증가
        // palindrome이 되면 break

        for(int i = 0; i < N; i++) {
            String sub = input.substring(i, N);
            if(isPalindrome(sub)) {
                break;
            } else {
                answer++;
            }
        }
        return answer;
    }

    private static boolean isPalindrome(String input) {
        int l = 0, r = input.length() - 1;
        char[] chars = input.toCharArray();
        while(l <= r) {
            if(chars[l] != chars[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
