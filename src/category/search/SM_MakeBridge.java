package category.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>SM 하나로 D4 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 모든 지점에 대해 각 지점을 출발 지점으로 했을 때<br/>
 * 매번 최단 거리를 구해 다리를 하나 지어주고 이동하는 방법 고안<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 (8/20) </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class SM_MakeBridge {

    static int[] x;
    static int[] y;
    static boolean[] visited;

    static int N;
    static double rate;
    static double min;

    static void solution(){
        min = Double.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            visited = new boolean[N];
            rec_search( i, 0);
        }
    }

    static void rec_search(int startIdx, double value) {
        int minIdx = -1;
        double minDistance = Double.MAX_VALUE;
        // O(N) = O(1000)
        for(int i = 0; i < visited.length; i++) {
            if(visited[i]) {
                continue;
            }

            // 최소 거리 비용을 갖는 지점 확인
            double distance = calculate(startIdx, i);
            if (minDistance > distance) {
                minIdx = i;
                minDistance = distance;
            }
        }

        // base case
        if(minIdx == -1) {
            min = Math.min(min, value);
            return;
        }

        // 다음 노드 확인
        visited[minIdx] = true;
        value += getTax(calculate(startIdx, minIdx));
        rec_search(minIdx, value);
    }

    static double calculate(int srcIdx, int targetIdx) {
        double left = Math.pow(x[targetIdx] - x[srcIdx], 2);
        double right = Math.pow(y[targetIdx] - y[srcIdx], 2);
        return Math.sqrt(left + right);
    }

    static double getTax(double distance) {
        return rate * Math.pow(distance, 2);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            y = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            rate = Double.parseDouble(br.readLine());
            solution();
            System.out.printf("#%d %d\n", i + 1, (long)(min * 10 + 0.5) / 10);
        }
    }
}
