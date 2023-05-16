package category.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_HideAndSeek_1697 {

    static int min;
    static boolean[] visited;
    static final int[] DIR = {2, -1, 1};
    static int N = 100000;

    static void solution(int A, int B) {
        min = Integer.MAX_VALUE;
        visited = new boolean[N + 1];

        visited[A] = true;
        rec(0, A, B);
        System.out.println(min);
    }

    static void rec(int time, int src, int target) {
        // base case
        // src 가 target 보다 크거나 같으면
        // 같으면 추가되는 시간은 0;
        // src > target이면 뒤로 이동해야하므로 그 만큼 시간 더해줌
        if (src >= target) {
            time += Math.abs(target - src);
            min = Math.min(min, time);
            return;
        }

        // 새로운 좌표가 타겟보다 작을 때
        for (int d = 0; d < DIR.length; d++) {
            int newPos = src;
            if (d == 0) {
                newPos *= DIR[d];
            } else {
                newPos += DIR[d];
            }

            if (isOutbound(newPos)) {
                continue;
            }

            if (!visited[newPos]) {
                visited[newPos] = true;
                rec(time + 1, newPos, target);
            }
        }

    }

    static boolean isOutbound(int n) {
        return n < 0 || n > N;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int A = inputs[0];
        int B = inputs[1];
        solution(A, B);
    }
}
