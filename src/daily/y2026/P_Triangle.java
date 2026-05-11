package daily.y2026;

import java.util.Arrays;

public class P_Triangle {

    // 아래, 오른쪽, 대각선
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};

    public int[] solution(int n) {
        final int N = n;
        int[][] arr = new int[N][];
        for (int i = 0; i < N; i++) {
            arr[i] = new int[i+1];
        }


        int x = 0, y = 0, value = 1, d = 0;

        while (true) {
            arr[x][y] = value++;
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (isOutbound(nx, ny, N) || arr[nx][ny] != 0) {
                // 다른 방향으로 시도해본다.
                d = (d + 1) % 3;
                nx = x + dx[d];
                ny = y + dy[d];

                if (isOutbound(nx, ny, N) || arr[nx][ny] != 0) {
                    // 그 방향도 안될 경우 종료
                    break;
                }
            }

            x = nx;
            y = ny;
        }

        // 2. convert to 1d array
        int[] answer = new int[value - 1];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx] = arr[i][j];
                idx++;
            }
        }

        return answer;
    }

    boolean isOutbound(int x, int y, int n) {
        return (x < 0 || x >= n) || (y < 0 || y >= n);
    }

    public static void main(String[] args) {
        P_Triangle p = new P_Triangle();
        int[] answer = p.solution(1000);
        System.out.println(Arrays.toString(answer));

    }
}
