package dataStructures.sorts.ComparisonSorts.MergeSort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MySplitter<T> {
    public T[] splitArray(T[] list, int fromIdx, int toIdx) {
        int newSize = toIdx - fromIdx;
        T[] newList = (T[]) Array.newInstance(list.getClass().getComponentType(), newSize);

        if (list.length == 0)
            System.out.println("Array is Empty");
        System.arraycopy(list, fromIdx, newList, 0, newSize);
        return newList;
    }

    public static void main(String[] args) {
        MySplitter<Integer> splitter = new MySplitter<Integer>();
        Integer[] list = new Integer[]{1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(list));
        Integer[] list2 = splitter.splitArray(list, 1, 3);
        System.out.println(Arrays.toString(list2));
    }
}
