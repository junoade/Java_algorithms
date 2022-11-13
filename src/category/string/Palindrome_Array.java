package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * --------------------------------------------------------------<br/>
 * <b> 삼성 D3 - 회문1 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 문자열 배열에서 탐색 길이 만큼 문자열 탐색
 * - 가로행은 substring(i, i+findLength)로
 * - 세로행은 StringBuilder(동기화 지원 X) StringBuffer(동기화 지원 O) 을 이용해서 col에 대해 붙여줬음
 * 회문 판별 (왼쪽 끝, 오른쪽 끝) 부터 중앙까지 : O(N/2)
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class Palindrome_Array {
    final int MAX_SIZE = 8;

    // 8 x 8 배열 입력을 처리하는 메소드
    String[] processInput(BufferedReader br) throws IOException {
        String[] result = new String[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            result[i] = br.readLine();
        }
        return result;
    }

    // 8 x 8 배열안의 Palindrome를 확인하는 함수
    int countPalindrome(int findLength, String[] inputs) {
        int count = 0;
        // 가로 행에 대해 검증
        for (String row : inputs) {
            int to = MAX_SIZE - findLength + 1;
            for (int i = 0; i < to; i++) {
                String temp = row.substring(i, i + findLength);
                if (isPalindrome(temp)) {
                    count++;
                    // i += findLength - 1; // 겹침 방지를 위해 넣었으나 겹침 허용
                }
            }
        }

        // 세로 행에 대해 검증
        for (int col = 0; col < MAX_SIZE; col++) {
            int to = MAX_SIZE - findLength + 1;
            for (int i = 0; i < to; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = i; j < i + findLength; j++) {
                    char colValue = inputs[j].charAt(col);
                    sb.append(colValue);
                }
                if (isPalindrome(sb.toString())) {
                    count++;
                    // i += findLength - 1; // 겹침 방지를 위해 넣었으나 겹침 허용
                }
            }
        }
        return count;
    }

    boolean isPalindrome(String s) {
        int L = s.length();
        for (int i = 0; i < L / 2; i++) {
            char leftVal = s.charAt(i);
            char rightVal = s.charAt(L - i - 1);
            if (leftVal != rightVal) return false;
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        //System.setIn(new FileInputStream("res/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Palindrome_Array sol = new Palindrome_Array();
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            /* 이 부분에 여러분의 알고리즘 구현이 들어갑니다. */
            int findLength = Integer.parseInt(br.readLine());
            String[] inputs = sol.processInput(br);
            int count = sol.countPalindrome(findLength, inputs);
            System.out.printf("#%d %d\n", test_case, count);
            /////////////////////////////////////////////////////////////////////////////////////////////
        }
    }
}
