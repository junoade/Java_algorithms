package dataStructures.search;

import java.util.Arrays;

public class MyBinarySearch<T extends Comparable<T>> {

    /**
     * 재귀적인 풀이
     *
     * @param arr
     * @param low
     * @param high
     * @param key
     * @return
     */
    public boolean binarySearch(T[] arr, int low, int high, T key) {
        /* Base case */
        if (low > high)
            return false;

        int mid = (low + high) / 2;
        int result = arr[mid].compareTo(key); // 앞이 크면 양수

        if (result == 0)
            return true;
        else if (result > 0)
            return binarySearch(arr, low, mid - 1, key);
        else
            return binarySearch(arr, mid + 1, high, key);
    }

    public boolean binarySearch(T[] arr, T key) {
        Arrays.sort(arr);

        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + mid) / 2; // 주의하기
            int result = arr[mid].compareTo(key);
            if (result > 0) { // arr[mid]가 더 큰 경우
                high = mid - 1;
            } else if (result < 0) {
                low = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
