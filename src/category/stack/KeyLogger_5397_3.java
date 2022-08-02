package category.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
 * - 직접 스택을 구현하였다.
 * --------------------------------------------------------------
 *
 * <b> 채점 </b>
 * <p> 메모리 70520KB / 256MB , 시간초과 584ms / 1000ms
 * -> 이전보다 시간이 단축되었다. char[] 등 기본자료형을 활용했기 때문으로 해석됨.
 * --------------------------------------------------------------
 */
public class KeyLogger_5397_3 {

    static class MyStack{
        char[] stack;
        int size;
        public MyStack(int length){
            stack = new char[length];
            size = 0;
        }

        void push(char c){
            if(c != ' '){
                stack[size++] = c;
            }
        }

        char pop(){
            if(size == 0){
                return ' ';
            }else{
                return stack[--size];
            }
        }

        void clear(){
            size = 0;
        }

        @Override
        public String toString(){
            return String.valueOf(stack, 0, size); // char[] 형 배열을 offset 부터  size까지 문자열로
        }

        public String reversedString(){
            char[] reversed = new char[size];
            int lastIdx = size -1;
            for(int i = 0; i<size; i++){
                reversed[i] = stack[lastIdx-i];
            }
            return String.valueOf(reversed);
        }
    }

    public static void solution(String input){
        int N = input.length();
        MyStack left = new MyStack(N+1);
        MyStack right = new MyStack(N+1);

        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            switch (c){
                case '<':
                    right.push(left.pop());
                    break;
                case '>':
                    left.push(right.pop());
                    break;
                case '-':
                    left.pop();
                    break;
                default:
                    left.push(c);
                    break;
            }
        }
        /* + 연산자를 쓰고 안쓰고 시간 차이는 30ms 차이 정도 쓰는게 더 빠름*/
        System.out.println(left+right.reversedString());
        left.clear();
        right.clear();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            solution(br.readLine());
        }
    }
}
