package category.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 컴백홈 1189</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 2차원 공간에서 dx, dy로 이동<br/>
 * 미리 벽이 존재함(visited = true)로 미리 할당<br/>
 * distance(depth) 도달 여부 확인<br/>
 * 유효한 다음 위치에 대한 재귀 함수로 들어 가서 확인했다가 돌아와서 visited = false해서 백트랙킹 할 수 있도록<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 처음엔 도달 여부를 바로 확인해주고 COUNT++ 해줬는데 K==10일 때 7이 나오는 등 문제에서 원하는 바와 달랐다.<br/>
 * distance == K인 경우에 대해서만 도달 여부를 확인해주고 해당 부분에 도달하는 여러 경우가 누적되어 원하는 가짓수를 구할 수 있게 됨<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  14732KB / 128 MB , 실행시간 140ms / 2000ms<br/>
 * --------------------------------------------------------------
 */

public class B_ComebackHome_1189 {

    static int R, C, K;
    static boolean[][] visited;

    // u - r - d - l
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    static int COUNT;

    static void solution() {
        int x = R - 1, y = 0;
        visited[x][y] = true;
        COUNT = 0;
        rec(x, y, 1); // 시작점 방문하면 distance : 1
        System.out.println(COUNT);
    }

    static void rec(int x, int y, int distance) {

        // 여기서 도달 여부를 판단하려 했는데
        // 6, 6, 6, 8, 8, 10, 6 이 있다고 하고
        // K = 10이 주어지면 1개만 반환 되는게 아니라 7이 반환됨


        // base case : 주어진 depth에 도달했을 때
        if (distance == K) {
            //System.out.printf("탐색 완료{%d, %d}\n", x, y);
            if (x == 0 && y == C - 1) {
                COUNT++;
            }
            return;
        }

        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny)) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }
            visited[nx][ny] = true;
            rec(nx, ny, distance + 1);
            visited[nx][ny] = false;
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= R) || (y < 0 || y >= C);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        R = inputs[0];
        C = inputs[1];
        K = inputs[2];

        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String[] temp = Arrays.stream(br.readLine().split("")).toArray(String[]::new);
            for (int j = 0; j < temp.length; j++) {
                // T인 위치는 미리 방문했다고 하자
                if (temp[j].equals("T")) {
                    visited[i][j] = true;
                }
            }
        }

        solution();
    }
}
