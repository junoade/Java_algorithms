package category.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 괄호 회전하기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 괄호 회전 구현하기 <br/>
 * 괄호 짝 맞추기, {} () [] 이렇게 <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  94.4MB , 실행시간  41.68ms<br/>
 * --------------------------------------------------------------
 */
public class P_ShiftParenthensis {
    public int solution(String s) {
        int answer = 0;
        for (int i = 0; i < s.length(); i++) {
            String newStr = shiftStr(s, i);
            // System.out.printf("{ before : %s, new : %s }\n", s, newStr);
            if (isValid(newStr)) {
                answer++;
            }
        }
        return answer;
    }

    private String shiftStr(String s, int offset) {
        final int L = s.length();
        char[] src = s.toCharArray();
        char[] target = new char[L];
        for (int i = 0; i < L; i++) {
            int newIdx = (L + i - offset) % L;
            target[newIdx] = src[i];
        }
        return new String(target);
    }

    private boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                if (c == '[' || c == '(' || c == '{') {
                    stack.addFirst(c);
                    continue;
                } else {
                    return false;
                }
            }

            char cursor = stack.peekFirst();
            if (c == ']' && cursor == '[') {
                stack.removeFirst();
            } else if (c == '}' && cursor == '{') {
                stack.removeFirst();
            } else if (c == ')' && cursor == '(') {
                stack.removeFirst();
            } else {
                stack.addFirst(c);
            }
        }
        return stack.isEmpty();
    }
}
