package lecture.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 컴퓨터알고리즘과실습_03 주종화 교수님
 * 과제3 - MergeSorts
 *
 * 2017112095 컴퓨터공학과 최준호
 */
public class MergeSort {
    int[] A_rec = {30, 20, 40, 35, 5, 50, 45, 10, 25, 15};
    int[] A_iter = MyArray.deepCopyArray(A_rec);

    /* 사용자로 부터 입력받는 B,C,D 배열 배열 크기 자체를 입력받지 아니함*/
    int[] B;
    int[] C;
    int[] D;

    /*두가지 정렬을 하기 위해서, 정렬 이전에 각 입력받은 배열을 deep copy 복사함 */
    int[] B_iter;
    int[] C_iter;
    int[] D_iter;


    public static void main(String[] args) throws IOException {

        MergeSort merge = new MergeSort();
        /*ArrayList<Integer> test = new ArrayList<>();*/

        /* 테스트 케이스 : 3회 */
        final int T = 3;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // scanner 보다 빠르게 입력처리 하기 위해
        try {
            /* 한번에 테스트 케이스 만큼 문자열 입력받을 생각 */
            for (int i = 0; i < T; i++) {
                if (i == 0) { // 테스트 케이스가 달라질 경우 각 행에 MergeSort클래스의 객체를 담는 2차원 배열을 생각해볼수도 있을듯.
                    merge.B = MyArray.yourArr(br.readLine());
                    merge.B_iter =MyArray.deepCopyArray(merge.B);
                }
                else if (i == 1) {
                    merge.C = MyArray.yourArr(br.readLine());
                    merge.C_iter =MyArray.deepCopyArray(merge.C);
                }
                else {
                    merge.D = MyArray.yourArr(br.readLine());
                    merge.D_iter =MyArray.deepCopyArray(merge.D);
                    /*test = MyArray.getArray(new StringTokenizer(br.readLine()));*/
                }
            }
            /*test = MyArray.getArray(new StringTokenizer(br.readLine()));*/
            System.out.println("==주어진 배열==");
            MyArray.printArray(merge.A_rec);

            System.out.println("==입력 받은 배열들==");
            MyArray.printArray(merge.B);
            MyArray.printArray(merge.C);
            MyArray.printArray(merge.D);

            /* 정렬 시작 */
            System.out.println("----재귀적 합병 정렬 시작" + "----");
            mergeSortRec(merge.A_rec, 0, merge.A_rec.length - 1);
            System.out.println("문제에서 주어진 배열 정렬 완료");
            for (int i = 0; i < T; i++) {
                if (i == 0) { // 테스트 케이스가 달라질 경우 각 행에 MergeSort클래스의 객체를 담는 2차원 배열을 생각해볼수도 있을듯.
                    System.out.println("----" + (i + 1) + "회차 정렬 시작" + "----");
                    mergeSortRec(merge.B, 0, merge.B.length - 1);
                } else if (i == 1) {
                    System.out.println("----" + (i + 1) + "회차 정렬 시작" + "----");
                    mergeSortRec(merge.C, 0, merge.C.length - 1);
                } else {
                    System.out.println("----" + (i + 1) + "회차 정렬 시작" + "----");
                    mergeSortRec(merge.D, 0, merge.D.length - 1);
                    /*test = MyArray.getArray(new StringTokenizer(br.readLine()));*/
                }
            }

            System.out.println("==재귀 정렬 결과==");
            MyArray.printArray(merge.B);
            for (int i = 0; i < T; i++) {
                if (i == 0) { // 테스트 케이스가 달라질 경우 각 행에 MergeSort클래스의 객체를 담는 2차원 배열을 생각해볼수도 있을듯.
                    System.out.println("----" + (i + 1) + "번 째 입력 정렬 완료" + "----");
                    MyArray.printArray(merge.B);
                } else if (i == 1) {
                    System.out.println("----" + (i + 1) + "번 째 입력 정렬 완료" + "----");
                    MyArray.printArray(merge.C);
                } else {
                    System.out.println("----" + (i + 1) + "번 째 입력 정렬 완료" + "----");
                    MyArray.printArray(merge.D);
                }
            }

            System.out.println("----비재귀 합병 입력 확인" + "----");
            System.out.println("주어진 배열 A, 사용자 입력 B,C,D");
            MyArray.printArray(merge.A_iter);
            MyArray.printArray(merge.B_iter);
            MyArray.printArray(merge.C_iter);
            MyArray.printArray(merge.D_iter);


            System.out.println("==비재귀 정렬 결과==");
            System.out.println("주어진 배열 A 확인");
            MyArray.printArray(merge.A_iter);
            mergeSortIter(merge.A_iter);
            MyArray.printArray(merge.A_iter);
            System.out.println("---주어진 배열 정렬 완료---");

            for (int i = 0; i < T; i++) {
                System.out.println("사용자 입력 배열 확인");
                if (i == 0) { // 테스트 케이스가 달라질 경우 각 행에 MergeSort클래스의 객체를 담는 2차원 배열을 생각해볼수도 있을듯.
                    MyArray.printArray(merge.B_iter);
                    mergeSortIter(merge.B_iter);

                } else if (i == 1) {
                    MyArray.printArray(merge.C_iter);
                    mergeSortIter(merge.C_iter);
                } else {
                    MyArray.printArray(merge.D_iter);
                    mergeSortIter(merge.D_iter);
                }
                System.out.println("----" + (i + 1) + "번 째 입력 정렬 완료" + "----");
            }
            System.out.println("비재귀적 합병정렬 최종 결과");
            MyArray.printArray(merge.A_iter);
            MyArray.printArray(merge.B_iter);
            MyArray.printArray(merge.C_iter);
            MyArray.printArray(merge.D_iter);
        } catch (IOException e) {
            System.out.println("Wrong Input");
            e.printStackTrace();
        }

    }

