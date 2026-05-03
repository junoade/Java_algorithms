package daily.y2025.q3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2025 프로그래머스 코드챌린지 2차 택배 상자 꺼내기
 * 1차 38분
 */
public class Solution_P_BoxPolling {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int n = Integer.parseInt(input[0]);
        int w = Integer.parseInt(input[1]);
        int num = Integer.parseInt(input[2]);

        Solution_P_BoxPolling instance = new Solution_P_BoxPolling();
        System.out.println(instance.solution(n, w, num));
    }

    // TC1 - 13 3 6
    // TC2 - 22 6 8
    // TC3 - 2 1 1
    public int solution(int n, int w, int num) {
        final int x = w;
        // int y = n/x + 1;
        final int y = (n + w - 1) / w;
        int[] targetPos = {-1, -1};
        int[][] arr = new int[y][x];

        if(num < 1 || num > n) return 0;

        int value = 1;
        boolean flag = false;

        for (int i = 0; i < y; i++) {
            if(i % 2 == 0) {
                // 왼쪽에서 오른쪽으로 증가
                for(int j = 0; j < w; j++) {
                    arr[i][j] = value;
                    if(value == num) {
                        mark(targetPos, i, j);
                    }

                    if(value == n) {
                        flag = true;
                        break;
                    }

                    value++;
                }

            } else {
                // 오른쪽으로 왼쪽으로 증가
                for(int j = w - 1; j >= 0; j--) {
                    arr[i][j] = value;
                    if(value == num) {
                        mark(targetPos, i, j);
                    }

                    if(value == n) {
                        flag = true;
                        break;
                    }
                    value++;
                }
            }

            if(flag) {
                break;
            }
        }

        // 위치 찾기
        int answer = 1; // 자기자신도 빼니까 1
        int top = y - 1;
        while(top != targetPos[0]) {
            if(arr[top][targetPos[1]] != 0) {
                answer++;
            }
            top--;
        }

        return answer;
    }

    void mark(int[] targetPos, int x, int y) {
        targetPos[0] = x;
        targetPos[1] = y;
    }


}
