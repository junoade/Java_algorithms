package dataStructures.sorts.ComparisonSorts.SelectionSort;

public class SelectionSort {
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        final int L = a.length;
        for(int i = 0; i < L; i++) {
            int minIdx = i;
            for(int j = i + 1; j < L; j++) {
                if(isLess(a[j], a[minIdx])) {
                    minIdx = j;
                }
            }

            // minIdx 탐색 완료
            swap(a, i, minIdx);
        }
    }

    // t1 < t2라면 true;
    private static <T extends Comparable<? super T>> boolean isLess(T t1, T t2) {
        return t1.compareTo(t2) < 0;
    }

    private static<T> void swap(T[] a, int i, int j) {
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
