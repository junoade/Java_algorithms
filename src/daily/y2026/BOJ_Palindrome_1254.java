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
        char[] arr = input.toCharArray();

        for(int i = 0; i < N; i++) {
            if(isPalindrome(arr, i, N-1)) {
                break;
            } else {
                answer++;
            }
        }
        return answer;
    }

    private static boolean isPalindrome(char[] arr, int l, int r) {
        if(l < 0 || r >= arr.length) {
            return false;
        }
        while(l <= r) {
            if(arr[l] != arr[r]) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
