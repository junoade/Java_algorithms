package baekjoon.problems.silver;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 하노이탑 문제
 * A, B, C 3 막대가 있다고 할 때,
 * A에 쌓인 N개의 크기가 큰게 아래로 작은게 위로간 탑을 C로 옮기는 문제다.
 * A=1, B=2, C=3 이고 해당 문제에서
 * A: start , B : mid , C : to 라는 이름으로 paramter를 설정함
 * <p>
 * 1차 풀이 2021-09-17
 * 2차 풀이
 */
public class Hannoi11729 {

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // Hanoi 탑 공식 a_n = 2^n-1
        sb.append((int) (Math.pow(2, N)) - 1).append("\n");
        hanoi(N, 1, 2, 3);

        System.out.println(sb);
    }

    /*
     * start : A
     * mid : B
     * to : C
     */
    public static void hanoi(int N, int start, int mid, int to) {
        // base case
        if (N == 1) {
            sb.append(start + " " + to + "\n");
            return;
        }
        /* case 1: N-1 개의 블록들을 B로 옮기기 */
        hanoi(N - 1, start, to, mid);

        /* case 2 A에 남은 큰 막대를 C로 옮기기 */
        sb.append(start + " " + to + "\n");

        /* case 3 B에서 C로 옮기기 */
        hanoi(N - 1, mid, start, to);
    }

}

