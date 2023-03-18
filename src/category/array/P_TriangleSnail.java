package category.array;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 LV2 삼각 달팽이 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 2차원 배열 생성 후 주어진 방향의 값들을 갱신하는 방식으로 구현
 * <br/> 다른 방향으로 가기전에 다음 좌표의 유효성 검사 및 이미 할당된 자리인지 검사 후 좌표 변경 작업 수행
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 10 / 10
 * --------------------------------------------------------------
 */
public class P_TriangleSnail {
    static int[][] arr;
    static int x; // row
    static int y; // col

    static int value;

    /* 이렇게 접근할까 했으나 활용도가 낮아 주석처리 */
    // down, right, cross
    // static int[] dx = {1, 0, -1};
    // static int[] dy = {0, 1, -1};

    public int[] solution(int n) {
        arr = new int[n][n];

        // col = 0일 때 row 부터 갱신(down)
        value = 1;
        x = 0;
        y = 0;
        int count = 0;
        while (count < n) {
            // go down 반복
            updateDown(arr, n);

            // go right 반복; 그 전에 col 값을 +1 만큼 증가시켜준다. 그 전에 범위 확인 필수
            if (!validateNextPos(y+1, n) || !isNextValueZero(x, y + 1)) {
                break;
            }
            y += 1;
            updateRight(arr, n);

            // go north-westard 반복; 그 전에 위치 변경
            if (!isNextValueZero(x - 1, y - 1)) {
                break;
            }
            x -= 1;
            y -= 1;
            updateNW(arr, n);

            // go down 전 위치 변경
            if (!validateNextPos(x+1, n) || !isNextValueZero(x + 1, y)) {
                break;
            }
            x += 1;
            count++;
        }

        return convertTo1D();
    }

    void updateDown(int[][] arr, int n) {
        while (true) {
            arr[x][y] = value++;
            if (!validateNextPos(x+1, n) || !isNextValueZero(x + 1, y)) {
                break;
            }
            x += 1;
        }

    }

    void updateRight(int[][] arr, int n) {
        while (true) {
            arr[x][y] = value++;
            if (!validateNextPos(y + 1, n) || !isNextValueZero(x, y + 1)) {
                break;
            }
            y += 1;
        }
    }

    void updateNW(int[][] arr, int n) {
        while (true) {
            arr[x][y] = value++;
            if (!isNextValueZero(x - 1, y - 1)) {
                break;
            }
            x += -1;
            y += -1;
        }
    }

    int[] convertTo1D() {
        int[] result = new int[value - 1];
        int idx = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                result[idx++] = arr[i][j];
            }
        }
        return result;
    }

    boolean validateNextPos(int nextPos, int n) {
        return nextPos < n;
    }

    // 먼저 유효한 index인지 검증할 것
    boolean isNextValueZero(int row, int col) {
        return arr[row][col] == 0;
    }

    public static void main(String[] args) {
        P_TriangleSnail p_triangleSnail = new P_TriangleSnail();
        System.out.println(Arrays.toString(p_triangleSnail.solution(10)));
    }
}
