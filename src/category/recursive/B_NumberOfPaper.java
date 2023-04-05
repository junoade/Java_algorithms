package category.recursive;

import java.io.*;
import java.util.Arrays;

public class B_NumberOfPaper {
    static int[] answer; // {0:-1, 1:0, 2:1} 순서로 인덱싱

    static void solution(int[][] inputs) {
        final int L = inputs.length;
        // base case1: 현재 부분 2차원 배열의 모든 요소가 같은 값을 갖을 때
        if (isAllSameValue(inputs)) {
            int value = inputs[0][0] + 1; // {-1, 0 , 1} 을 인덱싱 하기 위해
            answer[value]++;
            return;
        }

        if(L == 3) {
            countEach(inputs);
            return;
        }

        // general case
        for (int i = 0; i < L; i += L / 3) {
            for (int j = 0; j < L; j += L / 3) {
                int[][] temp = new int[L / 3][L / 3];
                for (int newRow = 0; newRow < L / 3; newRow++) {
                    System.arraycopy(inputs[i + newRow], j, temp[newRow], 0, L/3);
                }
                solution(temp);
            }
        }
    }

    static boolean isAllSameValue(int[][] inputs) {
        int value = inputs[0][0];
        for (int[] row : inputs) {
            for (int colElement : row) {
                if (colElement != value) {
                    return false;
                }
            }
        }
        return true;
    }

    static void countEach(int[][] inputs) {
        for(int[] row : inputs) {
            for(int colElement : row) {
                answer[colElement + 1]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] inputs = new int[N][N];
        for (int[] row : inputs) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(temp, 0, row, 0, N);
        }
        answer = new int[3];
        solution(inputs);
        for(int value : answer) {
            System.out.println(value);
        }
    }
}