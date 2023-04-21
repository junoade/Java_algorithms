package category.sorting;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 요격 시스템</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 2차원 배열의 각 행의 [1] 번 째 요소로 정렬 후 <br/>
 * 다같이 요격할 수 있는 조건을 계산해가며 answer++ <br/>
 * 비슷한 유형 <br/>
 * - 호텔 룸, 미팅 룸 시간 계획 문제 등<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_MissleAttack {
    public int solution(int[][] targets) {
        // 각 행의 end 인덱스를 기준으로 정렬
        Arrays.sort(targets, (r1, r2) -> Integer.compare(r1[1], r2[1]));
        // System.out.println(Arrays.deepToString(targets));

        int last = -1, answer = 0;
        // y축에 대한 last에 대해 다음 행의 (시작, 마지막) 인덱스를 비교하고 누적해간다.
        for(int[] target : targets) {
            int start = target[0], end = target[1];

            if(last == -1) {
                answer++;
                last = end -1;
                continue;
            }

            // 같이 요격할 수 있는 범위인 경우
            if(start <= last && last < end) {
                continue;
            }

            // start > last 가 되었을 때; 더 이상 같이 요격할 수 없는 경우
            answer++;
            last = end - 1;
        }

        return answer;
    }
}
