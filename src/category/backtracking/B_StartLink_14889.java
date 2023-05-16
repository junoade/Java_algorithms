package category.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>B_스타트와 링크 14889 </b><br/>
 * - SM 조삼모사와 유사함<br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 조합 구현<br/>
 * 모든 가능한 경우에 대해 최소값 비교<br/>
 * 한번 갔다가 다시 돌아와서 다른 길로<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  31380 KB / 512MB , 실행시간  308ms / 2000ms<br/>
 * --------------------------------------------------------------
 */
public class B_StartLink_14889 {

    static int[][] arr;
    static int N;
    static int min;

    static void solution() {
        int[] team = new int[N / 2];
        min = Integer.MAX_VALUE;

        rec_comb(N, N/2, 0, team, 0);
        System.out.println(min);
    }

    // 조합 구현
    static void rec_comb(int N, int r, int depth, int[] team, int startIdx) {
        // base case
        if(depth == r) {
            // 이 때 team과 otherTeam간 능력치를 비교
            int powerA = getPower(team);
            int powerB = getPower(getOtherTeam(team));
            // System.out.println(Arrays.toString(team) + " vs " + Arrays.toString(getOtherTeam(team)));
            // System.out.printf(">> powerA : %d, powerB : %d\n", powerA, powerB);
            min = Math.min(min, Math.abs(powerA - powerB));
            return;
        }

        for(int i = startIdx; i < N; i++) {
            team[depth] = i;
            rec_comb(N, r, depth + 1, team, i + 1);
            team[depth] = -1;
        }
    }

    // 조합 nC2을 이용해 팀 능력치 합산
    static int getPower(int[] team) {
        final int L = team.length;
        int sum = 0;
        for (int i = 0; i < L - 1; i++) {
            int aIdx = team[i];
            for (int j = i + 1; j < L; j++) {
                int bIdx = team[j];
                sum += arr[aIdx][bIdx] + arr[bIdx][aIdx];
            }
        }
        return sum;
    }

    static int[] getOtherTeam(int[] team) {
        int[] teamOther = new int[N / 2];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            boolean hasKey = false;
            for (int a : team) {
                if (a == i) {
                    hasKey = true;
                    break;
                }
            }
            if(!hasKey) {
                teamOther[idx++] = i;
            }
        }
        return teamOther;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int[] a : arr) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(temp, 0, a, 0, temp.length);
        }

        solution();
    }
}
