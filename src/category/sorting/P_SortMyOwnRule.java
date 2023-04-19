package category.sorting;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 문자열 내 마음대로 정렬하기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * Arrays.sort(T[], Comparator\<\? super T\> ) 이용</><br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 80MB, 실행시간  1.34ms <br/>
 * --------------------------------------------------------------
 */
public class P_SortMyOwnRule {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, (s1, s2) -> {
            // n번째 문자가 같은 문자라면 각 문자열에 대해 사전순으로 정렬
            if(s1.charAt(n)  == s2.charAt(n)) return s1.compareTo(s2);
            // 아닐땐, 주어진 조건대로 정렬
            // 오름차순 정렬, 이미 오름차순이면 -1 반환하게 됨. swap X
            return Character.compare(s1.charAt(n), s2.charAt(n));
        });

        return strings;
    }
}
