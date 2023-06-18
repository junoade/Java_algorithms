package category.search.bfs;

import java.util.*;

public class P_EscapeMaze {
    static class Node {
        final char key;
        final int x;
        final int y;
        boolean hasVisitedLever;

        Node(char key, int x, int y, boolean hasVisitedLever) {
            this.key = key;
            this.x = x;
            this.y = y;
            this.hasVisitedLever = hasVisitedLever;
        }

        @Override
        public String toString() {
            return String.format("Node{%s, %d, %d, %s}", key, x, y, hasVisitedLever ? "true" : "false");
        }
    }

    static Node[][] graphs;
    // static boolean[][] visited;
    static int[][] dist;

    // u - r - l - d
    static final int[] dx = {-1, 0, 0, 1};
    static final int[] dy = {0, 1, -1, 0};

    static int N;
    static int M;


    public int solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        graphs = new Node[N][M];
        // visited = new boolean[N][M];
        dist = new int[N][M];

        Node start = new Node('S', -1, -1, false);

        // maps 로 부터 graphs 생성
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char key = maps[i].charAt(j);
                graphs[i][j] = new Node(key, i, j, false);
                if (key == 'S') {
                    start = graphs[i][j];
                }
            }
        }

        // queue 생성
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            // Lever 위치 여부 확인
            if (!node.hasVisitedLever && node.key == 'L') {
                node.hasVisitedLever = true;
                queue.clear();
                queue.add(node);
            }

            // 레버를 당기고 나서 End 위치에 왔는지 여부 확인
            if (node.hasVisitedLever && node.key == 'E') {
                return dist[node.x][node.y];
            }

            for (int d = 0; d < dx.length; d++) {
                int nx = node.x + dx[d];
                int ny = node.y + dy[d];

                // 범위 체크 || X 자리 여부
                if (isOutbound(nx, ny) || graphs[nx][ny].key == 'X') {
                    continue;
                }

                Node next = graphs[nx][ny];
                if (node.hasVisitedLever && next.hasVisitedLever) {
                    continue;
                }

                // 레버를 당긴 상태에서
                // 거리를 갱신
                if (node.hasVisitedLever) {
                    dist[nx][ny] = dist[node.x][node.y] + 1;
                    next.hasVisitedLever = true;
                    queue.offer(next);
                }
                // 레버를 안 당겼을 때
                else {
                    // 레버를 당기지 않았고, 아직 방문하지 않은 경우에만
                    // 갱신하고 노드를 추가하도록
                    if (dist[nx][ny] == 0) {
                        dist[nx][ny] = dist[node.x][node.y] + 1;
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    private boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= M);
    }

    public static void main(String[] args) {
        P_EscapeMaze test = new P_EscapeMaze();
        String[] test01 = {
                "OOOOS",
                "OOOOO",
                "OOOOO",
                "OOOOO",
                "LOOOE"
        };

        String[] test02 = {
                "OOOOS",
                "OXXXX",
                "OOOOO",
                "OOOOO",
                "LXXXE"
        };

        String[] test03 = {
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOS",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOE",
        };

        String[] test04 = {
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOLS",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO",
                "OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOE",
        };

        System.out.println(test.solution(test04));
    }
}