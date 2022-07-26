package samsung.swtest_july;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GlobalWarming {
    static Scanner sc;

    public static void main(String[] args) throws Exception {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        System.setIn(new FileInputStream("src/samsung/swtest_july/input01.txt"));

        /* 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다. */
        int T;
        sc = new Scanner(System.in);
        T = sc.nextInt();
        /* 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다. */
        for (int test_case = 1; test_case <= T; test_case++) {

            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
				 이 부분에 여러분의 알고리즘 구현이 들어갑니다.
			 */
            System.out.printf("#%d %d\n", test_case, solution());
            /////////////////////////////////////////////////////////////////////////////////////////////

        }
    }

    /* 물이 얼만큼 잠겨야하는지 입력으로 부터 추정하는 부분 */
    static int maxYear = 0;
    static int[][] arr;
    /*left, right, down, up*/
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] visited;

    public static int[][] read() {
        int N = sc.nextInt();
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = sc.nextInt();
                /* 물에 잠기기까지 남은 최대 년도를 계산 */
                if (maxYear < temp[i][j]) {
                    maxYear = temp[i][j];
                }
            }
        }
        return temp;
    }

    public static int solution() {
        // 입력된 값을 N*N 배열로 초기화 함수 호출
        arr = read();
        int n = arr.length;

        // 탐색 결과 저장을 위한 배열 선언
        int[] blocks = new int[maxYear];

        // N*N 배열에 대하여 탐색 과정 수행
        for (int year = 1; year <= maxYear; year++) {
            // 현재 year에 잠기는지 확인 후 총 인접 block 갯수 반환하기
            int count = 0;
            visited = new int[n][n];
            // 해수면에 잠기게 하기
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] != 0) {
                        arr[i][j]--;
                    }
                }
            }
            // 현재 i,j 위치에 대해 DFS로 갈 수 있는 모든 묶음 찾기
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length; j++) {
                    if (arr[i][j] > 0 && visited[i][j] != 1) {
                        dfs(i, j);
                        count++;
                    }
                }
            }
            blocks[year-1] = count;
        }

        return Arrays.stream(blocks).max().getAsInt();
    }

    public static void dfs(int x, int y) {
        int n = arr.length;
        int m = arr[0].length;
        visited[x][y] = 1; // 방문

        for (int[] direction : directions) {
            int nextX = x + direction[0];
            int nextY = y + direction[1];

            if (nextX < 0 || nextY < 0 || nextX > n - 1 || nextY > m - 1) {
                continue;
            }
            // 방문한적이 없고, 해수면에 잠기지 않을 때
            if (visited[nextX][nextY] != 1 && arr[nextX][nextY] > 0) {
                dfs(nextX, nextY);
            }
        }
    }
}
