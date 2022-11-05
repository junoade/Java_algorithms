package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * --------------------------------------------------------------<br/>
 * <b> N-Queen 문제 백준 9663 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 백트랙킹 알고리즘 전략을 사용해서
 * 1. Queen을 놓을 수 있는 자리인지 판별(Promising)
 * 2. 중복되는 탐색 경우를 Pruning 한다.
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 처음엔 퀸을 놓고 행, 열, 대각선을 1 로 만들고 다음 depth로 탐색하려 했다.
 * 2. 그러나 이 경우 Q-1 단계에서 세팅한 Queen의 공격 범위를
 *    Q 단계 수행 후, Q+1 범위 수행한 이후 Q로 돌아올 때 0으로 초기화 하는 문제가 있다.
 * 3. 또한 isRowPossible, isColPossible, isDiagonalPossible은 해당 부분에 1이 하나라도 있다면( 이전 퀸의 공격 범위라면 )
 *    false를 반환하게 작성하였는데 주변 공간이 다른 퀸들의 공격 범위더라도 arr[i][j]가 0이면 놓을 수 있는데 놓지 못하게 된다는 문제가 있다.
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class NQueen_9663_Try {

    static int N;
    static int[][] arr;
    static int result;

    static void rec_func(int Q) {
        /* base case 1 : Q의 위치를 도저히 놓을 수 없는 경우 */
        /* base case 2 : Q의 위치를 다 배정한 경우 */
        if (Q == N) {
            result++;
        } else {
            // Q를 임의의 장소에 배정한다.
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    if(isRowPossible(i) && isColPossible(j) && isDiagonalPossible(i, j)) {
                        setValueOnRow(i, 1);
                        setValueOnCol(j, 1);
                        setValueOnDiagonal(i, j, 1);
                        rec_func(Q + 1);
                        setValueOnRow(i, 0);
                        setValueOnCol(j, 0);
                        setValueOnDiagonal(i, j, 0);
                    }
                }
            }
        }
    }

    static boolean isRowPossible(int row) {
        for (int col = 0; col < arr[row].length; col++) {
            int value = arr[row][col];
            if (value == 1) return false;
        }
        return true;
    }

    static boolean isColPossible(int col) {
        for (int row = 0; row < arr.length; row++) {
            int value = arr[row][col];
            if (value == 1) return false;
        }
        return true;
    }

    static boolean isDiagonalPossible(int row, int col) {
        // 대각선 방향 지정
        int[] posRow = {-1, -1, 1, 1};
        int[] posCol = {-1, 1, -1, 1};
        for(int i = 0; i < 4; i++){
            int curRow = row + posRow[i];
            int curCol = col + posCol[i];
            while(isInsideRange(curRow, curCol)){
                int value = arr[curRow][curCol];
                if(value == 1) return false;
                curRow += posRow[i];
                curCol += posCol[i];
            }
        }
        return true;
    }

    static void setValueOnRow(int row, int value){
        for (int col = 0; col < arr[row].length; col++) {
            arr[row][col] = value;
        }
    }

    static void setValueOnCol(int col, int value){
        for (int row = 0; row < arr.length; row++) {
            arr[row][col] = value;
        }
    }

    static void setValueOnDiagonal(int row, int col, int value){
        // 대각선 방향 지정
        int[] posRow = {-1, -1, 1, 1};
        int[] posCol = {-1, 1, -1, 1};
        for(int i = 0; i < 4; i++){
            int curRow = row + posRow[i];
            int curCol = col + posCol[i];
            while(isInsideRange(curRow, curCol)){
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
