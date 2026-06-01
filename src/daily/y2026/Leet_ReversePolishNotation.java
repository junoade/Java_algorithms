package daily.y2026;

import java.util.*;

public class Leet_ReversePolishNotation {
    // postfix notation; operand operand operator

    /**
     * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/?envType=problem-list-v2&envId=dsa-linear-shoal-stack">
     *     문제링크</a>
     * @param tokens
     * @return
     */
    public int evalRPN(String[] tokens) {

        // 1. tokens에서 숫자면 넣기
        // 2. operator 만나면 pop 두개 해서 연산하고 연산 결과 넣기
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            if(!isOperator(s)) {
                stack.push(Integer.parseInt(s));
                continue;
            }

            if(stack.size() < 2) {
                continue;
            }

            int y = stack.pop();
            int x = stack.pop();

            int temp = getEval(s, x, y);
            stack.push(temp);
        }

        return stack.pop();
    }

    private boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }

    private int getEval(String s, int x, int y) {
        int answer = 0;
        switch(s) {
            case "+":
                answer = x + y;
                break;
            case "-":
                answer = x - y;
                break;
            case "*":
                answer = x * y;
                break;
            case "/":
                answer = x / y;
                break;
        }
        return answer;
    }
}
