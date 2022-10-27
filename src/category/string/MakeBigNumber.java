package category.string;

/**
 * --------------------------------------------------------------<br/>
 * <b> 큰 수 만들기 </b><br/>
 * 프로그래머스 LV2  22/10/27
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1. 최소한 k + 1의 범위를 탐색하며, 1개를 고르고 k개를 건너뛸 수 있도록
 * 2. [현재 idx, 탐색 제한 idx) 사이에서 max값 찾고 현재 idx 갱신 -> 해당 범위에서의 최댓값을 찾는 Greedy Algorithms
 * 3. 최댓값은 정답 배열에 append
 * 4. 탐색 제한 idx 역시 갱신
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 실패1 : 먼저 [0, k)이내에서 큰 수 위치 찾고, (k, n.length()) 범위에서 남은 k 만큼 작은 위치들을 반복적으르 제거하는 풀이 30점
 * 실패2 : (k, n.length()) 범위에서 남은 k만큼 작은 위치들을 찾는 알고리즘 개선 58점
 * 성공  : 현재 풀이
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 12/12
 * --------------------------------------------------------------
 */
public class MakeBigNumber {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        final int ANSWER_LENGTH = number.length() - k;
        int cursorIdx = 0;

        while(cursorIdx < number.length() && answer.length() != ANSWER_LENGTH){
            int max = 0;
            int windowIdx = k + answer.length() + 1; // 최소한 k+1의 범위를 탐색하여, 1개를 고르고 k개를 건너뛸 수 있도록 한다.

            /* find max in range[cursorIdx, windowIdx) */
            for(int i = cursorIdx; i < windowIdx; i++){
                int value = number.charAt(i) - '0';
                if(value > max){
                    max = value;
                    cursorIdx = i + 1; // 다음 탐색 인덱스 cursor update
                }
            }
            /* max append */
            answer.append(max);
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        MakeBigNumber mb = new MakeBigNumber();
        String testNumber = "812341";
        int k = 4;

        System.out.println(mb.solution(testNumber, k));
    }
}
