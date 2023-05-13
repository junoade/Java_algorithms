package category.search;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_징검다리</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 바위 배열 M을 순회하면서; M in [1, 50,000] <br/>
 *  - 바위 i, 바위 j를 거리 비교할 수 있도록 [1, 50,000] 에 대해 또 순회 O(M^2) = 25억
 *  - 바위 위치에서 시작점 , 끝점 과 거리 비교 (아무런 장애물 없다고 가정)
 *  - 바위 i에서 다음 바위 j와의 거리 비교
 *  - min distance 값 갱신
 *  2. 해당 최솟값 중에 최대를 반환
 * --------------------------------------------------------------<br/>
 * <b> 채점 : 22.2점 </b><br/>
 * <p> 메모리  200MB, 실행시간 : 시간 초과<br/>
 * --------------------------------------------------------------
 */
public class P_RocksOnBridge {

    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;

        // O(M^2)
        long start = 1;
        long end = n;

        final int L = rocks.length;
        int totalMax = -1;


        for(int i = 0; i < L; i++) {
            long[] temp = new long[L + 1];
            long min = Long.MAX_VALUE;

            for(int j = 0; j < L; j++) {
                if(i == j) {
                    temp[0] = Math.abs(rocks[i] - start); // 시작점에서 해당 지점까지 거리
                    temp[L - 1] = Math.abs(end - rocks[i]); // 끝점에서 해당 지점까지 거리
                    if(min > temp[0]) {
                        min = temp[0];
                    }

                    if(min > temp[L - 1]) {
                        min = temp[L - 1];
                    }

                    continue;
                }

                temp[j] = Math.abs(rocks[j] - rocks[i]);
                if(min > temp[j]) {
                    min = temp[j];
                }
            }

            if(min > totalMax) {
                totalMax = (int)min;
            }
        }
        return totalMax;
    }
}
