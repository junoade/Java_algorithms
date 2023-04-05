package category.recursive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P_HanoiTower {
    List<int[]> list;
    final int SUM_OF_POSITION = 6;

    public int[][] solution(int n) {
        list = new ArrayList<>();
        hanoi(n, 1, 3);
        return list.toArray(new int[list.size()][]);
    }


    void hanoi(int n, int start, int to) {
        /* base case, 제일 마지막에 호출되는 부분 문제 */
        if(n == 1) {
            list.add(new int[]{start,to});
            return;
        }

        int mid = SUM_OF_POSITION - start - to;
        hanoi(n-1, start, mid);
        hanoi(1, start, to);
        hanoi(n-1, mid, to);
    }

    public static void main(String[] args) {
        P_HanoiTower p = new P_HanoiTower();
        System.out.println(Arrays.deepToString(p.solution(2)));
    }
}
