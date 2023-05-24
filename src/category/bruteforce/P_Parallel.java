package category.bruteforce;

import java.util.ArrayList;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 평행</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 조금 복잡하지만, 조합 이용 완탐<br/>
 * 방문한 인덱스 위치, 안한 위치 기록<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_Parallel {
    static boolean[] visited;
    static int[][] dot;
    static int count;


    public int solution(int[][] dots) {
        final int L = dots.length;
        visited = new boolean[L];
        dot = dots;
        count = 0;

        rec(L, 2, 0, 0);
        return count > 0 ? 1 : 0;
    }

    void rec(int n, int r, int depth, int startIdx) {
        // base case
        if(r == depth) {
            List<Integer> indices1 = new ArrayList<>();
            List<Integer> indices2 = new ArrayList<>();
            for(int i = 0; i < visited.length; i++) {
                if(visited[i]) {
                    indices1.add(i);
                } else {
                    indices2.add(i);
                }
            }

            int i1 = indices1.get(0);
            int i2 = indices1.get(1);

            int i3 = indices2.get(0);
            int i4 = indices2.get(1);

            int x1 = dot[i1][0], y1 = dot[i1][1];
            int x2 = dot[i2][0], y2 = dot[i2][1];
            int x3 = dot[i3][0], y3 = dot[i3][1];
            int x4 = dot[i4][0], y4 = dot[i4][1];

            if((y1 - y2) / (double)(x1 - x2) == (y3 - y4) / (double)(x3 - x4)) {
                count++;
            }

            return;
        }

        for(int i = startIdx; i < n; i++) {
            if(visited[i]) {
                continue;
            }

            visited[i] = true;
            rec(n, r, depth + 1, startIdx + 1);
            visited[i] = false;
        }

    }
}
