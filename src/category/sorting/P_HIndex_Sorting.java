package category.sorting;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 H-Index </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * H-index 의미 잘 이해하기<br/>
 * 정렬을 이용해 시간복잡도 O(NlogN)을 갖는 풀이 고안하기<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * N=10,000이라면 시간복잡도 O(NlogN)을 갖는 풀이 필요<br/>
 * 1. 오름차순 정렬 O(N log N)<br/>
 * 2. 논문 개수를 기준으로 각 논문이 인용된 횟수(value) 와 value 이상의 논문 개수(paperCnt = hIdx)를 구함
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  80MB / 실행시간  0.81ms <br/>
 * --------------------------------------------------------------
 */

public class P_HIndex_Sorting {
    public int solution(int[] citations) {
        final int L = citations.length;
        Arrays.sort(citations);

        // 초기 논문의 개수는 배열의 길이 L로 설정
        // [1, 4, 5] 의 경우 L = 3
        // L - paperCnt = 0 번 논문의 인용된 값(value)은 1;
        // 이 때 paperCnt = 3 이며 이는 1번(value) 이상 인용된 논문의 개수는 3개임을 의미
        // h번 이상(value) 인용된 논문의 개수 논문의 개수(paperCnt) 인 경우를 찾고자 함
        // value >= paperCnt
        for(int paperCnt = L; paperCnt >= 1; paperCnt--) {
            int targetIdx = L - paperCnt;
            int value = citations[targetIdx];
            if(value >= paperCnt) {
                return paperCnt;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        P_HIndex_Sorting test = new P_HIndex_Sorting();
        System.out.println(test.solution(new int[]{3, 0, 6, 1, 5}));
    }
}
