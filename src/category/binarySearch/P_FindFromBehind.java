package category.binarySearch;

import java.util.Arrays;

/**
 * --------------------------------------------------------------<br/>
 * <b>P 뒤에 있는 큰 수 찾기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 이진 탐색 - 파라메트릭 서치; 조건을 만족하는 수 중 가장 작은 위치를 찾는 방법을 고민해봄<br/>
 * Q1. 이진 탐색의 기본은 정렬 후 O(log N) 으로 탐색하는 건데, numbers 배열은 정렬하면 안될 것 같다. 어떡하지?<br/>
 * Q2. 정렬을 안해보고 왼쪽, 오른쪽의 값들을 모두 찾아 반환해봤다! 그런데 시간 초과 문제가 있다.
 * --------------------------------------------------------------<br/>
 * <b> 채점 30.4 / 100</b><br/>
 * --------------------------------------------------------------
 */
public class P_FindFromBehind {
    public int[] solution(int[] numbers) {
        final int L = numbers.length;
        int[] answer = new int[L];

        // O(N (log N + log N));
        // 1200만 (?)
        for(int i = 0; i < L; i++) {
            int value = numbers[i];
            answer[i] = binarySearch(numbers, value, i + 1, L - 1);
        }

        return answer;
    }

    int binarySearch(int[] numbers, int key, int start, int to) {

        // base case #1
        if(start > to) {
            return -1;
        }

        // base case #2 : 1개만 남았을 때
        if (start == to) {
            return isValid(key, numbers[start]) ? numbers[start] : -1;
        }

        // base case #3 : 2개만 남았을 때
        // 조건을 만족하는 min을 반환
        if (to - start == 1) {
            int lastLeft = numbers[start];
            int lastRight = numbers[to];
            if(!isValid(key, lastLeft) && !isValid(key, lastRight)) {
                return -1;
            }

            if(isValid(key, lastLeft)) {
                return lastLeft;
            }

            if(isValid(key, lastRight)) {
                return lastRight;
            }
        }

        // general case
        int target1 = 0, target2 = 0;
        int mid = (start + to) / 2 ;
        boolean cond = isValid(key, numbers[mid]);
        target1 = binarySearch(numbers, key, start, mid);
        target2 = binarySearch(numbers, key, mid, to);
        if(target1 == -1 && target2 == -1) {
            return -1;
        }
        if(target1 != -1) {
            return target1;
        }
        return target2;
    }

    boolean isValid(int n1, int n2) {
        return n1 < n2;
    }

    public static void main(String[] args) {
        P_FindFromBehind test = new P_FindFromBehind();
        int[] arr1 = new int[]{2, 3, 3, 5};
        int[] arr2 = new int[]{9, 1, 5, 3, 6, 2};
        int[] arr3 = new int[]{2, 1, 3, 1, 4, 5, 6};

        System.out.println(Arrays.toString(test.solution(arr3)));
    }
}
