package daily.a1008;

import java.util.Arrays;

public class RobotCleaning {

    final int HORIZON = 0;
    final int VERTICAL = 2;

    int MAX_N;

    // 수평 - 수직 순
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    /**
     * n in [2, 500]
     * horizontal : true 면 수평, false 면 수직
     * @param n
     * @param horizontal
     * @return
     */
    int[][] solution(int n, boolean horizontal) {
        int[][] answer = new int[n][n]; // 0'based indexing으로 리턴해야함
        boolean[][] v = new boolean[n][n];

        MAX_N = n;

        dfs(answer, v, 0, 0, horizontal, 1, 1);
        return answer;
    }

    void dfs(int[][] answer, boolean[][] v, int x, int y, boolean dirStatus, int value, int curN) {
        v[x][y] = true;
        answer[x][y] = value;

        // base case
        if(value == MAX_N * MAX_N) {
            return;
        }

        // 범위 확장 [주의]
        int nextN = Math.max(x, y) + 1;
        if(value == nextN * nextN) {
            // 그냥 ++하니까 중복 호출 때문에 값이 커져 안되는 경우가 있었다. [주의]
            // 이유 : 정점 기존 방향이 안되고 새로운 방향으로 탐색하게 될 때
            // 동일한 좌표, value 그러나 다른 dirStatus, curN이 호출되게 되는데
            // 중복 호출되면서 조건은 두번 만족해서 curN++; 으로 구현하면 값이 계속 늘어남
            curN = nextN + 1;
        }

        // 기존 방향 결정
        int dir = getDir(dirStatus);

        // 기존 방향대로 범위 내에서 갈 수 있는 만큼 가본다
        int flag = 0;
        for(int d = dir; d < dir + 2; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 부분 범위 밖을 벗어나려는 거 막으면서
            // 전체 범위 밖을 벗어나려는 것 역시 막고 [주의 : 최종 탐색 시 curN은 MAX_N + 1인 상태라]
            // 이미 방문한 곳 방문 막음
            if(isOutbound(nx, ny, curN) || isOutbound(nx, ny, MAX_N) ||v[nx][ny]) {
                flag++;
                continue;
            }

            // 방향 유지하는 경우
            if(Math.max(x, y) == Math.max(nx, ny)) {
                dfs(answer, v, nx, ny, dirStatus, value + 1, curN);
            } else {
                dfs(answer, v, nx, ny, !dirStatus, value + 1, curN);
            }

        }

        // 기존 방향으로 못 가는 경우
        if(flag == 2)
            dfs(answer, v, x, y, !dirStatus, value, curN); // 현재 위치에서 반대 방향으로 탐색 수행해봄 // curN 갱신 주의

    }

    boolean isOutbound(int x, int y, int curN) {
        return (x < 0 || x >= curN) || (y < 0 || y >= curN);
    }

    int getDir(boolean dirStatus) {
        return dirStatus ? HORIZON : VERTICAL;
    }

    public static void main(String[] args) {
        RobotCleaning test = new RobotCleaning();

        int[][] answer1 = test.solution(4, true);
        int[][] answer2 = test.solution(5, false);

        for(int[] a : answer1) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println();
        for(int[] a : answer2) {
            System.out.println(Arrays.toString(a));
        }

    }
}
