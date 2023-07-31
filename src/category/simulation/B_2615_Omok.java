package category.simulation;

import java.io.*;
import java.util.*;

public class B_2615_Omok {
    static final int N = 19;

    static void solution(int[][] maps) {
        StringBuilder sb = new StringBuilder();
        boolean isFound = false;

        for(int i = 0; i < N; i++) {
            if(isFound) {
                break;
            }

            for(int j = 0; j < N; j++) {
                int value = maps[i][j];
                if(value == 0) {
                    continue;
                }

                if(hasWon(maps, i, j)) {
                    // 출력시 idx + 1 주의;
                    sb.append(value).append("\n").append(i + 1).append(" ").append(j + 1);
                    isFound = true;
                    break;
                }
            }
        }

        if(!isFound) {
            sb.append(0);
        }

        // 행 렬 순
        System.out.print(sb);
    }

    // 조건을 만족하는 제일 첫번째 위치를 반환하는 것이 목적이므로
    // 4가지 방향{아래, 오른쪽, 대각선 위, 대각선 아래} 에 대해서만 5번 탐색 수행
    // 승리 조건을 발견하면 바로 return true;하고 종료
    static boolean hasWon(int[][] maps, int x, int y) {
        int[] dx = {0, 1, -1, 1};
        int[] dy = {1, 0, 1, 1};
        int value = maps[x][y];

        for(int d = 0; d < dx.length; d++) {
            int matchCnt = 1;
            int nx = x;
            int ny = y;
            // 딱 5번만 매칭 되어야함
            // 혹시 모르니까 6번 탐색 수행
            for(int count = 1; count < 5; count++) {
                nx = nx + dx[d];
                ny = ny + dy[d];

                if(isOutbound(nx, ny)) {
                    break;
                }

                if(maps[nx][ny] == value) {
                    matchCnt++;
                }
            }

            if(matchCnt == 5) {
                // 현재 위치의 반대 방향, 정방향에 대해서
                // 육목 확인 후 반환해주기
                int leftX = x - dx[d], leftY = y - dy[d];
                int rightX = nx + dx[d], rightY = ny + dy[d];

                if(!isOutbound(leftX, leftY) && maps[leftX][leftY] == value) {
                    continue;
                }

                if(!isOutbound(rightX, rightY) && maps[rightX][rightY] == value) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }

    static boolean isOutbound(int x, int y) {
        return (x < 0 || x >= N) || (y < 0 || y >= N);
    }

    public static void main(String[] args) throws Exception {
        //////////////////////////////////////////////////////////////
        // 테스트 후 아래 파일 입력을 표준입력으로 처리하는 문장은 주석 처리해주세요!!!! ( System.setIn처리 코드 )
        //////////////////////////////////////////////////////////////
        System.setIn(new FileInputStream("src/category/simulation/input/B_2615_input06.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] maps = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(maps);
        br.close();
    }
}
