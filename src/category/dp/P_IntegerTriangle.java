package category.dp;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 정수 삼각형</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 반복되는 재귀함수 호출을 DP를 이용해서 줄일 수 있다.<br/>
 * 1차 구현으로 바텀업 기법을 이용했다.<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 62MB, 실행시간  20ms <br/>
 * --------------------------------------------------------------
 */
public class P_IntegerTriangle {
    static int[][] mem;

    public int solution(int[][] triangle) {
        final int N = triangle.length;
        mem = new int[N][];
        mem[0] = triangle[0];

        // 바텀업
        for (int i = 1; i < N; i++) {
            final int M = triangle[i].length;
            mem[i] = new int[M];
            for (int j = 0; j < M; j++) {
                final int value = triangle[i][j];
                // System.out.println("변경 전 : " + mem[i][j]);
                // 방향이 여러 개 인 경우
                if (j > 0 && j < M - 1) {
                    mem[i][j] = Math.max(mem[i - 1][j - 1] + value, mem[i - 1][j] + value);
                }
                // 방향이 한 군데 인 경우
                else if (j == 0) {
                    mem[i][j] = mem[i - 1][j] + value;
                } else {
                    mem[i][j] = mem[i - 1][j - 1] + value;
                }
                // System.out.println("변경 후 : " + mem[i][j]);
            }
        }

        // max 반환
        Arrays.sort(mem[N - 1]);
        int L = mem[N - 1].length;
        return mem[N - 1][L - 1];
    }

    public static void main(String[] args) {
        P_IntegerTriangle test = new P_IntegerTriangle();
        int[][] input01 = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(test.solution(input01));
    }
}
