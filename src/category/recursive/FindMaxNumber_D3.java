package category.recursive;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * --------------------------------------------------------------<br/>
 * <b> 최대 상금 - 삼성 SW, D3 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * - 완전 탐색, 재귀를 이용한 DFS 방식 구현
 * - 이미 지나간 i는 지나가도록 startIdx 를 별도로 재귀함수로 전달하여 시간 초과를 줄인다.
 * - if(arr[i] <= arr[j]) 조건을 넣으면 11/15점을 받는다.
 * ->
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class FindMaxNumber_D3 {
    static int max;

    static void solution(String input, int swap) {
        // String -> Integer[ ] 변환
        Integer[] arr = input.chars().mapToObj(c -> (int) c - '0').toArray(Integer[]::new);
        max = Integer.MIN_VALUE;
        // swap
        swap_rec(arr, swap, 0);
    }

    static void swap_rec(Integer[] arr, int swap, int startIdx) {
        // base case
        if (swap == 0) {
            int currentValue = convertArrayToInt(arr);
            if (currentValue > max) {
                max = currentValue;
            }
        } else {
            // swap 진행 하고 swap count -1 해서 전달
            exhaustiveSearch(arr, swap, startIdx);
        }
    }

    static void exhaustiveSearch(Integer[] arr, int swap, int startIdx) {
        for (int i = startIdx; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // if (arr[i] <= arr[j]) { // 의 경우 11/15
                // (81 , 1)의 경우 swap을 할수가 없다.
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                swap_rec(arr, swap - 1, i); // 0부터 모든 N^2 에 대해 탐색하는게 아닌 앞으로 탐색해야할 i에 대해 j 반복 수행
                arr[j] = arr[i];
                arr[i] = temp;

            }
        }
    }

    /**/
    static int convertArrayToInt(Integer[] arr) {
        StringBuilder sb = new StringBuilder();
        for (Integer element : arr) {
            sb.append(element);
        }
        return Integer.parseInt(sb.toString());
    }

    /* 더 오래 걸림 */
    static int convertArrayToInt2(Integer[] arr) {
        return Integer.parseInt(Arrays.stream(arr).map(String::valueOf).collect(Collectors.joining("")));
    }


    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        /* 여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.*/
        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            /* 이 부분에 여러분의 알고리즘 구현이 들어갑니다. */
            String[] temp = br.readLine().split(" ");
            String input = temp[0];
            int swap = Integer.parseInt(temp[1]);
            // swap이 최대 입력 길이보다 긴 경우 최대 입력 길이로 변환
            if (swap > input.length()) {
                swap = input.length();
            }
            solution(input, swap);
            System.out.printf("#%d %d\n", test_case, max);
            /////////////////////////////////////////////////////////////////////////////////////////////

        }
    }
}
