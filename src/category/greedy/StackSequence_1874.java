package category.greedy;

import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * --------------------------------------------------------------
 * <title> 백준 1874, 스택 수열</title>
 * 유형 - 스택, 그리디
 * 복습 yes
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * - 1부터 N까지 수를 스택에서 집어넣음으로써, 입력으로 받은 수열과 같은 수열을 만들 수 있는 지
 * - 그렇다면 Push(+), Pop(-) 순서를 출력
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - 입력 시퀀스가 있을 때,
 * - 입력 시퀀스의 키값과 현재 규칙으로 구성하는 스택의 키값의 대소여부를 판단
 * 1. 만약 key <= cursor라면, cursor 값을 top에 갖도록 key값을 증가시키며 push를 반복 수행
 * 2. 이제 우리의 스택과 입력 시퀀스의 cursor 값이 같다면, pop을 수행
 * 3. 1번 과정을 수행했음에도 불구하고, 다르다면, NO를 반환
 * 4. 3번의 타당성은, 현재 규칙은 cursor까지 오름차순 push, 중복된 key값을 push는 불가하다는 점, 만약 stack내에서 오름차순이 깨지면 불가능하다는점.
 * --------------------------------------------------------------
 *
 * <b> 채점 </b>
 * <p> 메모리 30096KM/ 128MB , 시간 396ms / 2000ms
 * --------------------------------------------------------------
 */
public class StackSequence_1874 {

    static ArrayList<Integer> inputs;

    static void solution(int N) {
        StringBuffer sb = new StringBuffer();
        Stack<Integer> stack = new Stack<>();
        int count = 1; // 1~N까지의 수를 스택에 push하거나 pop하는 용도의 변수

        for (int i = 0; i < N; i++) {
            int cursor = inputs.get(i);
            while (count <= cursor) {
                stack.push(count);
                sb.append("+\n"); // cursor의 키값과 같을 때까지 push
                count++;
            }
            if (stack.peek() == cursor) {
                stack.pop();
                sb.append("-\n");
            } else {
                System.out.println("NO");
                return;
            }
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        inputs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputs.add(Integer.parseInt(br.readLine()));
        }

        solution(N);
    }
}
