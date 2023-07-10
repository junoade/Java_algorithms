package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B_DecreasingSequence {

    static List<Long> list;
    static final int[] symbols = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
    static boolean[] visited;

    static long solution(int N) {

        list = new ArrayList<>();
        final int L = symbols.length;
        visited = new boolean[L];

        // 백트랙킹으로 가능한 모든 감소하는 수의 순열을 구함
        for (int r = 1; r <= L; r++) {
            recursive(L, r, 0, new StringBuilder());
        }

        Collections.sort(list);

        if (list.size() < N) {
            return -1;
        }

        return list.get(N - 1);
    }

    static void recursive(int n, int r, int depth, StringBuilder sb) {
        // base case : r == depth일 때
        if (depth == r) {
            // 추가함
            list.add(Long.parseLong(sb.toString()));
            return;
        }

        // general case
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            if (sb.length() >= 1 && sb.charAt(sb.length() - 1) - '0' < symbols[i]) {
                continue;
            }

            sb.append(symbols[i]);
            visited[i] = true;
            recursive(n, r, depth + 1, sb);
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println(solution(N));
    }
}
