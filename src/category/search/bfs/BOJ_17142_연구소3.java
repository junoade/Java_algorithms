package category.search.bfs;

import java.io.*;
import java.util.*;

public class BOJ_17142_연구소3 {
    static int N, M;
    static int[][] maps;

    // 상 하 좌 우
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int MIN; // 구하고자 하는 값
    static int EMPTY_CNT;

    // 1. 초기 바이러스 위치 좌표 순서쌍을 받아서 kCm 조합 처리
    static void solution(List<int[]> virus) {

        MIN = Integer.MAX_VALUE;

        // 최초 활성화 조합 생성
        int[][] initVirus = new int[M][2];

        comb(virus, initVirus, 0, 0);


        if (MIN == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(MIN);
        }
    }

    static void comb(List<int[]> virus, int[][] initVirus, int idx, int depth) {

        // base case
        if (depth == M) {
			/*for(int[] v : initVirus) {
				System.out.println(Arrays.toString(v));
			}
			System.out.println("===========================");*/

            // bfs 수행
            int localTime = bfs(initVirus);

            if (localTime != -1) {
                MIN = Math.min(MIN, localTime);
            }
            return;
        }


        for (int i = idx; i < virus.size(); i++) {
            initVirus[depth] = virus.get(i);
            comb(virus, initVirus, i + 1, depth + 1);
        }
    }

    static int bfs(int[][] initVirus) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][N];
        int[][] dist = new int[N][N];

        int temp = EMPTY_CNT;
        int result = 0;

        for (int[] virus : initVirus) {
            v[virus[0]][virus[1]] = true;
            dist[virus[0]][virus[1]] = 0;
            q.add(virus);
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];

            for (int d = 0; d < dx.length; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                // 바이러스 이동 불가능한 경우
                if (isOutbound(nx, ny) || maps[nx][ny] == 1 || v[nx][ny]) {
                    continue;
                }

                // 이미 바이러스가 있던 공간이라면
                // 활성이든 아니든
                dist[nx][ny] = dist[x][y] + 1;
                if (maps[nx][ny] == 0) {
                    result = Math.max(result, dist[nx][ny]);
                    temp--;
                }

                v[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }

        if (temp != 0) {
            return -1;
        } else {
            return result;
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }


    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("res/0830/bj_17142_simple.txt"));
        // System.setIn(new FileInputStream("res/0830/bj_17142_hard.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][N];
        EMPTY_CNT = 0;
        List<int[]> virus = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 2) {
                    virus.add(new int[]{i, j});
                }

                if (maps[i][j] == 0) {
                    EMPTY_CNT++;
                }
            }
        }

        solution(virus);
        br.close();
    }
}
