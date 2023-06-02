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

    static boolean[][] visited;
    static final int N = 3;
    static Glass[] glasses;
    static HashSet<Integer> set;

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

        // 탐색 전에
        // 방문 처리
        // A가 비어있는지 확인하고 그렇다면 C의 물의 양을 확인
        visited[2][glasses[2].curWater] = true;
        if(glasses[0].curWater == 0) {
            set.add(glasses[2].curWater);
        }

        dfs(2);

        Integer[] arr = set.toArray(Integer[]::new);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }

    static void dfs(int startIdx) {
        Glass start = glasses[startIdx];
        final int START_WATER = start.curWater;

        // 다음 물 확인해주기
        for(int toIdx = 0; toIdx < N; toIdx++) {
            if(toIdx != startIdx) {
                Glass to = glasses[toIdx];
                final int TO_WATER = to.curWater;

                // 물을 줄 수 있나?
                if(!isAbleToGiveWater(start, to)) {
                    System.out.println("[CONTINUE] 물 줄 수 없음");
                    continue;
                }

                // 남은 물의 양에 대해 visited check
                if(visited[startIdx][start.curWater] && visited[toIdx][to.curWater]) {
                    System.out.println("[CONTINUE] 방문한 적 있는 물의 양\n");
                    continue;
                }

                // 물을 주고 나서
                System.out.printf("건내기 전 - start : %s, to : %s, mid : %s\n", start, to, findAnother(start, to));
                giveWater(start, to);
                visited[startIdx][start.curWater] = true;
                visited[toIdx][to.curWater] = true;
                System.out.printf("건내고 난 후 - start : %s, to : %s, mid : %s\n", start, to, findAnother(start, to));


                // A가 비어있는 지 확인
                // 그렇다면 C의 물의 양을 확인
                if(glasses[0].curWater == 0) {
                    set.add(glasses[2].curWater);
                }

                // 다음 재귀
                System.out.println("다음 재귀 호출\n");
                dfs(toIdx);

                // 다녀와서 curWater 복구해줌
                System.out.println("상태 초기화");
                // 방문 하고나서 상태
                visited[startIdx][start.curWater] = false;
                visited[toIdx][to.curWater] = false;
                start.curWater = START_WATER;
                to.curWater = TO_WATER;
                // 방문 전 상태


            }

        }
    }

    static boolean isAbleToGiveWater(Glass start, Glass to) {
        int possibleAmount = to.maxWater - to.curWater;
        return possibleAmount > 0 && start.curWater > 0;
    }

    static void giveWater(Glass start, Glass to) {
        // 전달하려는 물의 양이 받는 병의 maxWater 보다 크면
        int possibleAmount = to.maxWater - to.curWater;
        if(possibleAmount > 0) {
            // 다 주는 경우
            if(start.curWater < possibleAmount) {
                to.curWater += start.curWater;
                start.curWater = 0;
            }
            // 일부만 주는 경우
            else {
                start.curWater -= possibleAmount;
                to.curWater = possibleAmount;
            }
        }
    }

    static Glass findAnother(Glass start, Glass to) {
        Glass mid = null;
        for(Glass glass : glasses) {
            if (glass == start || glass == to) {
                continue;
            }
            mid = glass;
        }
        return mid;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = inputs[0], B = inputs[1], C = inputs[2];

        glasses = new Glass[3];
        glasses[0] = new Glass(A, 0, "A");
        glasses[1] = new Glass(B, 0, "B");
        glasses[2] = new Glass(C, C, "C");


        // 각자 남은 리터별로 방문 여부 확인하기
        // 1부터 시작
        visited = new boolean[3][];
        visited[0] = new boolean[A + 1];
        visited[1] = new boolean[B + 1];
        visited[2] = new boolean[C + 1];


        set = new HashSet<>();

        solution();
    }
}