package dataStructures.sorts.ComparisonSorts.BubbleSort;

public class MyBubbleSort {

    public static <T extends Comparable<? super T>> void sort(T[] a) {
        final int L = a.length;
        for (int i = 0; i < L; i++) {
            boolean isSwap = false;

            for (int j = 0; j < L - 1 - i; j++) {
                // 인덱스 j와 j+1의 요소를 비교
                // 오름차순 정렬하므로
                // j > j+1일 때 정렬
                if (a[j].compareTo(a[j + 1]) > 0) {
                    swap(a, j, j + 1);
                    isSwap = true;
                }
            }

            if (!isSwap) {
                break;
            }
        }
    }

    static <T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
