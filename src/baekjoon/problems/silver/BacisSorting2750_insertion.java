package baekjoon.problems.silver;

import lecture.week1.GreedyAndBrute00.HelpGreedy;

import java.util.Scanner;

/**
 * 2021-03-16
 * 수 정렬하기 2750 번 - insertion sorting
 * Junho Choi
 */
public class BacisSorting2750_insertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N;
        int[] arr;
        N = sc.nextInt();

        arr=new int[N];
        for(int i=0; i<N; i++){
            arr[i]=sc.nextInt();
        }
        /*HelpGreedy.printArray(arr);*/

        solution(arr);

    }

    static void solution(int[] arr){
        int temp=0;
        
        for(int i = 0; i<arr.length;i++) { //정렬 안된 부분
            int min=i; // 최소값

            for (int j = i+1; j < arr.length; j++) {
                /* 정렬 안된 부분의 min 찾기*/
                if(arr[j]<arr[min])
                    min=j;

            }
            /* swap */

            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;


        }
        for(int i : arr)
            System.out.println(i);

    }
}
