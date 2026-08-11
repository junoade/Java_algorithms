package daily.y2026;

import java.util.*;

public class Leet_LargestRectangleHistogram {
    public int largestRectangleArea(int[] heights) {
        // stack : 넓이가 오름차순으로 늘어나며, 끝이 결정 되지 않은 배열의 index 기록
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        final int L = heights.length;
        int maxArea = 0;

        for(int cursor = 0; cursor < L; cursor++) {
            // stack의 top의 값이 heights[cursor] 보다 큼.
            // e.g) 스택 idx{value} [1{1}, 2{5}, 3{6}] , 커서 idx{value} [4{2}]
            // 6 > 2
            while(!stack.isEmpty()) {
                int top = stack.peek();

                // 내림차순으로 바뀌는 위치 cursor.
                // 오름차순 유지한 가장 최신의 위치 top.
                // top의 top-1 위치 left.
                // cursor보다 높은 막대들은 여기서 끝났으므로, 스택에서 하나씩 꺼내며 각자의 최대 면적을 확정하고, 스택이 다시 오름차순이 되면 멈춘다.
                if(heights[top] >= heights[cursor]) {
                    stack.pop();
                    int left = stack.isEmpty() ? -1 : stack.peek();
                    int width = cursor - left - 1;
                    maxArea = Math.max(maxArea, heights[top] * width);
                } else {
                    break;
                }
            }

            stack.push(cursor);
        }

        // for문이 다 끝나고 stack에 여전히 오름차순이 유지되며 넓이 계산이 되지 않은 경우가 있을 수 있음.
        while(!stack.isEmpty()) {
            int top = stack.pop();
            int left = stack.isEmpty() ? -1 : stack.peek();
            int width = L - left - 1;
            maxArea = Math.max(maxArea, heights[top] * width);
        }


        return maxArea;

    }

    public static void main(String[] args) {
        Leet_LargestRectangleHistogram obj = new Leet_LargestRectangleHistogram();
        obj.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3});
    }
}
