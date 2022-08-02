package category.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

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
 * - 가운데에 커서가 있다 생각하고, 왼쪽 스택, 오른쪽 스택을 두어 pop, push 연산을 통해 커서이동을 구현한다.
 * --------------------------------------------------------------
 *
 * <b> 채점 </b>
 * <p> 메모리 235132KB / 256MB , 시간초과 824ms / 1000ms
 * -> 시간이 간당하다. 자바로 푼 사람들 중 시간을 300ms대로 줄인 풀이도 있다.
 * -> 직접 스택 자료구조를 구현한 경우다.
 * --------------------------------------------------------------
 */
public class KeyLogger_5397_2 {
    static void solution(String input){
        Stack<String> left = new Stack<>();
        Stack<String> right = new Stack<>();

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c == '<'){
                if(!left.isEmpty())
                    right.push(left.pop());
            }else if(c == '>'){
                if(!right.isEmpty())
                    left.push(right.pop());
            }else if(c == '-'){
                if(!left.isEmpty())
                    left.pop();
            }else{
                left.push(String.valueOf(c));
            }
        }

        System.out.println(join(left.toArray(), right.toArray()));
    }

    static StringBuilder join(Object[] o1, Object[] o2){
        StringBuilder sb = new StringBuilder();
        for(Object o : o1){
            sb.append(o);
        }
        for(int i = o2.length-1; i>=0; i--){
            sb.append(o2[i]);
        }
        return sb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solution(br.readLine());
        }
    }
}
