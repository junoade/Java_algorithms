package daily.a1214;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_bj_12893_적의적 {
    static int N, M;
    static final int L = 2_000;

    static List<Integer>[] graphs;
    static int[] v;
    static final int NOT_VISITED = 0;
    static final int FRIEND = 1;
    static final int ENEMY = -1;


    static int solution() {
        for (int i = 1; i <= L; i++) {
            if (v[i] != NOT_VISITED) {
                continue;
            }

            if (!bfs(i)) {
                return 0;
            }
        }
        return 1;
    }

    static boolean bfs(int start) {
        ArrayDeque<Integer> dq = new ArrayDeque<>();
        dq.offer(start);
        v[start] = FRIEND;

        while (!dq.isEmpty()) {
            int key = dq.poll();

            for (Integer adjKey : graphs[key]) {
                if(v[adjKey] == NOT_VISITED) {
                    v[adjKey] = v[key] * ENEMY; // -1(Enemy) * -1(Enemy) => 1(Friend)
                    dq.offer(adjKey);
                } else if (v[adjKey] == v[key]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graphs = new List[L + 1];
        v = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            graphs[i] = new ArrayList<>();
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graphs[x].add(y);
            graphs[y].add(x);
        }

        System.out.println(solution());
    }
}
