package daily.y2026;

import java.util.*;

public class Leet_ImplementsQueueUsingStack {

    // inStack은 push 하는 스택의 LIFO 구조로.
    // outStack은 pop, peek 할때 비어있는지에 따라
    // inStack 에서 -> outStack으로 한번 더 LIFO 구조로 옮기면서 Queue의 FIFO 구조를 구현함.
    // queue가 안비어있는 경우엔 O(1) 로 pop.
    // queue가 비어있으면 stack의 내용을 queue로 push 하는 O(N)
    // push N번 후 pop N번이라는 최악 경우에서도,
    // 첫 pop만 이동 비용 O(N)을 치르고 나머지 pop N-1번은 각각 O(1).
    // 이동한 원소는 다시 inStack으로 돌아가지 않으므로,
    // 총 비용 O(N) / 연산 N번 = amortized O(1).

    /**
     * <a href="https://leetcode.com/problems/implement-queue-using-stacks/description/">문제 링크</a>
     */
    private final Deque<Integer> inStack;
    private final Deque<Integer> outStack;

    public Leet_ImplementsQueueUsingStack() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if(!outStack.isEmpty()) {
            return outStack.pop();
        }

        while(!inStack.isEmpty()) {
            int topValue = inStack.pop();
            outStack.push(topValue);
        }

        return outStack.pop();
    }

    public int peek() {

        if(!outStack.isEmpty()) {
            return outStack.peek();
        }

        while(!inStack.isEmpty()) {
            int topValue = inStack.pop();
            outStack.push(topValue);
        }

        return outStack.peek();

    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public static void main(String[] args) {
        Leet_ImplementsQueueUsingStack obj = new Leet_ImplementsQueueUsingStack();

        /**
         * Your MyQueue object will be instantiated and called as such:
         * MyQueue obj = new MyQueue();
         * obj.push(x);
         * int param_2 = obj.pop();
         * int param_3 = obj.peek();
         * boolean param_4 = obj.empty();
         */
    }
}