    /* 재귀적 구현 */
    public static void mergeSortRec(int[] a, int left, int right) {
        /* left : 정렬한 부분의 최소 인덱스, right : 정렬한 부분의 최대 인덱스 */
        int mid; // 중간값
        System.out.println("분할 전 ");
        MyArray.printSubArray(a, left, right);
        if (left < right) {
            mid = (left + right) / 2;
            /*블록1개까지 모두 분할한 후*/
            /* MyArray.printSubArray(a,left,right);*/
            System.out.println("분할 과정 후 ");
            MyArray.printSubArray(a, left, mid);
            MyArray.printSubArray(a, mid + 1, right);

            mergeSortRec(a, left, mid);
            mergeSortRec(a, mid + 1, right);
            /*MyArray.printSubArray(a, left, mid);
            MyArray.printSubArray(a, mid + 1, right);*/


            /*merge*/
            merge(a, left, mid, right);
        }
        /* merge 과정 */
        System.out.println("merge 과정  ");
        MyArray.printSubArray(a, left, right);

    }

    /* 비재귀적 구현 */
    public static void mergeSortIter(int[] a) {
        int N = a.length-1; // 인자로 받은 배열의 크기에서 배열의 마지막 인덱스를 구함.
        int left = 0, right = 0, mid = 0; // 초기화
        for (int i = 1; i < N; i = 2 * i) { // 2의 배수개씩 짝지어, 합병하기 위해서, i는 현재 합병정렬의 크기를 나타냄
            left = 0;  // 힌트 알고리즘과 달리 0부터 시작,
            while (left <= N) { // N보다 크면 안됨.
                right = left + 2 * i - 1;
                if (right > N) // right이 마지막 인덱스보다 크다면,  N으로 고정
                    right = N;
                mid = left + i - 1; //예를들어 3개의 원소끼리 합병하면, i=3인 두 배열 A B C 와 D E F 가 만나 C를  mid로 갖게됨
                if (mid <= N) { // merge할 수 있는 조건,
                    merge(a, left, mid, right);
                    System.out.println("합병 후 ");
                    MyArray.printSubArray(a, left,right);
                }
                left = right + 1; // left는 다음 합병해야하는 인덱스로 이동
            }

        }

    }

    /*merge 호출 될 때, 모든게 분할되어있음, left right값을 고려해야해*/
    public static void merge(int[] a, int left, int mid, int right) {

        /* 하나의 배열이 left와 right로 갈리기 때문에, 다음과 같이 구현함 */
        int left_N = mid - left + 1;
        int right_N = right - mid;

        int[] L = new int[left_N];
        int[] R = new int[right_N];

        /* Left 서브 어레이, right 서브 어레이를 만들어 줌*/
        for (int i = 0; i < left_N; ++i)
            L[i] = a[left + i];
        for (int j = 0; j < right_N; ++j)
            R[j] = a[mid + 1 + j];

        /* Merge left 서브 어레이와 right 서브 어레이 */
        int i = 0, j = 0, k=0; // while문에서 counter 로 쓰기 위해서,

        k = left; // k는 커서 인덱스의 역할을 수행함
        /* 분할된 subArray들을 비교하면서 합치는 과정, */
        while (i < left_N && j < right_N) {
            if (L[i] <= R[j]) {
                a[k++] = L[i++]; // 연산 진행 후 i값 1 증가 , k값 1 증가
            } else {
                a[k++] = R[j++];
            }
        }

        /* 수업시간 공부한 알고리즘의 마지막 for(i=left; i<=right; i++) A[i]=B[i] 부분 */
        while (i < left_N) {
            a[k++] = L[i++]; //연산 진행 후 i값 1 증가 , k값 1 증가
        }
        while (j < right_N) {
            a[k++] = R[j++];
        }
    }
}
