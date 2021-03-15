package baekjoon.problems.silver;

import java.util.Scanner;

/**
 * bubble sort
 */
public class BasicSorting2750_bubble {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N;
        int[] arr;
        N = sc.nextInt();

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        /*HelpGreedy.printArray(arr);*/

        solution(arr);

    }

    static void solution(int[] arr) {
        int temp = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) { //j=i+1; 이면 다르게 반대로 내림차순정렬하네
                /* swap*/
                if (arr[j] > arr[i]) {
                    temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        for (int i : arr)
            System.out.println(i);

    }
}
