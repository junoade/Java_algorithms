package dataStructures.sorts.ComparisonSorts.InsertionSort;

public class InsertionSort {
    public static <T extends Comparable<? super T>> void sort(T[] a) {
        final int L = a.length;
        for (int i = 1; i < L; i++) {
            int j = i;
            T keyValue = a[i];

            // value를 정렬된 집합의 맨 오른쪽(j-1)과 비교해가며
            // 대소 비교 후 오른쪽으로 값을 shift하여 자신의 삽입 위치를 찾아간다.
            // j = 1부터 시작하므로
            // 어떤 구현은 j = i - 1부터 시작할 수도 그러면 아래의 세부 조건이 바뀌긴 한다
            while (j > 0 && a[j - 1].compareTo(keyValue) > 0) {
                a[j] = a[j - 1];
                j--;
            }
            a[j] = keyValue; // 찾은 삽입 위치에 삽입
        }
    }
}
