package category.bruteforce;

import java.io.*;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------
 * <title> 백준 2798, 블랙잭 </title>
 * 유형 - 완전탐색
 * 복습 yes
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - 카드 세장이라 for문 세번, 모든 경우에 대해 완전 탐색
 * --------------------------------------------------------------
 * <b> 채점 </b>
 * <p> 메모리 14300KB / 128MB , 시간 132ms / 1000ms
 * --------------------------------------------------------------
 */
public class BlackJack_2798 {
    public static void solution(int[] cards, int M) {
        int maxSum = 0;
        for (int i = 0; i < cards.length - 2; i++) {
            for (int j = i + 1; j < cards.length - 1; j++) {
                for (int h = j + 1; h < cards.length; h++) {
                    int sum = cards[i] + cards[j] + cards[h];
                    if(maxSum < sum && M >= sum){
                        maxSum = sum;
                    }
                }
            }
        }
        System.out.println(maxSum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        solution(cards, M);
    }
}
