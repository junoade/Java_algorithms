package category.dp;

/**
 * --------------------------------------------------------------<br/>
 * <b> </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 단순히 최단 경로를 찾는 문제가 아니라; <br/>
 * 그 가짓수를 찾는 문제라 한 정점이 갖을 수 있는 가짓수는 2로
 * 이 때 시간복잡도는 O(2^{MN}) 을 가지게 됨
 * 탐색 공간 상 반복 방문하게 되는 경우를 줄여서(DP) 효율적으로 수행하도록;
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  52.3MB, 실행시간  1.17ms<br/>
 * --------------------------------------------------------------
 */
public class P_RoadToSchool {
    static int M;
    static int N;

    // -1 : puddle
    // 0 : possible
    // other : short distance
    int[][] mem;
    final int DIV = 1_000_000_007;

    public int solution(int m, int n, int[][] puddles) {
        M = m;
        N = n;
        mem = new int[M + 1][N + 1];

        // 물웅덩이 체크
        for (int[] puddle : puddles) {
            int x = puddle[0], y = puddle[1];
            mem[x][y] = -1;
        }

        // 재귀함수 호출
        // 최적화 : 메모제이션DP를 이용
        return rec(1, 1);
    }

    // row, col
    int rec(int x, int y) {

        // base case : 유효한 좌표범위인지 확인
        if (isOutbound(x, y) || mem[x][y] == -1) {
            return 0;
        }

        // base case : 이미 방문한 경로인지 부터 확인
        if (mem[x][y] > 0) {
            return mem[x][y];
        }

        // base case : 종료 조건
        if (x == M && y == N) {
            return 1; // 경로가 존재한다는 의미; depth 따로 필요 없을 듯
        }

        mem[x][y] = (rec(x + 1, y) + rec(x, y + 1)) % DIV;
        return mem[x][y];

    }

    // 유효 범위 [1, M], [1, N];
    boolean isOutbound(int x, int y) {
        return (x < 1 || x > M) || (y < 1 || y > N);
    }

    public static void main(String[] args) {
        P_RoadToSchool test = new P_RoadToSchool();
        int[][] pud01 = {{2, 2}};
        System.out.println(test.solution(4, 3, pud01));
    }
}
