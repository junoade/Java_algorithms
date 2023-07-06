package category.search.bfs;

import java.io.*;
import java.util.*;

public class SM_SwimmingContest {

    static int N;

    // 이동용
    // u - r - d - l
    static final int[] dx = {-1, 0, 1, 0};
    static final int[] dy = {0, 1, 0, -1};

    // 기록용
    static int[][] times;
    static boolean[][] visited;

    static int solution(int[][] maps, int[] startPos, int[] endPos) {
        // 소용돌의 위치를 따로 기록
        List<int[]> storms = getStorms(maps);

        // 시간 기록 배열 초기화
        visited = new boolean[N][N];
        times = new int[N][N];

        // 시작 위치부터, 종료 위치 도달 까지
        Queue<int[]> queue = new ArrayDeque<>();
        visited[startPos[0]][startPos[1]] = true;
        queue.offer(startPos);

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            int time = times[x][y];

            for (int d = 0; d < dx.length; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 유효성 검사
                if (isOutbound(nx, ny) || maps[nx][ny] == 1 || visited[nx][ny]) {
                    continue;
                }

                // 다음 방문 상태에 대해 도착 여부 확인
                // 도착지점엔 소용돌이가 없음
                // 만약 존재하면 아래로 내려야
                if (nx == endPos[0] && ny == endPos[1]) {
                    return times[x][y] + 1;
                }

                // 소용돌이 치는 곳일 떈
                // 대기하는 경우
                if (maps[nx][ny] == 2 && time % 3 != 2) {
                    times[x][y]++;
                    queue.offer(new int[]{x, y});
                    continue;
                }

                // 소용돌이 멈췄거나,
                // 최초 방문
                times[nx][ny] = times[x][y] + 1;
                visited[nx][ny] = true;
                queue.offer(new int[]{nx, ny});


            }
        }

        return -1;
    }

    static void updateStorms(List<int[]> storms, int[][] maps, int time) {
        for (int[] storm : storms) {
            // storm 이 잠잠해지는 경우
            if (time % 3 == 2) {
                maps[storm[0]][storm[1]] = 0;
            } else {
                maps[storm[0]][storm[1]] = 2;
            }
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    static List<int[]> getStorms(int[][] maps) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(maps[i][j] == 2) {
                    result.add(new int[]{i, j});
                }
            }
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            int[][] inputs = new int[N][N];
            for (int i = 0; i < N; i++) {
                inputs[i] = processInput(br);
            }

            int[] startPos = processInput(br);
            int[] endPos = processInput(br);

            System.out.printf("#%d %d\n", t + 1, solution(inputs, startPos, endPos));
        }
    }

    private static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
