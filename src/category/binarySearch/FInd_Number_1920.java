package category.binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1920 번 수찾기
 * 유형 : 문자열, 숫자 배열 탐색, 이진 탐색 O(log N)
 *
 * 요약) N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.
 *
 * 풀이
 * - 입력 범위와 시간을 보아
 * - 예제 입력에서 입력으로 찾는 대상 리스트 개수, 리스트, 찾을 숫자 리스트 개수 찾을 숫자 리스트를 받는다.
 */
public class FInd_Number_1920 {

    public static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로 토큰으로 입력
        for(int i = 0; i<arr.length; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        /* step1) 이진탐색을 하게 될 arr는 오름차순 또는 내림차순으로 정렬되어 있어야함 */
        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " "); // 공백을 구분자로, int[] arr에서 찾을 값들을 받음
        StringBuffer sb = new StringBuffer(); // 출력 성능을 위해

        /*for(int i = 0; i<M; i++){
            *//* 찾았을 경우 *//*
            if(binary_search_iter(Integer.parseInt(st.nextToken()))){
                sb.append(1);
            }else{
                sb.append(0);
            }
            if(i < M-1){
                sb.append("\n");
            }
        }
        System.out.println(sb);
        */

        /* recursive */
        for(int i = 0; i<M; i++){
            /* 찾았을 경우 */
            if(binary_search_rec(0, arr.length-1, Integer.parseInt(st.nextToken()))){
                sb.append(1);
            }else{
                sb.append(0);
            }
            if(i < M-1){
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    /**
     * static 형 클래스 변수 배열 int[] arr 에 접근한다.
     * @param key
     * @return
     */
    public static boolean binary_search_iter(int key){

        int left = 0;
        int right = arr.length - 1;

        while(left <= right){
            int mid = (left + right)/2; // bit 연산을 활용하여 (left+right)>>>1를 할수있다.
            if(arr[mid] == key){
                return true;
            }else if(arr[mid] < key){
                left = mid + 1;
            }else{ // arr[mid] > key
                right = mid - 1;
            }
        }
        /* 못 찾았을 때 */
        return false;
    }

    public static boolean binary_search_rec(int left, int right, int key){
        /* base case */
        if(left>right)
            return false;

        int mid = (left +right)/2;

        if(arr[mid] == key){
            return true;
        }else if(arr[mid] < key){
            return binary_search_rec(mid+1, right, key);
        }else{
            return binary_search_rec(left, mid-1, key);
        }
    }
}
