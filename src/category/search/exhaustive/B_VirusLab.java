package category.search.exhaustive;

import java.io.*;
import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>B_연구소 14502</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 완탐, 그래프 탐색<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 입출력 N,M과 벽의 개수가 3으로 작아서<br/>
 * 완전 탐색 풀이를 고민했다.<br/>
 * 모든 벽의 가능한 경우의 수를 따져보니, 62 * 61 * 60, 약 216,000 가지 정도로<br/>
 * 충분히 시간 제한안에 풀 수 있다고 판단이 들었다.<br/>
 * 그 다음 각 공간에 대해 독립적으로 다룰 수 있도록 deepCopy한 배열을 가지고<br/>
 * 초기 바이러스 위치에 대해 dfs 탐색을 수행했다. O(10 * V^2); V = 64 (2차원 배열내 모든 정점의 수)<br/>
 * 총 계산 량은 20만 * 64(배열 복사 시간) * 3만 정도로 360만 정도라 시간 안에 풀 수 있다.<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  50268KB / 512MB , 실행시간  388ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_VirusLab {

    static int N, M;
    static int[][] maps;
    // 상 - 하 - 좌 - 우
    static final int[] dx = {-1, 1, 0, 0}; // row
    static final int[] dy = {0, 0, -1, 1}; // col


    static List<Node> zeroList;
    static List<Node> virusList;

    static class Node {
        final int x;
        final int y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("{%d, %d}", x, y);
        }
    }

    static void solution() {
        // 전체 스캔해서 값 0을 갖는 좌표 생성
        // 값 2를 갖는 좌표 리스트를 생성
        scan();

        // 가능한 0의 위치에 벽 3개를 세움
        // 최대 63 * 62 * 61 = 216,000 가짓수; 완탐
        List<Node[]> wallList = pickThreeZeros();

        int max = Integer.MIN_VALUE;

        for(Node[] walls : wallList) {
            // 상태를 독립적으로
            // deep Copy;
            int[][] state = copyMaps();

            // 먼저 벽을 세움
            for(Node wall : walls) {
                state[wall.x][wall.y] = 1;
            }

            // 앞서 벽을 세운 상태에서 존재하는 모든 바이러스가 퍼졌을 떄; 그래프 탐색
            // S 를 구함
            // 최대값 반환
            max = Math.max(max, search(state));
        }

        System.out.println(max);
    }

    static void scan() {
        zeroList = new ArrayList<>();
        virusList = new ArrayList<>();

        for(int i = 0; i < maps.length; i++) {
            for (int j = 0; j < maps[i].length; j++) {
                if(maps[i][j] == 0) {
                    zeroList.add(new Node(i, j));
                } else if(maps[i][j] == 2) {
                    virusList.add((new Node(i, j)));
                }
            }
        }
    }

    // 벽을 놓고 난 후
    // 초기 바이러스들에 대해
    // 퍼뜨리고 나서 안전 영역의 값을 반환
    static int search(int[][] state) {
        for (Node virus : virusList) {
            dfs(virus.x, virus.y, state);
        }

        return getArea(state);
    }

    // 바이러스를 전파함
    static void dfs(int x, int y, int[][] state) {

        if(state[x][y] != 2) {
            return;
        }

        for(int d = 0; d < dx.length; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(isOutbound(nx, ny)) {
                continue;
            }

            if(state[nx][ny] != 0) {
                continue;
            }

            state[nx][ny] = 2;
            dfs(nx, ny, state);
        }
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }


    // 안전 영역을 계산
    static int getArea(int[][] state) {
        int result = 0;
        for(int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[i].length; j++) {
                if(state[i][j] == 0) {
                    result++;
                }
            }
        }
        return result;
    }

    static List<Node[]> pickThreeZeros() {
        List<Node[]> result = new ArrayList<>();

        for(int i = 0; i < zeroList.size() - 2; i++) {
            for(int j = i + 1; j < zeroList.size() - 1; j++) {
                for(int k = j + 1; k < zeroList.size(); k++) {
                    Node[] arr = new Node[3];
                    arr[0] = zeroList.get(i);
                    arr[1] = zeroList.get(j);
                    arr[2] = zeroList.get(k);
                    result.add(arr);
                }
            }
        }

        return result;
    }

    static int[][] copyMaps() {
        int[][] result = new int[N][M];
        for(int i = 0; i < N; i++) {
            result[i] = Arrays.copyOf(maps[i], M);
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = getInputs(br);
        N = inputs[0];
        M = inputs[1];

        maps = new int[N][M];

        for(int i = 0; i < N; i++) {
            maps[i] = getInputs(br);
        }

        solution();
    }

    private static int[] getInputs(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
