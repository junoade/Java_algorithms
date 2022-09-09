package category.greedy;

import org.junit.Test;

import static org.junit.Assert.*;

public class Fractional_Knapsack_Test {


    @Test
    public void testExample01() {
        Integer[][] test = {{10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5}};
        int MAX_WEIGHT = 30;
        double actual = Fractional_Knapsack_Problem.solution(test, MAX_WEIGHT);
        assertEquals(24.5, actual, 0.01);
    }
}
