package dataStructures.search;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>이분 탐색 응용 - 파라메트릭 서치란?</b><br/>
 * 어떤 값이 존재하는 위치를 O(log N)의 시간복잡도로 탐색하는 이분 탐색과<br/>
 * 어떤 조건을 만족하는 최소/최대 값의 위치를 O(log N)의 시간복잡도로 탐색하는 파라메트릭 서치<br/>
 *  - 차이점은? 최적화 문제를 결정조건으로 바꾸어 주어진 문제를 해결한다는 점<br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1. 결정 문제로 바꾸자; 해당 요소들이 결정 조건을 만족하는지 이분 탐색 과정 중에 판별하자<br/>
 * 2. 최적화 조건에 맞춰 이분 탐색을 진행하자; <br/>
 *  2-1. 이 때 적절하게 범위를 조절하며 진행하게 됨<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 어떤 조건를 만족하는 가장 큰 수 개념 학습 및 구현<br/>
 * --------------------------------------------------------------<br/>
 */
public class ParametricSearch {
    // 특정 조건보다 가장 큰 수를 찾는 방법 구현
    public void searchBiggestOver(int[] arr, int key) {
        Arrays.sort(arr);
        System.out.println("정렬 후 : " + Arrays.toString(arr));

        int start = 0;
        int end = arr.length;
        int resultIdx = -1;

        while(start < end - 1) { // [start, end)로 해야 무한루프를 돌지 않음
            int mid = (start + end) / 2;
            int value = arr[mid];

            if(key > value) { // key보다 작은 value (조건을 만족하지 않음)
                start = mid + 1;
            } else if(key < value) { // key보다 큰 value (조건 만족, k 이상인 값 True)
                // mid를 포함하여 좁힌다
                // 결정 조건을 만족하더라도 더 큰 값이 있을 수 있으므로;
                start = mid;
            } else {
                resultIdx = mid;
                break;
            }
        }

        if(resultIdx < 0) {
            System.out.println(resultIdx);
            resultIdx = start; // 결정됨
        }

        System.out.printf("key 값을 갖는 인덱스 : %d, 값 : %d\n", resultIdx, arr[resultIdx]);
    }

    public static void main(String[] args) {
        ParametricSearch test01 = new ParametricSearch();
        int[] arr = {1, 4, 5129, 12, 1320, 44, 55, 2, 6, 7, 8};
        test01.searchBiggestOver(arr, 10); // 5129
    }
}
