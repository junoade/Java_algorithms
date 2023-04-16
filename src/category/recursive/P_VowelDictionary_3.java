package category.recursive;

import java.util.ArrayList;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b> </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_VowelDictionary_3 {
    final char[] SYMBOLS = {'A', 'E', 'I', 'O', 'U'};
    List<String> dict;

    public int solution(String word) {
        dict = new ArrayList<>();
        recVowelDictionary("");
        return dict.indexOf(word);
    }

    private void recVowelDictionary(String str) {
        dict.add(str);

        // (2) 재귀의 결과를 결정짓고, 호출한 함수로 돌아간다.
        if(str.length() == 5) {
            return;
        }

        for(int i = 0; i < SYMBOLS.length; i++) {
            // (1) 현재 상태 str 에서 상태 전이 진행
            recVowelDictionary(str + SYMBOLS[i]);
            // (3) 이 때는 str 이전 상태에서 대기
            // System.out.println(str);
        }
    }

    public static void main(String[] args) {
        P_VowelDictionary_3 test = new P_VowelDictionary_3();
        String word1 = "AAAAE";
        System.out.println(test.solution(word1));
    }
}
