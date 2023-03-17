package category.array;

import java.util.ArrayList;
import java.util.Arrays;

public class P_TriangleSnail_Failed {
    static int value;
    static int baseCol;

    public int[] solution(int n) {
        int[][] arr = new int[n][n];
        // down, right, cross
        int[] dx = {0, 1, -1};
        int[] dy = {1, 0, -1};

        // col = 0일 때 row 부터 갱신(down)
        value = 1;
        baseCol = 0;
        arr[0][0] = 1;

        while(baseCol < n ) {
            // go down 반복
            updateDown(arr, n);

            // 열 증가
            baseCol++;
            // go right 반복
            updateRight(arr, n);
            // go north-westard 반복
            updateNW(arr, n);
        }

        return convertTo1D(arr);
    }

    void updateDown(int[][] arr, int n) {
        int newCol = baseCol;
        for(int i = newCol + 1; i < n - baseCol; i++) {
            if(isCurrentZero(arr[i][newCol])) {
                arr[i][newCol] = value++; // value는 1부터 시작
            }
        }
    }

    void updateRight(int[][] arr, int n) {
        int newRow = n - baseCol; // 최 updateDown() 이후 baseCol 이 1로 증가하는 것 주의
        for(int i = baseCol; i <= newRow; i++) {
            if(isCurrentZero(arr[newRow][i])) {
                arr[newRow][i] = value++;
            }
        }
    }

    void updateNW(int[][] arr, int n) {
        int newCross = n - baseCol - 1;
        for(int i = newCross; i >= 1; i--) {
            if(isCurrentZero(arr[i][i])) {
                arr[i][i] = value++;
            }
        }
    }

    boolean isCurrentZero(int value) {
        return value == 0;
    }

    int[] convertTo1D(int[][] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int[] ints : arr) {
            for (int value : ints) {
                if (value != 0) {
                    result.add(value);
                }
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        P_TriangleSnail_Failed p_triangleSnail = new P_TriangleSnail_Failed();
        System.out.println(Arrays.toString(p_triangleSnail.solution(10)));
    }
}
