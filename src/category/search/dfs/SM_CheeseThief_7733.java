package category.search.dfs;

import java.io.*;
import java.util.*;

public class SM_CheeseThief_7733 {

    // 기록
    static int N;
    static int[][] graphs;
    static Map<Integer, List<int[]>> map;
    static int max;

    // 상 - 하 - 좌 - 우
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};

    static int solution() {
        List<Integer> keys = new ArrayList<>(map.keySet());
        keys.sort(Comparator.naturalOrder());
        max = Integer.MIN_VALUE;

        for (int key : keys) {
            // key와 동일한 값을 갖는 위치에 대해 0로 변환
            update(key);
            // dfs 탐색 수행하여 치즈 덩어리 갯수 반환
            boolean[][] visited = new boolean[N][N];
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    count += dfs(i, j, visited);
                }
            }
            max = Math.max(max, count);
        }

        return max;
    }

    static void update(int key) {
        for (int[] pos : map.get(key)) {
            int x = pos[0], y = pos[1];
            graphs[x][y] = 0;
        }
    }

    static int dfs(int x, int y, boolean[][] visited) {
        if(visited[x][y] || graphs[x][y] == 0) {
            return 0;
        }

        visited[x][y] = true;
        int count = 0;
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny) || visited[nx][ny] || graphs[nx][ny] == 0) {
                count++;
                continue;
            }

            dfs(nx, ny, visited);
        }

        // 더 갈 경로가 없으면 1 반환
        if (count > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("res/2307/0712-02-input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            graphs = new int[N][N];
            map = new HashMap<>();

            // 입력 처리
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int j = 0;
                while (st.hasMoreTokens()) {
                    int value = Integer.parseInt(st.nextToken());
                    graphs[i][j] = value;

                    // 기록용
                    List<int[]> adjList;
                    int[] inputs = {i, j};
                    if (map.containsKey(value)) {
                        adjList = map.get(value);
                    } else {
                        adjList = new ArrayList<>();
                    }
                    adjList.add(inputs);
                    map.put(value, adjList);

                    j++;
                }

            }
            sb.append("#").append(tc).append(" ").append(solution()).append("\n");
        }
        System.out.print(sb);
    }
}
