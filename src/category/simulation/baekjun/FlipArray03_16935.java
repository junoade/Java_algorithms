package category.simulation.baekjun;

import java.io.*;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------
 * <title> 백준 16935, 배열 돌리기3 </title>
 * 유형 - 시뮬레이션
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * - R_OP의 각 element, 즉 단일 명령을 수행하는 메소드를 구현
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - swap, subset, System.arraycopy, = 연산자를 통한 shallow copy 이용
 * --------------------------------------------------------------
 * <b> 채점 </b>
 * <p> 메모리 67428KB / 512MB , 시간 784ms / 2000ms
 * --------------------------------------------------------------
 * <b> 생각한 내용 </b>
 * <p>
 */
public class FlipArray03_16935 {

    /* 각 element의 최대 크기가 10^8개라 int형 배열로 선언 */
    static int N, M, R;
    static int[] R_OP;

    public static void solution(int[][] arr) {

        for (int i = 0; i < R; i++) {
            switch (R_OP[i]) {
                case 1:
                    firstOperation(arr);
                    break;
                case 2:
                    secondOperation(arr);
                    break;
                case 3:
                    arr = thirdOperation(arr);
                    break;
                case 4:
                    arr = fourthOperation(arr);
                    break;
                case 5:
                    fifthOperation(arr);
                    break;
                case 6:
                    sixOperation(arr);
                    break;
            }
        }
        printArr(arr);
    }

    /* 상하 반전 연산 수행 */
    public static void firstOperation(int[][] arr) {
        int N = arr.length;
        for (int i = 0; i < N / 2; i++) {
            int[] temp;
            /* 최상위는 최하위로 */
            temp = arr[i];
            arr[i] = arr[N - i - 1];
            arr[N - i - 1] = temp;
        }
    }

    /* 좌우 반전 연산 수행 */
    public static void secondOperation(int[][] arr) {
        int N = arr.length;
        int M = arr[0].length;

        for (int i = 0; i < M / 2; i++) {
            for (int j = 0; j < N; j++) {
                int temp = arr[j][i];
                arr[j][i] = arr[j][M - i - 1];
                arr[j][M - i - 1] = temp;
            }
        }
    }

    /* 오른쪽으로 90도 회전 연산 수행 */
    public static int[][] thirdOperation(int[][] arr) {
        int N = arr.length;
        int M = arr[0].length;
        int[][] rotatedArr = new int[M][N];

        for (int j = N - 1, row = 0; j >= 0; j--, row++) {
            int[] tempSingleArr = new int[M];
            System.arraycopy(arr[row], 0, tempSingleArr, 0, M);

            for (int h = 0; h < M; h++) {
                rotatedArr[h][j] = tempSingleArr[h];
            }
        }

        return rotatedArr;
    }

    /* 왼쪽으로 90도 회전 연산 수행*/
    public static int[][] fourthOperation(int[][] arr) {
        int N = arr.length;
        int M = arr[0].length;
        int[][] rotatedArr = new int[M][N];

        for (int j = 0, row = 0; j < N; j++, row++) {
            int[] tempSingleArr = new int[M];
            System.arraycopy(arr[row], 0, tempSingleArr, 0, M);

            for (int h = 0; h < M; h++) {
                rotatedArr[M - h - 1][j] = tempSingleArr[h];
            }
        }

        return rotatedArr;
    }

    /* 오른쪽으로 90도 회전 연산 응용*/
    public static void fifthOperation(int[][] arr) {
        int N = arr.length;
        int M = arr[0].length;
        int[][] firstSubset = new int[N / 2][M / 2];
        int[][] secondSubset = new int[N / 2][M / 2];
        int[][] thirdSubset = new int[N / 2][M / 2];
        int[][] fourthSubset = new int[N / 2][M / 2];

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i], 0, firstSubset[i], 0, M / 2);
            System.arraycopy(arr[i], M / 2, secondSubset[i], 0, M / 2);
        }
        for (int i = N / 2; i < N; i++) {
            System.arraycopy(arr[i], 0, fourthSubset[i - N / 2], 0, M / 2);
            System.arraycopy(arr[i], M / 2, thirdSubset[i - N / 2], 0, M / 2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < N / 2 && j < M / 2) { // 1번 그룹
                    arr[i][j] = fourthSubset[i][j];
                } else if (i < N / 2 && j >= M / 2) { // 2번 그룹
                    arr[i][j] = firstSubset[i][j - M / 2];
                } else if (i >= N / 2 && j < M / 2) { // 4번 그룹
                    arr[i][j] = thirdSubset[i - N / 2][j];
                } else { // 3번 그룹
                    arr[i][j] = secondSubset[i - N / 2][j - M / 2];
                }
            }
        }
    }

    /* 왼쪽으로 90도 응용*/
    public static void sixOperation(int[][] arr) {
        int N = arr.length;
        int M = arr[0].length;
        int[][] firstSubset = new int[N / 2][M / 2];
        int[][] secondSubset = new int[N / 2][M / 2];
        int[][] thirdSubset = new int[N / 2][M / 2];
        int[][] fourthSubset = new int[N / 2][M / 2];

        for (int i = 0; i < N / 2; i++) {
            System.arraycopy(arr[i], 0, firstSubset[i], 0, M / 2);
            System.arraycopy(arr[i], M / 2, secondSubset[i], 0, M / 2);
        }
        for (int i = N / 2; i < N; i++) {
            System.arraycopy(arr[i], 0, fourthSubset[i - N / 2], 0, M / 2);
            System.arraycopy(arr[i], M / 2, thirdSubset[i - N / 2], 0, M / 2);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (i < N / 2 && j < M / 2) { // 1번 그룹
                    arr[i][j] = secondSubset[i][j];
                } else if (i < N / 2 && j >= M / 2) { // 2번 그룹
                    arr[i][j] = thirdSubset[i][j - M / 2];
                } else if (i >= N / 2 && j < M / 2) { // 4번 그룹
                    arr[i][j] = firstSubset[i - N / 2][j];
                } else { // 3번 그룹
                    arr[i][j] = fourthSubset[i - N / 2][j - M / 2];
                }
            }
        }
    }

    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        /* 입력 전처리 */
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine()); // 행 데이터 입력
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /* R_Operation 입력 전처리 */
        R_OP = new int[R];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            R_OP[i] = Integer.parseInt(st.nextToken());
        }
        solution(arr);
    }

}
