package category.string;

import java.util.stream.IntStream;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 자연수 뒤집어 배열로 만들기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 정수형 리터럴을 문자열로 바꾸고
 * <br/> 그 char[]에 대해 거꾸로 조회 또는 스트림을 활용하여 결과 반환하기
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 정확도 100점 13/13
 * --------------------------------------------------------------
 */
public class P_ReverseString {
    public int[] solution01(long n) {
        char[] arr = String.valueOf(n).toCharArray();
        final int L = arr.length;
        int[] answer = new int[L];
        for(int i = 0; i < L; i++) {
            answer[i] = arr[L-i-1] - '0';
        }
        return answer;
    }

    public int[] solution02(long n) {
        StringBuilder sb = new StringBuilder().append(n).reverse();
        IntStream intStream = sb.chars();
        return intStream.map(Character::getNumericValue).toArray();
    }
}
