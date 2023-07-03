package category.array;

import java.io.*;
import java.util.Arrays;

public class SE_NumberArrRotate {

    static void solution(int[][] arr) {
        int[][] once = rotate90deg(arr);
        int[][] twice = rotate90deg(once);
        int[][] third = rotate90deg(twice);

        print(once, twice, third);
    }

    static int[][] rotate90deg(int[][] a) {
        final int L = a.length;
        int[][] result = new int[L][L];

        for (int i = 0; i < L; i++) {
            int newCol = 0;
            for (int j = L - 1; j >= 0; j--) {
                result[i][newCol] = a[j][i];
                newCol++;
            }
        }

        return result;
    }

    static void print(int[][] a, int[][] b, int[][] c) {
        final int L = a.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < L; i++) {
            append(sb, a, i);
            sb.append(" ");
            append(sb, b, i);
            sb.append(" ");
            append(sb, c, i);
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void append(StringBuilder sb, int[][] a, int row) {
        for (int j = 0; j < a[row].length; j++) {
            sb.append(a[row][j]);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
            System.out.printf("#%d\n", t+1);
            solution(arr);
        }
    }
}
