package category.recursive;

import java.util.Arrays;

public class P_QuadCompression {
    static int countOne = 0;
    static int countZero = 0;

    public int[] solution(int[][] arr) {
        recursive(arr);
        return new int[]{countZero, countOne};
    }

    void recursive(int[][] arr) {
        int L = arr.length;

        /* base case : 모두 같은 값이라 분할할 필요가 없을 때 */
        if (isAllMatch(arr)) {
            countAsSame(arr);
            return;
        }

        /* base case : 더 이상 4분할 할 수 없을 때 */
        if (L == 2) {
            countEach(arr);
            return;
        }
        /* general case */
        int[][] firstS = getS(arr, 1);
        recursive(firstS);

        int[][] secondS = getS(arr, 2);
        recursive(secondS);

        int[][] thirdS = getS(arr, 3);
        recursive(thirdS);

        int[][] fourthS = getS(arr, 4);
        recursive(fourthS);
    }

    int[][] getS(int[][] arr, int quarter) {
        int L = arr.length;
        int[][] newArr = new int[L / 2][L / 2];
        int rowIdx = 0, colIdx = 0;

        if (quarter == 1) {
            rowIdx = 0;
            colIdx = 0;
        } else if (quarter == 2) {
            rowIdx = 0;
            colIdx = L / 2;
        } else if (quarter == 3) {
            rowIdx = L / 2;
            colIdx = 0;
        } else if (quarter == 4) {
            rowIdx = L / 2;
            colIdx = L / 2;
        }

        for (int i = 0; i < L / 2; i++) {
            System.arraycopy(arr[rowIdx + i], colIdx, newArr[i], 0, L / 2);
        }
        return newArr;
    }

    /* params: 2*2 배열 */
    boolean isAllMatch(int[][] arr) {
        int firstValue = arr[0][0];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (firstValue != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    void countAsSame(int[][] arr) {
        if (arr[0][0] == 1) {
            countOne++;
        } else {
            countZero++;
        }
    }

    void countEach(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    countOne++;
                } else {
                    countZero++;
                }
            }
        }
    }

    public static void main(String[] args) {
        P_QuadCompression p_quadCompression = new P_QuadCompression();
        int[][] test01 = {{1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 1, 1, 1}};
        System.out.println(Arrays.toString(p_quadCompression.solution(test01)));
    }
}
