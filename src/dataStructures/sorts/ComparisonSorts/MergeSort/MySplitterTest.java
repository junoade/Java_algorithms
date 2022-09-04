package dataStructures.sorts.ComparisonSorts.MergeSort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MySplitterTest {

    @Test
    public void testSplitWithStringType() {
        MySplitter<String> splitter = new MySplitter<>();
        String[] testArr1 = new String[]{"Junho", "Hunho", "James", "John", "Hennessy"};
        String[] testArr2 = splitter.splitArray(testArr1, 1, 3);
        assertEquals("[Hunho, James]", Arrays.toString(testArr2));
    }

    @Test
    public void testSplitWithIntegerType() {
        MySplitter<Integer> splitter = new MySplitter<>();
        Integer[] testArr1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] testArr2 = splitter.splitArray(testArr1, 1, 5);
        assertEquals("[2, 3, 4, 5]", Arrays.toString(testArr2));
    }

    @Test
    public void testSpiltWithCollectionType() {
        ArrayList<Integer> testArr1 = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
        List<Integer> testArr2 = testArr1.subList(1, 5);

        // ArrayList<Integer> testArr2 = splitter.splitArray(testArr1, 1, 5); // 안 됨
        assertEquals("[2, 3, 4, 5]", Arrays.toString(testArr2.toArray()));
        assertEquals("[2, 3, 4, 5]", testArr2.toString()); // toString을 별도로 오버라이딩해서

    }
}
