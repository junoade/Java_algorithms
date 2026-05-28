package daily.y2026;

public class P_Dungeons {

    int global_max = 0;

    /**
     * @implNote <p>모든 던전 방문 순서를 DFS 백트랙킹으로 완전탐색한다.<br/>
     *              완탐 시간복잡도는 O(N!) 이나 던전수 N in [1, 8] 이므로 최악의 경우 8! 이지만 허용 범위.<br/>
     *              매 노드에서 depth를 답 후보로 갱신.
     *
     *          </p>
     * @param k
     * @param dungeons
     * @return
     */
    public int solution(int k, int[][] dungeons) {
        final int N = dungeons.length; // 던전 방 개수
        boolean[] v = new boolean[N];

        dfs(k, dungeons, v, 0);

        return global_max;
    }

    void dfs(int k, int[][] arr, boolean[] v, int depth) {
        // 현재 depth 와 global_max 비교
        global_max = Math.max(global_max, depth);

        // 다음 노드 탐색을 위한 상태 전이
        for(int i = 0; i < arr.length; i++) {
            int minK = arr[i][0], costK = arr[i][1];
            if(!v[i] && k >= minK) { // 진행가능한 상태 확인
                v[i] = true;
                dfs(k - costK, arr, v, depth + 1);
                v[i] = false; // 백트랙킹을 위해 상태복원
            }
        }
    }
}
