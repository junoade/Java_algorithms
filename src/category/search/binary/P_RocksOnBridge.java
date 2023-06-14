package category.search.binary;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_징검다리</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 결정 조건을 어떻게 정의할 것인가?<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 지점 사이의 최소 값 d가 가능한 범위를 고려하자<br/>
 * 2. 결정 조건<br/>
 *  - 지점 사이의 최소 값이 d라고 가정했을 때<br/>
 *  - O(M) 시간 복잡도로 rocks 를 순회하며 현재 지점 - 이전 지점 의 actual 한 거리와 d를 비교하자<br/>
 *  - 만약 실제 거리 차이가 가정한 최소 거리보다 작다면<br/>
 *  - 현재 위치의 돌을 제거해서 가정을 맞추도록 한다고 생각하자<br/>
 *  - 제거한 돌의 갯수와 n를 비교해서 결정 조건의 참/거짓을 반환하자<br/>
 *
 *  3. 반복 범위 관련<br/>
 *  - 조건을 만족하는 요소 중 최대 요소를 찾아야 하므로 [start, end) 범위를 두어 end 포인터가 끝 + 1 를 가르키도록 하자;<br/>
 *  - 반복은 탐색 요소의 개수가 1이 될 때까지 반복한다.<br/>
 *
 *  4. 파라메트릭 서치<br/>
 *  - 가정한 최솟값 d에 대해 결정 조건이 참이라면 더 큰 범위에 대해 탐색할 수 있도록 범위를 조절한다.<br/>
 *     - 동일한 경우에도 더 뒤에 위치 가능한 참인 결정 조건 을 탐색할 수 있도록 한다.<br/>
 *  - 가정한 최솟값 d에 대해 결정 조건이 거짓이라면 더 작은 범위에 대해 탐색할 수 있도록 범위를 조절한다.<br/>
 *     - end = d; 로 하면 end 는 탐색 범위의 exclusive한 위치에 존재하게 된다.<br/>
 *  - d가 갖을 수 있는 모든 경우에 대해 이분 탐색을 수행하므로 [F, F] 인 경우는 존재하지 않는다.<br/>
 *
 *  5. 기타<br/>
 *  - n = rocks.length; 일 때 이 떄 d는 마지막 위치 - 초기 위치 = distance가 d가 될 것이다.<br/>
 *  - 이를 rocks의 마지막에 넣어주기 위해, 배열 길이 L + 1을 갖는 배열로 copy하고 마지막 요소 위치에 추가해준다.<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 : 22.2점 </b><br/>
 * <p> 메모리  100MB, 실행시간 : 26ms <br/>
 * --------------------------------------------------------------
 */
public class P_RocksOnBridge {
    public int solution(int distance, int[] rocks, int n) {

        // 답해서 구하고자 하는 distance의 가능한 범위;
        // 이 범위안에 답은 반드시 하나 포함된다.
        // 조건을 만족하는 범위 중 최대값을 찾는 경우이므로 [start, end)
        int start = 1;
        int end = distance + 1;

        // 만약 n = rocks.length 인 경우
        // 이 때 구하고자 하는 d는 end - start 일 것이다.
        // 이를 포함해서 검사하도록 하자
        final int L = rocks.length;
        rocks = Arrays.copyOf(rocks, L + 1); // int[] original, int newLength;
        rocks[L] = distance;
        Arrays.sort(rocks);

        while(end - start > 1) {
            int d = (start + end) / 2; // 각 지점 사이의 최솟값을 d라고 가정

            boolean cond = isPossible(d, n, rocks); // 결정 조건을 검사

            if(cond) {
                // 더 큰 d를 가정하고 검사하도록
                start = d;
            } else {
                // 더 작은 d를 가정하고 검사할 수 있도록
                end = d;
            }
        }

        // [F, F] 인 경우는 존재하지 않으므로 별도의 검사가 필요하지 않다

        return start;
    }

    // 결정 조건
    // 각 지점 사이의 최솟값을 d라고 고정했다고 가정하자.
    // 현재 위치 - 이전 위치의 거리(acutal)가 < d 라면 앞선 가정에 맞지 않으므로
    // 현재 위치의 돌을 제거한다고 고려하자 removeCount++
    // 최종 removeCnt 와 n을 비교해서 n개 이하를 제거했을 때 최소 거리 d를 얻을 수 있는지 결정한다.
    boolean isPossible(int d, int n, int[] rocks) {
        // O(M)
        int removeCnt = 0;
        int last = 1;

        for(int rock : rocks) {
            int actual = rock - last;
            if(actual < d) {
                removeCnt++;
                continue;
            }

            last = rock;
        }

        return removeCnt <= n;
    }
}
