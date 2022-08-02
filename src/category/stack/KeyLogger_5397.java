package category.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * --------------------------------------------------------------
 * <title> 백준 5397, 키로거</title>
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * - 핵심아이디어를 파악했는가? Yes
 * - 어떤 요소때문에 시간복잡도에서 걸리는가?
 * 1. StringBuilder 클래스의 replace메소드로 swap을 구현하는 컨셉이였는데, System.arraycopy가 오버헤드가 크다.
 * 2. 입력이 최대 100만까지 가능하게 하기 위해 왼쪽 - 커서 - 오른쪽 에 대한 자료구조를 두고, 보다 효율적인 접근할 수 있도록 한다.
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - 컴파일러 수업때 처럼, 명령코드(opcode)를 받고
 * - 이에 대해 cursor를 변경시키며
 * - 새로운 문자를 삽입하는 식으로 구현
 * --------------------------------------------------------------
 *
 * <b> 채점 </b>
 * <p> 메모리 / 256MB , 시간초과 / 1000ms
 * --------------------------------------------------------------
 */
public class KeyLogger_5397 {

    static void solution(String input) {
        // Stack<String> ops = new Stack<>();
        Queue<String> ops = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        int cursor = 0;
        String firstOp = "";
        input += " ";
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!isChar(c) && c != ' ') {
                ops.add(String.valueOf(c));
            } else {
                /* <, >, - 등 명령이 반복될 수 있기 떄문에, */
                while(!ops.isEmpty()){
                    firstOp = ops.poll();

                    if (firstOp.equals("<") && cursor > 0) {
                        cursor--;
                    } else if (firstOp.equals(">") && cursor < result.length()) {
                        cursor++;
                    } else if (firstOp.equals("-") && cursor > 0) {
                        result.replace(cursor-1, cursor, "");
                        cursor--;
                    }
                }

                /* 결과 반환 준비 */
                if(c == ' ')
                    continue;

                if (result.length() == cursor) {
                    result.append(c);
                } else {
                    // char temp = result.charAt(cursor - 1);
                    String left = result.substring(0, cursor);
                    String right = result.substring(cursor, result.length());
                    result = new StringBuilder().append(left).append(c).append(right);
                }
                cursor++;
                firstOp = "";
            }
        }
        System.out.println(result);
    }

    static boolean isChar(char c) {
        return c >= 'A' && c <= 'Z' || c >= 'a' && c <= 'z';
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solution(br.readLine());
        }
    }
}
