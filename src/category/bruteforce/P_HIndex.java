package category.bruteforce;
/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 H-Index </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * H-index 의미 잘 이해하기<br/>
 * N의 크기 잘 고려하기 <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * N=1000 이라 O(N^2)의 완전탐색 풀이 <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  80MB / 실행시간  1.53ms <br/>
 * --------------------------------------------------------------
 */
public class P_HIndex {
    // h = 1000부터 가능한 모든 경우를 탐색
    public int solution(int[] citations) {
        final int L = citations.length;

        // 1000 * 1000
        for(int h = L; h >= 1; h--) {
            if(isValid(citations, h)){
                return h;
            }
        }

        return 0;
    }

    private boolean isValid(int[] arr, int h) {
        int count = 0;
        // O(N), N = 1000
        for(int e : arr) {
            if(e >= h) {
                count++;
            }
        }
        return count >= h;
    }
}
