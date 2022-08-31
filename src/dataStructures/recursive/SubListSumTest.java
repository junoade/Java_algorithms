package dataStructures.recursive;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SubListSumTest {

    SubListSum sum;

    @Before
    public void init() {
        sum = new SubListSum();
    }

    @Test
    public void testRecursiveSum() {
        int[] datas = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayList<Integer> testData = (ArrayList<Integer>) Arrays.stream(datas).boxed().collect(Collectors.toList());
        int result = sum.recursiveSum(testData);

        assertEquals(result, 45);
    }
}
