package category.recursive;

import java.io.*;
import java.util.StringTokenizer;

public class B_searchByZ_1074 {
    // 2^N; * 2^N;
    static int N, X, Y;

    // 기록용
    static int count;

    // 오른쪽 -> 대각선 아래 -> 오른쪽
    static int[] dx = {0, 1, 0};
    static int[] dy = {1, -1, 1};

    static void solution() {
        int L = (int) Math.pow(2, N);
        rec(0, 0, L);
        System.out.println(count);
    }


    static void rec(int x, int y, int L) {
        // base case #1
        if (x == X && y == Y) {
            return;
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
                    break;
                }
            }
            return;
        }

        // general case
        // 사분면 결정 (재귀)
        // 건너뛰는 사분면의 count 계산
        // 찾을 때 까지, L==2 일 때 까지

        if(X < x + L/2 && Y < y + L/2) {
            rec(x, y, L / 2);
            return;
        }

        count += L * L / 4;
        if (X < x + L / 2 && Y >= y + L / 2) {
            rec(x, y + L / 2, L / 2);
            return;
        }

        count += L * L /4;
        if (X >= x + L / 2 && Y < y + L / 2) {
            rec(x + L / 2, y, L / 2);
            return;
        }

        count += L * L /4;
        rec(x + L / 2, y + L / 2, L / 2);

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
