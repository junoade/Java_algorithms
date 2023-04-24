package category.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 올바른 괄호 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 괄호 짝 맞추기<br/>
 * 올바른 괄호는 '('로 시작해서 ')'로 닫힐 수 있는 괄호
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  75MB , 실행시간  18.65ms<br/>
 * --------------------------------------------------------------
 */
public class P_ValidParenthensis {
    boolean solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();

        // O(N)
        for(char c : s.toCharArray()) { // toCharArray()로 루프돌아야 효율성 테스트 통과함
            if(stack.isEmpty()) {
                if(c == ')') { return false; }
                stack.addFirst(c);
                continue;
            }

            // element와 현재 스택 내 상단의 괄호가 서로 다르면 pop
            // 아니면 push 진행
            char cursor = stack.peekFirst();
            if(cursor != c) {
                stack.removeFirst();
            } else {
                stack.addFirst(c);
            }
        }

        return stack.isEmpty();
    }
}
