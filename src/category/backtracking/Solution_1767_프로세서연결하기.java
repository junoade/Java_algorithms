package category.backtracking;

import java.io.*;
import java.util.*;

public class Solution_1767_프로세서연결하기 {

    static int N;
    // 상 하 좌 우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int MIN_WIRE, MAX_CORES;

    // cores의 방문 순서를 조정하면서
    // 최대한 선을 연결할 수 있을 때까지
    // dfs 탐색하다가
    // 할 수 없게 되면 MIN 갱신
    static int solution(int[][] maps, List<int[]> cores) {
        // display(maps, cores);
        MIN_WIRE = Integer.MAX_VALUE;
        MAX_CORES = 0;

        dfs(maps, cores, 0, 0, 0);
        return MIN_WIRE;
    }

    static void dfs(int[][] maps, List<int[]> cores, int depth, int coreCnt, int wireCnt) {

        // 방문 해서 전선을 펼쳤던 못 했든
        if (depth == cores.size()) {
            if (MAX_CORES < coreCnt) {
                MAX_CORES = coreCnt;
                MIN_WIRE = wireCnt;
            } else if (MAX_CORES == coreCnt) {
                MIN_WIRE = Math.min(MIN_WIRE, wireCnt);
            }
            return;
        }

        // 아직 방문 안한 코어인 경우
        // 4방향에 전선 직선 연결 가능성 여부 검사
        // 여기선 BFS를 써야할 것 같은데?
        // 일단 DFS로
        int[] pos = cores.get(depth);
        final int x = pos[0], y = pos[1];

        int flag = 0;
        // int[][] copiedMaps = getCopy(maps);
        for (int d = 0; d < dx.length; d++) {
            if (!hasEmptyLine(pos[0], pos[1], d, maps)) {
                flag++;
                continue;
            }

            // 전선 연결해보고 갯수 세주고
            // dfs 탐색 돌린다.
            int temp = 0;
            int nx = x, ny = y;
            while (!isEdge(nx, ny)) {
                nx += dx[d];
                ny += dy[d];
                maps[nx][ny] = -1;
                temp++;
            }

            dfs(maps, cores, depth + 1, coreCnt + 1, wireCnt + temp);

            // 원래대로 돌려줌
            nx = x;
            ny = y;
            while (!isEdge(nx, ny)) {
                nx += dx[d];
                ny += dy[d];
                maps[nx][ny] = 0;
            }

            dfs(maps, cores, depth + 1, coreCnt, wireCnt);
        }

        if (flag == dx.length) {
            // display(maps, cores);
            dfs(maps, cores, depth + 1, coreCnt, wireCnt);
            // display(maps, cores);
        }

    }

    static boolean hasEmptyLine(int x, int y, int d, int[][] maps) {
        int nx = x, ny = y;

        while (!isEdge(nx, ny)) {
            nx += dx[d];
            ny += dy[d];

            // 다른 코어가 있거나, 다른 전선이 하나라도 있으면 바로 false
            if (maps[nx][ny] == 1 || maps[nx][ny] == -1) {
                return false;
            }
        }
        // 가장자리에 도착하고나서까지 장애물이 없었다면
        return true;
    }

    static int[][] getCopy(int[][] maps) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(maps[i], N);
        }
        return result;
    }

    static void display(int[][] maps, List<int[]> cores) {
        // 확인부터 해볼까요?
        for (int[] map : maps) {
            System.out.println(Arrays.toString(map));
        }
        for (int[] c : cores) {
            System.out.print(Arrays.toString(c) + ", ");
        }
        System.out.println();
    }

    static boolean isEdge(int x, int y) {
        return (x == 0) || (y == 0) || (x == N - 1) || (y == N - 1);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/category/backtracking/input/input_swea_1767.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            int[][] maps = new int[N][N];
            List<int[]> cores = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int value = Integer.parseInt(st.nextToken());
                    maps[i][j] = value;
                    if (value == 1 && !isEdge(i, j)) {
                        cores.add(new int[]{i, j});
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(solution(maps, cores)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
