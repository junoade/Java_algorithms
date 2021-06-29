package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 백준 9012 스택 관련 문제
 */
public class Parenthesis_9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String cmd = "";
        for (int i = 0; i < N; i++) {
            // N 만큼 명령어 입력
            cmd = br.readLine();    // N-1 일때 문제
            if (chkVPS(cmd))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }

    public static boolean chkVPS(String str) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (!stack.isEmpty() && str.charAt(i) == ')' && stack.peek() == '(') {
                stack.pop();
            } else {
                stack.push(str.charAt(i));
            }
        }
        if (stack.isEmpty())
            return true;
        else
            return false;
    }
}
