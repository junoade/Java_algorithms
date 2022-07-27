package category.simulation.baekjun;

import java.io.*;
import java.util.StringTokenizer;


/**
 * --------------------------------------------------------------
 * <title> 백준 16974, 레벨 햄버거 </title>
 * 유형 - 시뮬레이션
 * --------------------------------------------------------------
 * <b> 주요 키포인트 </b>
 * <p>
 * <p>
 * --------------------------------------------------------------
 * <b> 나의 풀이 </b>
 * - 1차 풀이 : 재귀함수 이용해서 햄버거 구현하는 풀이
 * -> N=29일때 부터 터짐
 * - 2차 풀이 : 내가 L-layer에서 먹어야 하는 햄버거(패티 또는 번)의 개수를 비교하는 방식으로 재귀 깊이를 조절
 * <p>
 * --------------------------------------------------------------
 * <b> 채점 </b>
 * 시간 132ms/150ms
 * --------------------------------------------------------------
 * <b> 생각한 내용 </b>
 * <p>
 * OutOfMemoryError - Java Heap space 문제, N=29일때부터 발생
 * - static 메소드라 문제? X, 인스턴스 메소드라도 걸림
 * - StringBuffer라 문제? X, String 클래스 역시 heap Space 문제 생김
 * - 얼마나 크길래?
 * a_n = \sigma 6^{n-2} + 2^{n-1} (n>=1)
 * a_0 = 1;
 */
public class LevelHamburger {

    public static final int LAYER = 50;
    public static long[] burger = new long[LAYER + 1]; // 각 Layer의 먹을 수 있는 패티 또는 번의 개수
    public static long[] patties = new long[LAYER + 1];
    public static long count = 0;

    /**
     * 2차 풀이 - 먹어야하는 층의 정보 X 활용
     *
     * @param N
     * @param X
     */
    public static void burger(int N, long X) {
        /* base case 1 : 더 이상 먹을 패티 또는 번이 없는 경우 */
        if (X == 0) {
            System.out.println(count);
            return;
        }
        /* base case 2 : 햄버거 Layer-0 까지 온 경우 */
        else if (N == 0) {
            count++;
            System.out.println(count);
            return;
        }

        X--; // (N-1)-layer의 버거에 들어가기전 N-버거의 맨 밑의 번 먹음

        /* 레이어 N에 존재하는 햄버거의 크기와 내가 먹어야하 는 X의 개수가 같냐? 작냐? 크냐에 따라*/
        if (X == burger[N - 1]) { // 내가 먹어야 하는 버거 또는 패티가, N-1 층의 버거 수와 같음.
            count += patties[N - 1];
            X -= burger[N - 1];
            burger(N - 1, X);
        } else if (X > burger[N - 1]) { //내 가 먹어야 하는 버거 또는 패티가, N-1층의 버거 수보다 큼.
            count += patties[N - 1] + 1; // 중간 P 먹고 위에 N-1층도 먹어야겠네.
            X -= (burger[N - 1] + 1); // 중간 P 먹은거 포함
            burger(N - 1, X);
        } else { // 내가 먹어야하는 버거 또는 패티가 N-1층의 버거 수 보다 작음
            burger(N - 1, X); // 더 깊이 탐색할 필요가 있음
        }
    }

    /**
     * 주어진 로직대로 시뮬레이션 했을때, 먹은 패티 수 반환하기
     *
     * @param N
     * @param X
     */
    public static void solution(int N, long X) {
        patties[0] = 1;
        burger[0] = 1;
        /* 각 Layer의 번 개수, 패티 개수 계산 */
        for (int i = 1; i <= 50; i++) {
            patties[i] = 2 * patties[i - 1] + 1;
            burger[i] = 2 * burger[i - 1] + 3;
        }
        burger(N, X);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        solution(N, X);
    }


    /**
     * 1차풀이 - 재귀함수 이용 햄버거 구하고 나서 계산하기
     * -> 힙 메모리가 부족할 뿐더러,
     * -> O(N) 선형탐색으로 Layer가 큰 햄버거에 대해 탐색하는 것은 비효율적
     *
     * @param N
     */
    public static StringBuffer makeBurger(int N) {
        /* Base Case */
        if (N == 0) {
            return new StringBuffer("P");
        } else {
            String front = "B";
            String rear = "B";
            StringBuffer priorBurger = makeBurger(N - 1);
            return new StringBuffer().append("B").append(priorBurger).append("P").append(priorBurger).append("B");
        }
    }
}
