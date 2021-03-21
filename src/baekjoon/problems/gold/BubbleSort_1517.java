package baekjoon.problems.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BubbleSort_1517 {
    public static void main(String[] args) throws IOException {
        int[] arr;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());


        System.out.println(bubblesort(arr));

    }

    public static int bubblesort(int[] arr) {
        final int N=arr.length;
        int result=0;
        for(int i=0; i<N; i++){
            for (int j = 0; j < N - i - 1; j++) {
                if (arr[j] < arr[j + 1]) { //앞 인덱스의 배열값이 더 크면, swap
                   swap(arr,j,j+1);
                   result++;
                }
            }
        }
        return result;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
}