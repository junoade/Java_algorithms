package category.bruteforce;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 평행</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 6가지 수가 아니라 4-1 = 3 가지수를 확인해야한다! <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  90MB, 실행시간  0.03ms<br/>
 * --------------------------------------------------------------
 */
public class P_Parallel2 {
    public int solution(int[][] dots) {
        boolean test1 = calculate(dots[0], dots[1]) == calculate(dots[2], dots[3]);
        boolean test2 = calculate(dots[0], dots[2]) == calculate(dots[1], dots[3]);
        boolean test3 = calculate(dots[0], dots[3]) == calculate(dots[1], dots[2]);
        return (test1 | test2 | test3) ? 1 : 0;
    }

    double calculate(int[] a, int[] b) {
        int x1 = a[0], y1 = a[1];
        int x2 = b[0], y2 = b[1];
        return (double) (y2 - y1) / (x2 - x1);
    }
}
