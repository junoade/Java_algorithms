package category.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/**
 * --------------------------------------------------------------<br/>
 * <b>B 물통 2251</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 최초 C 용량 만큼 물을 담고 <br/>
 * 1. 현재 물통에 물이 있는지
 * 2. 현재 물통의 물을 다른 물통에 대해 물을 줄 수 있는 상태인지
 * 3. 이미 옮긴 적 있는 상태 인지; 물통 A와 물통 B에 남아있는 물의 양으로 판별
 * 4. 아니라면 방문해주고 물을 전달; 이 때 static 객체로 관리하는 각 객체에 대해 상태가 변할 수 있음에 주의
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 초기 풀이 작성<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class B_WaterGlass {

    static final int N = 3;
    static Glass[] glasses;
    static HashSet<Integer> set;

    // A의 물의 양 - B의 물의 양
    static boolean[][] visited;

    static class Glass {
        int maxWater;
        int curWater;
        String name;

        Glass(int maxWater, int curWater, String name) {
            this.maxWater = maxWater;
            this.curWater = curWater;
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("%s {%d / %d}", name, curWater, maxWater);
        }
    }

    static void solution() {
        updateGlassStatusIfPossible();
        dfs();

        Integer[] arr = set.toArray(Integer[]::new);
        Arrays.sort(arr);
        for (Integer num : arr) {
            System.out.print(num + " ");
        }
    }

    static void dfs() {
        // 6가지 경우의 수에 대해 확인
        for (int startIdx = N - 1; startIdx >= 0; startIdx--) {
            Glass start = glasses[startIdx];
            final int START_WATER = start.curWater;

            if (!isAbleToGiveWater(start)) {
                continue;
            }

            // 다음 물통 확인해주기
            for (int toIdx = 0; toIdx < N; toIdx++) {
                if (startIdx == toIdx) {
                    continue;
                }

                Glass to = glasses[toIdx];
                final int TO_WATER = to.curWater;

                // 물을 줄 수 있나?
                if (!isAbleToGiveWater(start, to)) {
                    continue;
                }

                // 현재 A, B에 남은 물의 양에 대해 visited check
                final int A1 = glasses[0].curWater, B1 = glasses[1].curWater;
                if (visited[A1][B1]) {
                    continue;
                }
                visited[A1][B1] = true;

                // 물을 준다
                giveWater(start, to);

                // A가 비어있는 지 확인, 그렇다면 C의 물의 양을 확인
                updateGlassStatusIfPossible();

                // 바뀐 상태에 대해 모든 경우의 수에 대해 탐색하도록 다음 재귀 호출
                dfs();

                // 상태 변화가 생긴 객체들에 대해
                // 방문 전 상태로 원복
                visited[A1][B1] = false;
                start.curWater = START_WATER;
                to.curWater = TO_WATER;
            }
        }
    }

    // A가 비어있는 지 확인
    // 그렇다면 C의 물의 양을 확인
    static void updateGlassStatusIfPossible() {
        if (glasses[0].curWater == 0) {
            set.add(glasses[2].curWater);
        }
    }

    // 현재 물통이 물을 줄 수 있나
    static boolean isAbleToGiveWater(Glass start) {
        return start.curWater > 0;
    }

    // 현재 물통이 다른 물통에 물을 줄 수 있나
    static boolean isAbleToGiveWater(Glass start, Glass to) {
        int possibleAmount = to.maxWater - to.curWater;
        return possibleAmount > 0 && isAbleToGiveWater(start);
    }

    // 물 건내기 로직
    static void giveWater(Glass start, Glass to) {
        // 전달하려는 물의 양이 받는 병의 maxWater 보다 크면
        int possibleAmount = to.maxWater - to.curWater;
        if (possibleAmount > 0) {
            // 다 주는 경우
            if (start.curWater <= possibleAmount) {
                to.curWater += start.curWater;
                start.curWater = 0;
            }
            // 일부만 주는 경우
            else {
                start.curWater -= possibleAmount;
                to.curWater += possibleAmount;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = inputs[0], B = inputs[1], C = inputs[2];

        glasses = new Glass[3];
        glasses[0] = new Glass(A, 0, "A");
        glasses[1] = new Glass(B, 0, "B");
        glasses[2] = new Glass(C, C, "C");

        // 현재 A, B의 물의 양을 기준으로 visited check
        visited = new boolean[A + 1][B + 1];

        set = new HashSet<>();

        solution();
    }
}