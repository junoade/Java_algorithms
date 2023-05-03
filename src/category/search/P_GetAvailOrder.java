package category.search;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 순위 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 일부의 방향 그래프의 기록을 바탕으로 순위가 결정된 선수의 개수 반환하기<br/>
 * - 단서1) 모든 선수 [1~100] 는 다른 선수들과 결투를 해야함 그러나 일부 기록만 전달됨
 * - 단서2) 선수 a가 선수 b를 이긴다고 표현하는 방향 그래프
 * - 단서3) 만약 한 선수가 다른 선수와 결투를 하면 승리 + 패배 + 1(자신)은 = 선수 사람 수 N과 같을 것
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * O(N * (V+E))
 * 1. 그래프 인접 행렬로 구현; N의 크기가 작고 손쉽게 인접 정점을 인덱싱 할 수 있도록<br/>
 * 2. 모든 정점에 대해 각자 시작노드로 자기가 이긴 수 + 패배한 수 + 1 == N 인지 확인하기
 * 3. 자기가 이긴 수 : 자기를 시작노드로 해서 인접 노드들을 탐색하며 이긴 수를 count; 만약 graph[u][v] = true 라면 정점 u가 정점 v를 이겼다는 것
 *  - DFS 탐색, 재귀로 구현
 * 4. 자기가 진 수 : 자기를 시작노드로 해서 역으로 인접 노드를 탐색하고 v가 자신을 이겼는지 탐색
 *  - DFS 탐색, 재귀로 구현
 *  - base case에서 부터 1이 더해질 수 있도록 count = 1
 *  - 나중에 1 뺴주기
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 84MB , 실행시간  8ms <br/>
 * --------------------------------------------------------------
 */
public class P_GetAvailOrder {
    // 방문 정보를 담는 그래프
    boolean[][] graph;

    public int solution(int n, int[][] results) {
        int answer = 0;
        // 그래프 초기화
        initGraph(n, results);

        // 모든 정점에 대해 탐색 N = [1, 100]
        for (int s = 0; s < n; s++) {
            // 자신 - 자신인 경우 제외
            int win = countForward(s, new boolean[n]) - 1;
            int lose = countBackward(s, new boolean[n]) - 1;

            // 모든 기록에 대해 조회가 가능한 경우
            if (win + lose + 1 == n) {
                answer++;
            }
        }

        return answer;
    }

    // 시작 정점의 인근 정점들을 탐색
    int countForward(int u, boolean[] visited) {
        // base case에서 1이 더해질 수 있도록
        int count = 1;
        for (int v = 0; v < graph[u].length; v++) {
            // 만약 방문한 노드가 아니거나 또는 이전에 방문한 인근 정점이라면
            if (!graph[u][v] || visited[v]) {
                continue;
            }
            visited[v] = true;
            count += countForward(v, visited);
        }

        return count;
    }

    int countBackward(int u, boolean[] visited) {
        // base case에서 1이 더해질 수 있도록
        int count = 1;
        for (int v = 0; v < graph[u].length; v++) {
            if (!graph[v][u] || visited[v]) {
                continue;
            }
            visited[v] = true;
            count += countBackward(v, visited);
        }
        return count;
    }

    void initGraph(int n, int[][] results) {
        graph = new boolean[n][n];
        for (int[] result : results) {
            // 인덱싱 주의
            int u = result[0] - 1;
            int v = result[1] - 1;
            graph[u][v] = true;
        }

        // System.out.println(Arrays.deepToString(graph));
    }
}
