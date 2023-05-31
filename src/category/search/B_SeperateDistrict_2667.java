package category.search;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준2667 - 단지번호 붙이기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1과 0의 값을 같는 정사각 2차원 배열에서<br/>
 * 인접한 1을 모두 한 단지로 잇고<br/>
 * 단지의 갯수와 단지내 집들의 갯수들을 오름차순 정렬<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 인접 행렬 사용; 메모리 공간; O(V^2), V = 425, <br/>
 * DFS 탐색 수행; O(V^2)<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  14000KB / 128 MB , 실행시간 160ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_SeperateDistrict_2667 {
    // u - r - d - l
    static final int[] dx = {-1, 0, 1, 0}; // row
    static final int[] dy = {0, 1, 0, -1}; // col

    // 기록용
    static int[][] graph;
    static boolean[][] visited;
    static int N;

    static List<Integer> list;
    static int COUNT;

    // V : 425, V^2 ~ 160,000
    static void solution() {

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (graph[x][y] == 0 || visited[x][y]) {
                    continue;
                }

                // 새로운 집인지 확인 (새로운 방문 지역이면 탐색 완료하고 방문 개수 반환)
                visited[x][y] = true;
                COUNT = 1;
                dfs(x, y);
                if (COUNT > 0) {
                    list.add(COUNT);
                }
            }
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (Integer num : list) {
            System.out.println(num);
        }
    }

    static void dfs(int x, int y) {
        // 방문 여부 확인
        // if (visited[x][y]) {return;}
        // visited[x][y] = true;

        // 1인지 확인
        // 새로운 집인지 확인
        // 방문
        for (int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny)) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (graph[nx][ny] == 0) {
                continue;
            }

            visited[nx][ny] = true;
            COUNT++;
            dfs(nx, ny);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solution();
    }
}
