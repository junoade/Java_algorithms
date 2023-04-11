package category.bruteforce;

import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 카펫</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 문제 파악 하기, 완전 탐색의 상태 공간 크기 파악<br/>
 * 아 yellow의 약수의 개수 만큼이 겠구나<br/>
 * yellow의 약수는 어떻게 구할까? (1 x 4) (4 x 1)이 있을 수 있을 텐데?<br/>
 * -> 제한 조건에 기술해놨구나!<br/>
 *
 * 상태 전이는? 반복문으로 진행<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 약 75MB, 실행시간  약 0.04ms ~ 12ms <br/>
 * --------------------------------------------------------------
 */
public class P_Carpet {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
        HashSet<int[]> sets = getCandidate(yellow);

        for(int[] set: sets) {
            int col = set[0], row = set[1];
            int expected = (col + 2) * 2 + row * 2;
            if(expected == brown) {
                // 가로 부터
                answer = new int[]{col + 2, row + 2};
                break;
            }
        }

        return answer;
    }

    HashSet<int[]> getCandidate(int yellow) {
        HashSet<int[]> candidates = new HashSet<>();
        final int L = yellow;
        for(int n = 1; n <= L; n++) {
            if(yellow % n == 0 && isColBiggerThanRow(n, yellow/n)) {
                // 가로(열) x 세로(행) (가로는 세로의 길이와 같거나 길다);
                candidates.add(new int[]{yellow / n, n});
            }
        }
        return candidates;
    }

    boolean isColBiggerThanRow(int r, int c) {
        return c >= r;
    }

    public static void main(String[] args) {
        P_Carpet p = new P_Carpet();
        int[][] inputs = {{10, 2}, {8, 1}};
        System.out.println(Arrays.toString(p.solution(inputs[1][0], inputs[1][1])));
    }
}
