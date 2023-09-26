package daily.a0916;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14676_영우는사기꾼 {

    static int N, M, K; // in [1, 10만]
    static List<Integer>[] graph;

    static final int CONSTRUCTION = 1;
    static final int DESTRUCTION = 2;

    // 진입차수의 수를 기록 하는 배열
    static int[] inDegrees;

    // 각 건물 번호로 생성된 건물들의 갯수를 기록
    static int[] builds;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1. 일단 인접 리스트 구성
        graph = new List[N + 1]; // 1's based indexing
        // 가짜 정점이 들어올 수도 있는데 그러면 NullPointerException 발생 가능
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        inDegrees = new int[N + 1];
        builds = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y); // 방향성 그래프
            inDegrees[y]++; // y로 진입하는 차수에 대해 증가시켜줌

        }

        // 2. 게임 정보 입력 처리
        boolean isLier = false;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int key = Integer.parseInt(st.nextToken());


            if (cmd == CONSTRUCTION) {
                // 해당 진입 차수 배열의 값이 0일 때만 건설이 가능
                if (inDegrees[key] == 0) {

                    builds[key]++;
                    // 첫 건설 여부 확인
                    if (builds[key] == 1) {
                        // 인접 정점들도 건설이 가능하도록
                        // 진입차수를 1 줄여줌
                        for (int adj : graph[key]) { // null 주의해야
                            if (inDegrees[adj] > 0) {
                                inDegrees[adj]--;
                            }
                        }
                    }

                } else {
                    // 건설 불가능한데 건설하려고 함!
                    isLier = true;
                    break;
                }
            } else if (cmd == DESTRUCTION) {
                // 건설한 적 없는 건물 파괴?
                if (builds[key] == 0) {
                    isLier = true;
                    break;
                }

                builds[key]--;
                // 건물이 모두 파괴되었다면
                // 인접한 정점들의 진입 차수를 하나 늘려줌
                if (builds[key] == 0) {
                    for (int adj : graph[key]) {
                        inDegrees[adj]++;
                    }
                }

            }
        }

        if (isLier) {
            System.out.println("Lier!");
        } else {
            System.out.println("King-God-Emperor");
        }
        br.close();
    }
}
