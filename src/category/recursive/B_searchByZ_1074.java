package category.recursive;

import java.io.*;
import java.util.StringTokenizer;

public class B_searchByZ_1074 {
    static int N, X, Y;

    // 기록용
    static int count;

    // 오른쪽 -> 대각선 아래 -> 오른쪽
    static int[] dx = {0, 1, 0};
    static int[] dy = {1, -1, 1};

    static void solution() {
        rec(0, 0, (int) Math.pow(2, N));
        System.out.println(count);
    }


    static boolean rec(int x, int y, int L) {
        // base case #1
        if (x == X && y == Y) {
            return true;
        }

        // base case #2
        // L = 2일 때
        if (L == 2) {
            // 갱신 필요!
            int nx = x;
            int ny = y;
            for (int d = 0; d < dx.length; d++) {
                nx = nx + dx[d];
                ny = ny + dy[d];

                // 범위를 벗어나지 않도록 설계했음
                count++;
                if (nx == X && ny == Y) {
                    return true;
                }
            }
            return false;
        }

        // general case
        // (0,0)
        // (0, 0 + L/2)
        // (0+ L/2, 0)
        // (0+L/2, 0+L/2)
        if (rec(x, y, L / 2)) {
            return true;
        }
        count++;
        if (rec(x, y + L / 2, L / 2)) {
            return true;
        }
        count++;
        if (rec(x + L / 2, y, L / 2)) {
            return true;
        }
        count++;

        return rec(x + L / 2, y + L / 2, L / 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        solution();
    }
}
