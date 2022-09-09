package category.greedy;

import java.util.Arrays;
import org.junit.Test;

import static org.junit.Assert.*;

public class MyGreedyChangesTest {

    @Test
    public void testGreedyChanges_normal(){
        int money = 4720;
        int[] arr = {1, 50, 100, 500};
        Integer[] coins = Arrays.stream(arr).boxed().toArray(Integer[] :: new);
        int result = MyGreedyChanges.greedyChanges(money, coins);
        assertEquals(31, result);
    }

    @Test
    public void testGreedyChanges_outBound(){
        int money = 0;
        int[] arr = {1, 50, 100, 500};
        Integer[] coins = Arrays.stream(arr).boxed().toArray(Integer[] :: new);
        int result = MyGreedyChanges.greedyChanges(money, coins);
        assertEquals(0, result);
    }

    @Test
    public void testGreedyChanges_negative(){
        int money = -100;
        int[] arr = {1, 50, 100, 500};
        Integer[] coins = Arrays.stream(arr).boxed().toArray(Integer[] :: new);
        int result = MyGreedyChanges.greedyChanges(money, coins);
        assertEquals(0, result);
    }
}
