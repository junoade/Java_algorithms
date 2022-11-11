package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * --------------------------------------------------------------<br/>
 * <b> 구름 챌린지 - 3주차 폴더플 폰 자판</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 중복되는 입력을 count하다가 새로운 문자로 바뀌면 이전 누적 Idx의 문자열을
 * 스트링 빌더에 삽입
 * 범위가 [0, i-1) 이므로 마지막 문자 처리도 스프링 빌더에 잘 넣어주기
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. Map 구조를 만들어 키보드 자판 자료구조 구현
 * 2. O(N)으로 순회
 * 3. 주요 키포인트를 명심하고 구현
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class PressKeyBoard {
    final HashMap<Character, String> map;

    PressKeyBoard(){
        map = new HashMap();
        map.put('1', "1.,?!");
        map.put('2', "2ABC");
        map.put('3', "3DEF");
        map.put('4', "4GHI");
        map.put('5', "5JKL");
        map.put('6', "6MNO");
        map.put('7', "PQRS");
        map.put('8', "8TUV");
        map.put('9', "WXYZ");
    }

    void solution(int N, String cmd) {
        StringBuilder sb = new StringBuilder();
        int accumIdx = 0;

        for (int i = 0; i < N - 1; i++) {
            boolean accumCondition = cmd.charAt(i) == cmd.charAt(i + 1);
            if (accumCondition) {
                accumIdx++;
            } else {
                appendLastInput(sb, cmd.charAt(i), accumIdx);
                accumIdx = 0;
            }
        }

        // 마지막 중복 입력 / 마지막 입력 처리
        char lastChar = cmd.charAt(N - 1);
        appendLastInput(sb, lastChar, accumIdx);
        System.out.println(sb);
    }

    void appendLastInput(StringBuilder sb, char key, int accumIdx) {
        String temp = map.get(key);
        int idx = accumIdx % temp.length();
        sb.append(temp.charAt(idx));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String cmd = br.readLine();
        PressKeyBoard main = new PressKeyBoard();
        main.solution(N, cmd);
    }
}
