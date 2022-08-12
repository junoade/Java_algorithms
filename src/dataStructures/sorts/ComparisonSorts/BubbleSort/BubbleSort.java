package dataStructures.sorts.ComparisonSorts.BubbleSort;

/**
 * What is a  bubble sort?
 * repeatedly swapping the adjacent elements if they are in wrong order.
 */
public class BubbleSort {
    public static void bubbleSort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) { // N까
            for (int j = 0; j < N - i - 1; j++) {
                if (isGreater(i, j)) //a[i] > a[j] 이면 swap
                    swap(a, i, j);
            }
        }
    }

    public static boolean isGreater(Comparable i, Comparable j) {
        return (i.compareTo(j) > 0); // i.compareTo(j) i<j 이면 -1 i=j면 0 i>j  면 1 ,
        // <0 이면
    }

    public static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = a[i];

    }

}
