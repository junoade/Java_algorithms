package category.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------
 * 백준 1748, 1<=N<=100,000,000
 * 유형 - 브루트포스
 * --------------------------------------------------------------
 * 주요 키포인트
 * <p> 자릿수 규칙에 대한 패턴 발견
 * <p>
 * --------------------------------------------------------------
 * 나의 풀이
 * - N ~ 10 * Math.pow(10, length -1) 사이의 숫자들의 갯수와 그 때의 길이, length 를 곱하며,
 * - 자릿수에 대해 탐색을 진행함
 * - 시간복잡도는 최악의 경우, N = 100,000,000일 때, length = 9이므로 O(9)임
 * <p>
 * 채점 : 120ms, 시간 제한 150ms
 * -> 더 간단한 규칙으로, 더 빠르게 푼 사람들이 있음.
 */
public class SequentialWriting_1748 {

    /* x * (i+1, 자릿수) - y * (i 자릿수) */
    public static void solution(int n, int length) {
        int sum = length * (int) (n - Math.pow(10, length - 1) + 1);

        for (int d = length - 1; d > 1; d--) {
            sum += d * ((Math.pow(10, d) - 1) - Math.pow(10, d - 1) + 1);
        }

        if (length > 1) {
            sum += 9;
        }
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String input = st.nextToken();
        int n = Integer.parseInt(input);

        solution(n, input.length());

    }

}