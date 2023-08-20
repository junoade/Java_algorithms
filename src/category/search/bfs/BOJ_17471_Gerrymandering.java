package category.search.bfs;

import java.io.*;
import java.util.*;

public class BOJ_17471_Gerrymandering {
    static int N;
    // static List<Integer>[] graph;
    static int[][] graph;

    static int[] arr; // 인구수 기록용
    static int MIN;

    // 1~N까지 두 개의 선거구로 나눈다.
    // 한 정점 번호에 대해 A선거구로 포함되는 경우와 B선거구로 포함되는 경우를 따져준다. (부분집합)
    // 나눠준 선거구에 대해 BFS 탐색으로 전부 연결되어있는지 판단한다.
    // 전부 연결되어있다면 지역구 A, 지역구 B의 인구차를 반환한다.
    static void solution() {
        boolean[] visited = new boolean[N + 1];
        MIN = Integer.MAX_VALUE;

        subs(1, visited);

        if (MIN == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(MIN);
        }
    }

    static void subs(int cnt, boolean[] visited) {
        // 다 선택한 경우
        if (cnt == N + 1) {
            List<Integer> aList = getList(visited, true);
            List<Integer> bList = getList(visited, false);
            if (bfs(aList) && bfs(bList)) {
                int sumA = getSum(aList);
                int sumB = getSum(bList);
                MIN = Math.min(MIN, Math.abs(sumA - sumB));
            }
            return;
        }

        // true -> A지역구
        visited[cnt] = true;
        subs(cnt + 1, visited);

        // false -> B지역구
        visited[cnt] = false;
        subs(cnt + 1, visited);
    }

    static List<Integer> getList(boolean[] visited, boolean flag) {
        List<Integer> list = new ArrayList<>(N);
        for (int i = 1; i <= N; i++) {
            if (visited[i] == flag) {
                list.add(i);
            }
        }
        return list;
    }

    // list의 첫번째 요소부터 BFS탐색을 수행하고 났을 때
    // visited배열의 모든 요소를 방문했는지 여부를 판단한다
    static boolean bfs(List<Integer> list) {

        if (list.isEmpty()) {
            return false;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        int vertex = list.get(0);
        q.offer(vertex);
        visited[vertex] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int cursor = q.poll();

            // 주의!
            // 간선은 다 연결되어있더라도 지역구는 다를 수 있음
            // 처음 시도에서는 그냥 연결되어있는지만 따졌다
            // if (!visited[i] && graph[cursor][i] == 1) {
            for(int i = 1; i <= N; i++) {
                boolean isAdj = graph[cursor][i] == 1 || graph[i][cursor] == 1;
                if(!visited[i] && isAdj && list.contains(i)) {
                    visited[i] = true;
                    q.offer(i);
                    count++;
                }
            }
        }
        // 처음 생각
        /*for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                return false;
            }
        }
           return true;
        */
        // RIGHT ANSWER : 방문 가능한 지점인가?
        /*for(int idx : list) {
            if(!visited[idx]) {
                return false;
            }
        }
        return true;*/

        // RIGHT ANSWER : 지역구 수와 같은가?
        return count == list.size();

    }

    static int getSum(List<Integer> list) {
        int sum = 0;
        for (int idx : list) {
            sum += arr[idx];
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        // 인구수 정보 기록
        arr = new int[N + 1];
        // graph = new List[N];
        graph = new int[N + 1][N + 1]; // 1's based

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // i번째 정점과 인접 노드들을 이어준다.
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= M; j++) { // 1's based
                int adj = Integer.parseInt(st.nextToken());
                graph[i][adj] = 1;
                graph[adj][i] = 1;
            }
        }

        solution();
        br.close();
    }
}
