package category.array;

import java.util.Arrays;

public class P_TriangleSnail_Better {
    // d - r - nw 방향 순
    static final int[] dx = {1, 0, -1}; // row
    static final int[] dy = {0, 1, -1}; // col
    static int[][] arr;
    static int value;

    public int[] solution(int n) {
        arr = new int[n][n];
        value = 1;

        int x = 0, y = 0, d = 0;
        while(true) {
            // 초기 방향 d = 0 부터
            arr[x][y] = value++;
            int nX = x + dx[d];
            int nY = y + dy[d];

            // 방향 d를 반복 수행 후 다음 좌표가 현재 방향에 유효하지 않을 때,
            if(!validateNextPos(nX, nY, n) || !isNextZero(nX, nY)) {
                d = (d + 1) % 3;
                nX = x + dx[d];
                nY = y + dy[d];

                // 새로운 방향 수행 후에도 좌표가 유효하지 않다면...
                if(!validateNextPos(nX, nY, n) || !isNextZero(nX, nY)) {
                    break;
                }
            }

            // 새로운 방향으로 좌표 갱신할 수 있도록
            x = nX;
            y = nY;
        }

        return convertTo1DArray(arr);

    }

    boolean validateNextPos(int x, int y, int n) {
        boolean startCond = x >= 0 && y >= 0;
        boolean toCond = x < n && y < n;
        return startCond && toCond;
    }

    boolean isNextZero(int x, int y) {
        return arr[x][y] == 0;
    }

    int[] convertTo1DArray(int[][] arr) {
        int[] result = new int[value - 1];
        int idx = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j <= i; j++) {
                result[idx++] = arr[i][j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        P_TriangleSnail_Better triangleSnail = new P_TriangleSnail_Better();
        System.out.println(Arrays.toString(triangleSnail.solution(10)));
    }
}
