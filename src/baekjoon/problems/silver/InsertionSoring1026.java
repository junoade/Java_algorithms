package baekjoon.problems.silver;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 길이가 N인 정수 배열 A와 B가 있다. 다음과 같이 함수 S를 정의하자.
 * <p>
 * S = A[0]×B[0] + ... + A[N-1]×B[N-1]
 * <p>
 * S의 값을 가장 작게 만들기 위해 A의 수를 재배열하자. 단, B에 있는 수는 재배열하면 안 된다.
 * <p>
 * S의 최솟값을 출력하는 프로그램을 작성하시오.
 * <p>
 * Greedy?
 * 아 A배열의 최솟값 x B의 배열의 최댓값
 */
public class InsertionSoring1026 {

    static int solution(int[] A, int[] B, int N) {
        //A에서 최소값 찾아서 B랑 곱함
        int temp = 0, S = 0;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                /* 정렬 안된 부분의 min 찾기*/
                if (A[j] < A[min])
                    min = j;
            }
            /* swap */
            temp = A[i];
            A[i] = A[min];
            A[min] = temp;
        }

        /* B배열에서의 최댓값을 가지는 index 순서 찾기*/
        ArrayList<Integer> idx = new ArrayList<>();
        for (int i = 0; i < N; i++)
            idx.add(i);

        int[] idx_maxB = new int[N];

        for (int h=0; h<N; h++) {
            int del_i=0;
            int max=B[idx.get(0)];
            for (int i=0; i < idx.size(); i++) {
                if (max<=B[idx.get(i)] ) {
                    idx_maxB[h]=idx.get(i);
                    max=B[idx.get(i)];
                    del_i=i;
                }
            }
            if(idx.size()==1){ //i
                idx_maxB[h]=idx.get(0);
                del_i=0;
            }
            if(!idx.isEmpty())
                idx.remove(del_i); // 여기서 문제 가능성 //idx_maxB[h] 라면 h가 idx의 사이즈보다 커져버려
        }

        /* 그 다음 sum 구해 주기*/
        for(int i=0; i<N; i++)
            S+=A[i]*B[idx_maxB[i]];
        /* 3rd Try*/
        /*for (int h=0; h<N; h++) {
            *//*int max=0;*//*
            int del_i=0;
            for (int i=0; i < idx.size()-1; i++) {
                if (B[idx.get(i)] < B[idx.get(i+1)]) {
                    idx_maxB[h]=idx.get(i+1);
                    del_i=i+1;
                }


            }
            if(idx.size()==1){ //i
                idx_maxB[h]=idx.get(0);
                del_i=0;
            }
            if(!idx.isEmpty())
                idx.remove(del_i); // 여기서 문제 가능성 //idx_maxB[h] 라면 h가 idx의 사이즈보다 커져버려
        }*/
        /* 2nd Try
        int[] idx = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (i == 0) {
                    if (B[j] < B[j + 1]) {
                        idx_maxB[i] = j + 1;
                    }
                } else {

                    if (B[j] < B[j + 1]) {
                        if (idx_maxB[i - 1] != j) {
                            idx_maxB[i] = j + 1;
                        }
                    }
                }
            }
        }*/

        /* 1st Try
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) { //j=i+1; 이면 다르게 반대로 내림차순정렬하네
                *//* swap*//*
                if (B[j] < B[i]) { //내림차순 정렬
                    *//*temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;*//*
                    temp=i;
                    idx_maxB[j]=idx_maxB[i];
                    idx_maxB[i]=temp;

                }
            }
        }*/


        /*B배열에서의 최댓값을 가지는 index 찾기*/
        /*for (int k = 0; k < N; k++) {
            int[] best_B = new int[N];
            int idx_temp=0;
            for (int i = 0; i < N; i++) {
                int max = i;
                for (int j = i + 1; j < N; j++) {
                    if (B[j] > B[max]) // i+1 인 j에 해당하는 값이 현재 max인덱스의 값보다 크다면
                        max = j;

                }
                best_B[i]=max;

                *//*내림차순으로 인덱스를 정렬 한 max의 인덱스를 best_B에 저장*//*
         *//*best_B[i]=i;*//*

            }
            System.out.println(A[k]+" * "+B[best_B[k]]+"="+A[k]*B[best_B[k]]);
            S+=A[k]*B[best_B[k]];
        }*/
        return S;
    }

    public static void main(String[] args) {
        int N;
        int[] A;
        int[] B;

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        B = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = sc.nextInt();
        for (int i = 0; i < N; i++)
            B[i] = sc.nextInt();

        /* 호출 */
        System.out.println(solution(A, B, N));
    }
}
