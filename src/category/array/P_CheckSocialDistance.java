package category.array;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 LV2 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 어떻게 맨해튼 거리 2를 확인할 것인가?
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 1. 입력 처리 과정에서 별도의 2차원 배열 int[][] 를 선언하여 {P, O, X} 에 맞게 0, 1, 2 값을 부여
 * <br/> 2. P의 위치를 ArrayList에 기록하고, 이 리스트를 바탕으로 주변 맨해튼 거리 1 그리고 2 를 탐색
 * <br/> 3. 맨해튼 거리가 1인 지점이 P(0)면 false 반환, O(1)이면 맨해튼 거리 2에 대해서 조사, X(2) 이면 P이든 O이든 상관 없어서 스킵
 * <br/> 4. 해당 방에서 한명의 p라도 0이면 탐색 종료
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 100점 31/31
 * --------------------------------------------------------------
 */
public class P_CheckSocialDistance {
    static final int LENGTH = 5;
    static final int P_IDX = 0;
    static final int O_IDX = 1;
    static final int X_IDX = 2;

    // x: row, y : col;
    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    static class Person {
        // x: row, y = col;
        int x;
        int y;

        Person(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    ArrayList<Person> p_list;

    int checkEachRoom(String[] room) {
        // int[][] 배열로 기록
        int[][] arr = loopAndRecord(room);

        // p_list 순회하며 맨해튼 거리 확인
        return getManhattan(arr);
    }

    int[][] loopAndRecord(String[] room) {
        int[][] arr = new int[LENGTH][LENGTH];
        p_list = new ArrayList<>();
        int i = 0, j = 0;
        for (String row : room) {
            j = 0;
            for (String s : row.split("")) {
                if (s.equals("P")) {
                    arr[i][j] = P_IDX;
                    p_list.add(new Person(i, j));
                } else if (s.equals("O")) {
                    arr[i][j] = O_IDX;
                } else {
                    arr[i][j] = X_IDX;
                }
                j++;
            }
            i++;
        }

        return arr;
    }

    // 3. 대기실 별로 확인하며 하나라도 거리두기 X면 0 반환
    int getManhattan(int[][] arr) {
        for (Person p : p_list) {
            int x = p.x;
            int y = p.y;
            if (!validateDistance(arr, x, y)) {
                return 0;
            }
        }
        return 1;
    }

    boolean validateDistance(int[][] arr, int x, int y) {
        // d1 부터 확인
        int L = dx.length;
        for (int i = 0; i < L; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 주변 확인
            if (isOutBoundary(nx, ny)) {
                continue;
            }

            if (arr[nx][ny] == 0) {
                return false;
            }

            // d2 도 확인
            else if (arr[nx][ny] == 1) {
                for (int j = 0; j < L; j++) {
                    int nnx = nx + dx[j];
                    int nny = ny + dy[j];
                    if (nnx == x && nny == y) {
                        continue;
                    }

                    if(isOutBoundary(nnx, nny)) {
                        continue;
                    }

                    if (arr[nnx][nny] == 0) {
                        return false;
                    }

                }
            } // else { }  // 가림막 있으면 상관 없다.
        }

        return true;
    }

    boolean isOutBoundary(int x, int y) {
        boolean cond1 = x < 0 || y < 0;
        boolean cond2 = x == LENGTH || y == LENGTH;
        return cond1 || cond2;
    }

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        // 1. 입력처리
        // 2. p의 위치 별로 맨해튼 거리 내 거리두기 확인
        // 3. 대기실 별로 확인하며 하나라도 거리두기 X면 0 반환
        int idx = 0;
        for (String[] room : places) {
            answer[idx] = checkEachRoom(room);
            idx++;
        }
        return answer;
    }

    public static void main(String[] args) {
        P_CheckSocialDistance checkSocialDistance = new P_CheckSocialDistance();
        String[][] ex01 = {{"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}};

        String[][] ex02 = {{"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"}};
        System.out.println(Arrays.toString(checkSocialDistance.solution(ex01)));
    }
}
