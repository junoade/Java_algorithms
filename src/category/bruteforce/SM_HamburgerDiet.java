package category.bruteforce;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SM_HamburgerDiet {

    static int N;
    static int L;
    static int max;

    // 완전 탐색을 이용하여
    // 가능한 모든 조합에 대해 가장 높은 점수를 반환하도록
    static int solution(int[][] infos) {
        max = Integer.MIN_VALUE;
        rec(infos, 0, 0, 0, true);
        return max;
    }

    static void rec(int[][] infos, int startIdx, int prevS, int prevC, boolean promise) {
        int[] scores = infos[0];
        int[] calories = infos[1];

        // base case
        if (startIdx == N || !promise) {
            max = Math.max(max, prevS);
            return;
        }

        // 조합 구현
        for (int i = startIdx; i < N; i++) {
            prevC += calories[i];

            // 더 탐색해야하는 지 판별
            if (prevC <= L) {
                prevS += scores[i];
            } else {
                promise = false;
            }

            rec(infos, i + 1, prevS, prevC, promise); // 다음 탐색까진 가고 나서 return 되도록 // 다시 다음 순서 탐색해야함

            // 다음 탐색에 영향을 주지 않도록
            prevC -= calories[i];
            if (promise) {
                prevS -= scores[i];
            } else {
                promise = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("res/2307/0712-01-input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int[] inputs = processInput(br);
            N = inputs[0];
            L = inputs[1];

            // 칼로리, 점수 구분
            int[][] infos = new int[2][N];

            for (int i = 0; i < N; i++) {
                inputs = processInput(br);
                infos[0][i] = inputs[0];
                infos[1][i] = inputs[1];
            }

            System.out.printf("#%d %d\n", tc, solution(infos));
        }
    }

    static int[] processInput(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}