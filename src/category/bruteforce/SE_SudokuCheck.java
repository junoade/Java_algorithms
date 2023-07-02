package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

public class SE_SudokuCheck {
    static final int N = 9;

    static int solution(int[][] sudoku) {
        // 3x3 에 대해 모두 검증
        // 각 행에 대해 모두 검증
        // 각 열에 대해 모두 검증
        return hasValidSquare(sudoku) && hasValidRow(sudoku) && hasValidCol(sudoku) ? 1 : 0;
    }

    static boolean hasValidSquare(int[][] sudoku) {
        for (int i = 0; i < N; i += 3) {
            for (int j = 0; j < N; j += 3) {
                if (!iter(sudoku, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean hasValidRow(int[][] sudoku) {
        for (int r = 0; r < N; r++) {
            HashSet<Integer> set = new HashSet<>();
            for (int c = 0; c < N; c++) {
                int value = sudoku[r][c];
                if (set.contains(value)) {
                    return false;
                } else {
                    set.add(value);
                }
            }
        }
        return true;
    }

    static boolean hasValidCol(int[][] sudoku) {
        for (int c = 0; c < N; c++) {
            HashSet<Integer> set = new HashSet<>();
            for (int r = 0; r < N; r++) {
                int value = sudoku[r][c];
                if (set.contains(value)) {
                    return false;
                } else {
                    set.add(value);
                }
            }
        }
        return true;
    }

    static boolean iter(int[][] sudoku, int i, int j) {
        HashSet<Integer> set = new HashSet<>();
        for (int r = i; r < i + 3; r++) {
            for (int c = j; c < j + 3; c++) {
                int value = sudoku[r][c];
                if (set.contains(value)) {
                    return false;
                } else{
                    set.add(value);
                }
            }
        }
        return true;
    }




    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int[][] sudoku = new int[N][N];
            for (int i = 0; i < N; i++) {
                sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            System.out.printf("#%d %d\n", t + 1, solution(sudoku));
        }
    }

}
