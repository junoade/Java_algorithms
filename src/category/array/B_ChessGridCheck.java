package category.array;

import java.io.*;
import java.util.Arrays;

public class B_ChessGridCheck {

    static int N, M;
    // 상 - 하 - 좌 - 우
    // 로 풀까하다가 단순 반전으로 풀이
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static final int L = 8;

    static void solution(String[][] chess) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N - L + 1; i++) {
            for (int j = 0; j < M - L + 1; j++) {
                // String[][] state = deepcopy(chess);
                min = Math.min(min, count(chess, i, j));
            }
        }

        System.out.println(min);
    }

    static int count(String[][] state, int i, int j) {
        final int startRow = i, startCol = j;
        int countB = 0, countW = 0;


        for (int k = 0; k < 2; k++) {
            String key = k == 0 ? "B" : "W"; // 첫 탐색키
            for (int x = startRow; x < startRow + L; x++) {
                for (int y = startCol; y < startCol + L; y++) {
                    String current = state[x][y];

                    if (!key.equals(current)) {
                        if (k == 0) {
                            countB++;
                        } else {
                            countW++;
                        }
                    }

                    // 탐색키를 반전 시켜줌
                    if (key.equals("B")) {
                        key = "W";
                    } else {
                        key = "B";
                    }
                }

                // BWBWBWBW
                // WBWBWBWB 가 될 수 있게 한번 더 반전 시켜줌
                key = key.equals("B") ? "W" : "B";
            }
        }

        return Math.min(countB, countW);
    }

    static String[][] deepcopy(String[][] chess) {
        String[][] state = new String[N][M];
        for (int i = 0; i < N; i++) {
            state[i] = Arrays.copyOf(chess[i], M);
        }
        return state;
    }

    static boolean isOutbound(int x, int y, int startRow, int startCol) {
        return (x < startRow || x >= startRow + L) || (y < startCol || y >= startCol + L);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        N = Integer.parseInt(inputs[0]);
        M = Integer.parseInt(inputs[1]);

        String[][] chess = new String[N][M];
        for (int i = 0; i < N; i++) {
            chess[i] = br.readLine().split("");
        }
        solution(chess);

    }
}
