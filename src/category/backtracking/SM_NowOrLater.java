package category.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b> 삼성(코드트리)-조삼모사</b><br/>
 * - 삼성 SW 역량테스트 2017 하반기 오전 1번 문제
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 조합 n_C_n/2 구현
 * <br/> 순열 nP2 구현 후 모든 경우의 (x, y)쌍에 대해 Pxy + Pyx 합을 더하면서
 * <br/> 주어진 조건 탐색
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 재귀를 이용하여 조합 구현 후
 * <br/> 방문 -> 다음 방문을 위해 초기화 하는 작업 반복
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  34MB / 80MB , 실행시간  426ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class SM_NowOrLater {
    static int[][] arr;
    static int[] work_index;
    static boolean[] visited;
    static int min;
    static int N;


    static void solution() {
        // 조합을 이용해서 n_C_2 와 그 외 (i, j)쌍을 구함
        work_index = new int[N];
        int i = 0;
        Arrays.fill(work_index, i+=1);
        // for(int i = 0; i < N; i++) { work_index[i] = i + 1; }

        visited = new boolean[N];
        comb(0, N, N/2);
    }

    static void comb(int depth, int n, int r) {
        // base case #1: 모든 선택을 완료했을 때
        if(r == 0) {
            int value = calculateP();
            if(value < min) {
                min = value;
            }
            return;
        }

        // base case #2: 배열의 최대 크기까지 탐색을 마쳤을 때
        if(depth == n) {
            return;
        }

        // general case: depth 방문 후, 다음 조합 재귀 호출 하고
        visited[depth] = true;
        comb(depth+1, n, r-1);

        // 돌아와서 백트랙킹 할 수 있도록 false로
        visited[depth] = false;
        comb(depth+1, n, r);
    }

    static int calculateP() {
        ArrayList<Integer> visitedIdx = new ArrayList<>();
        ArrayList<Integer> unvisitedIdx = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            if(visited[i]) {
                visitedIdx.add(i);
            } else {
                unvisitedIdx.add(i);
            }
        }

        return Math.abs(getSum(visitedIdx) - getSum(unvisitedIdx));
    }

    // P(i,j) + P(j,i)를 수행;
    static int getSum(ArrayList<Integer> idxList) {
        int L = idxList.size();
        int result = 0;
        for(int i = 0; i < L - 1; i++) {
            int xIdx = idxList.get(i);
            for(int j = i + 1; j < L; j++ ) {
                int yIdx = idxList.get(j);
                result += arr[xIdx][yIdx] + arr[yIdx][xIdx];
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        min = Integer.MAX_VALUE;

        for(int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        solution();
        System.out.println(min);
    }
}
