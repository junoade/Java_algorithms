package category.greedy;

import java.util.Arrays;
import java.util.Collections;

/**
 * --------------------------------------------------------------<br/>
 * <b>그리디 동전 교환</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 그리디 알고리즘의 대표 예제 -
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class MyGreedyChanges {
    public static int greedyChanges(int price, Integer[] coins){
        Arrays.sort(coins, Collections.reverseOrder());
        int changes = 0;
        for(Integer coin : coins){
            int change = price / coin;
            if(change > 0){
                changes += change;
                price -= change * coin;
            }
        }
        return changes;
    }
}
