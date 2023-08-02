package category.simulation;

import java.io.*;
import java.util.Arrays;

/**
 * SWEA D3 2805 농작물 수확하기
 */
public class SM_2805_FarmCrop {

    static int solution(int[][] arr) {
        int L = arr.length;
        int half = L/2;
        int sum = 0;
        for(int i = 0; i <= half; i++) {
            int col = half - i;
            for(int j = 0; j < L - 2 * col; j++) {
                sum += arr[i][j + col];
            }

        }

        for(int i = half + 1; i < L; i++) {
            int col = i - half;
            for(int j = 0; j < L - 2 * col; j++) {
                sum += arr[i][j + col];
            }
        }

        return sum;
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/category/simulation/input/B_2805_input01.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for(int i = 0; i < N; i++) {
                // 빈 문자열에 대한 String.spilt() vs StringTokenizer의 구분자 차이
                arr[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
            sb.append("#").append(tc).append(" ").append(solution(arr)).append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
