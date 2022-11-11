package category.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NQueen_9663 {

    static int[][] arr;
    static int result, N;
    static boolean possible = false;

    static void rec_func(int Q) {
        // 마지막 Q까지 놓은 경우
        if (Q == N + 1) {
            if (possible) result++; // 증가시켜준다
        } else { // 퀸을 배치한다.
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if (arr[i][j] == 0) {
                        int[][] beforeRow = getRowInfo(j);
                        int[] beforeCol = getColInfo(i);
                        int[][] beforeDiag = getDiagonalInfo(i, j);
                        arr[i][j] = 1;
                        setValueOnRow(i, 1);
                        setValueOnCol(j, 1);
                        setValueOnDiagonal(i, j, 1);
                        possible = true;

                        rec_func(Q + 1);

                        updateToBeforeRow(beforeRow, j);
                        updateToBeforeCol(beforeCol, i);
                        updateToBeforeDiag(beforeDiag, i, j);
                        arr[i][j] = 0;
                        possible = false;
                    }
                }
            }
        }

    }

    static void updateToBeforeRow(int[][] beforeRow, int col) {
        for (int i = 0; i < N; i++) {
            arr[i][col] = beforeRow[i][col];
        }
    }

    static void updateToBeforeCol(int[] beforeCol, int row) {
        for (int i = 0; i < N; i++) {
            arr[row][i] = beforeCol[i];
        }
    }


    static void updateToBeforeDiag(int[][] beforeDiag, int row, int col) {
        // 대각선 방향 지정
        int[] posRow = {-1, -1, 1, 1};
        int[] posCol = {-1, 1, -1, 1};
        for (int i = 0; i < 4; i++) {
            int curRow = row + posRow[i];
            int curCol = col + posCol[i];
            while (isInsideRange(curRow, curCol)) {
                arr[curRow][curCol] = beforeDiag[curRow][curCol];
                curRow += posRow[i];
                curCol += posCol[i];
            }
        }
    }


    static int[][] getRowInfo(int col) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i][col] = arr[i][col];
        }
        return result;
    }

    static int[] getColInfo(int row) {
        int[] result = new int[N];
        for (int i = 0; i < N; i++) {
            result[i] = arr[row][i];
        }
        return result;
    }

    static int[][] getDiagonalInfo(int row, int col) {
        int[][] result = new int[N][N];
        // 대각선 방향 지정
        int[] posRow = {-1, -1, 1, 1};
        int[] posCol = {-1, 1, -1, 1};
        for (int i = 0; i < 4; i++) {
            int curRow = row + posRow[i];
            int curCol = col + posCol[i];
            while (isInsideRange(curRow, curCol)) {
                result[curRow][curCol] = arr[curRow][curCol];
                curRow += posRow[i];
                curCol += posCol[i];
            }
        }
        return result;
    }

    static void setValueOnRow(int row, int value) {
        for (int col = 0; col < arr[row].length; col++) {
            arr[row][col] = value;
        }
    }

    static void setValueOnCol(int col, int value) {
        for (int row = 0; row < arr.length; row++) {
            arr[row][col] = value;
        }
    }

    static void setValueOnDiagonal(int row, int col, int value) {
        // 대각선 방향 지정
        int[] posRow = {-1, -1, 1, 1};
        int[] posCol = {-1, 1, -1, 1};
        for (int i = 0; i < 4; i++) {
            int curRow = row + posRow[i];
            int curCol = col + posCol[i];
            while (isInsideRange(curRow, curCol)) {
                arr[curRow][curCol] = value;
                curRow += posRow[i];
                curCol += posCol[i];
            }
        }
    }

    static boolean isInsideRange(int x, int y) {
        boolean isXInside = x >= 0 && x < N;
        boolean isYInside = y >= 0 && y < N;
        return isXInside && isYInside;
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        result = 0;
    }

    public static void main(String[] args) throws IOException {
        input();
        rec_func(1);
        System.out.println(result);
    }
}
