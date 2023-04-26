package dataStructures.sorts.ComparisonSorts.SelectionSort;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        testIntegerArr();
        testFloatArr();
    }

    static void testIntegerArr() {
        Integer[] arr = {1, 99, 123, 14, 5, 5, 124, 11};
        display(arr);
    }

    static void testFloatArr() {
        Float[] arr = {1.0f, 99f, 123f, 14f, 5f, 5f, 124.59f, 11.12f};
        display(arr);
    }

    private static <T extends Comparable<? super T>> void display(T[] arr) {
        System.out.println("타입 : " + arr.getClass());
        System.out.println("Before Sorting : " + Arrays.toString(arr));
        SelectionSort.sort(arr);
        System.out.println("After Sorting : " + Arrays.toString(arr));
    }
}
