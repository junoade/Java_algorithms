package category.search;

import java.io.*;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;

public class B_Escape {

    static class Node {
        int x;
        int y;
        boolean isWater; // 고슴 도치인지 물인지 구분용

        Node(int x, int y, boolean isWater) {
            this.x = x;
            this.y = y;
            this.isWater = isWater;
        }
    }

    static int N, M;

    static char[][] graphs;
    static boolean[][] visited;
    static int[][] dist;


    // 동 서 남 북
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    // 시작 위치와 종료 위치
    static Node start;
    static Node end;

    static void solution() {
        // 1. 초기 상태에 대한 큐 생성
        // 초기 물의 위치 먼저 넣고, 고슴도치 위치를 마지막에 넣어줌
        Queue<Node> queue = scanAndInit();
        queue.add(start);

        // 2. BFS 탐색 준비
        // 고슴도치가 이동할 때가 되면 이미 1분에 대해 물들이 이동완료된 상태
        while (!queue.isEmpty()) {
            // 물이 움직이는지
            // 고슴도치가 움직이는 지 구분
            Node target = queue.poll();

            // 고슴도치 또는 물이 end에 도착한 경우
            boolean sthArrived = target.x == end.x && target.y == end.y;
            if (sthArrived && !target.isWater) {
                if (target.isWater) {
                    System.out.println("KAKTUS");
                } else {
                    System.out.println(dist[end.x][end.y]);
                }
                break;
            }

            visited[target.x][target.y] = true;

            // 먼저 1분간 고슴도치 또는 물이 움직일 건데
            for (int d = 0; d < dx.length; d++) {
                int nx = target.x + dx[d];
                int ny = target.y + dy[d];

                // [공통] 범위 밖인지 먼저 확인 || 이미 방문한 곳인지 확인 || 돌이 위치한 곳인지 확인
                if (isOutbound(nx, ny) || visited[nx][ny] || graphs[nx][ny] == 'X') {
                    continue;
                }

                boolean status = true;
                if (!target.isWater) {
                    // 현재 대상이 고슴도치이고 이동 가능한 경우
                    // 최단 경로를 갱신
                    dist[nx][ny] = dist[target.x][target.y] + 1;
                    status = false;
                }
                queue.add(new Node(nx, ny, status));
            }
        }
    }

    // 1. 고슴도치의 위치, 초기 물 위치 확인
    static Queue<Node> scanAndInit() {
        Queue<Node> init = new ArrayDeque<>();
        for (int i = 0; i < graphs.length; i++) {
            for (int j = 0; j < graphs[i].length; j++) {
                char symbol = graphs[i][j];
                if (symbol == 'S') {
                    start = new Node(i, j, false);
                } else if (symbol == 'D') {
                    end = new Node(i, j, false);
                } else if (symbol == '*') {
                    init.offer(new Node(i, j, true));
                } else {
                    // nothing
                }
            }
        }
        return init;
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = inputs[0];
        M = inputs[1];

        graphs = new char[N][M];
        visited = new boolean[N][M];
        dist = new int[N][M];

        for (int i = 0; i < N; i++) {
            graphs[i] = br.readLine().toCharArray();
        }

        solution();
    }
}
