package category.search;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 방의 개수 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1. 인접행렬 vs 인접리스트; 인접리스트로 구현<br/>
 * 2. 최초 vertex부터 주어진 vertex들을 순회<br/>
 * 3. 그러는 동시에 vertex들의 인접 리스트들을 구성(방을 만드는 것)<br/>
 * 4. 새로운 방의 조건? 방문한적이 있지만, 아직 해당 정점의 인접리스트에 포함되지 않은 정점을 지날때(다시 돌아올 때)<br/>
 * 5. 끝난 후에는 간선을 이어주고 정점 상태 이동<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 475MB , 실행시간 786.44ms<br/>
 * --------------------------------------------------------------
 */
public class P_CountRoom {
    static class Vertex {
        final int x;
        final int y;
        final String id;
        Set<String> adjVertices; // 해당 정점과 인접한 정점 리스트 관리; 방

        Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            id = getId();
            adjVertices = new HashSet<>();
        }

        public String getId() {
            return String.format("(%d, %d)", x, y);
        }
    }

    // int형 변수 d로 주어진 방향에 맞게 인덱싱
    // 0:n, 1:ne, 2:e, 3:se, 4:s, 5:sw, 6: w, 7:nw;
    static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static final int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

    public int solution(int[] arrows) {
        int answer = 0;
        Map<String, Vertex> visited = new HashMap<>(); // 전체적으로 방문한 정점 관리

        // 최초의 (0, 0) 방문
        Vertex v = new Vertex(0, 0);
        visited.put(v.id, v);

        // O(N*(V+E))
        for(int d : arrows) {
            // 주의 대각선 한번 이동시 두개의 삼각형이 생길 수 있으므로
            for(int i = 0; i < 2; i++) {
                // d의 값: 방향 인덱스
                // 이전에 방문한 정점에서
                // 새로운 방향으로 이동
                int nx = v.x + dx[d];
                int ny = v.y + dy[d];
                Vertex temp = new Vertex(nx, ny);

                // 방문한 적이 없는 정점이라면
                if(!visited.containsKey(temp.id)) {
                    visited.put(temp.id, temp);
                }
                // 방문한적이 있지만, 이전 정점의 인접 리스트에 포함되지 않는 경우라면
                else if(!v.adjVertices.contains(temp.id)) {
                    answer++;
                }
                // 해당 시점에 temp 정점은 이미 방문했던 정점, 이미 정점의 인접 리스트에 포함되어 있을 수도 있어서
                // visited.get()으로 찾아주도록 하자;
                // 이전 정점 v와 새로운 정점 u 간선을 이어줌
                Vertex u = visited.get(temp.id);
                v.adjVertices.add(u.id);
                u.adjVertices.add(v.id); // 방문 정점 관리하는 곳에서 찾아와서 이어줘야 함

                // 상태 이동
                v = u;
            }
        }

        return answer;
    }
}
