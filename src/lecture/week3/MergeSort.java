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
 * <p>
 * 2017112095 컴퓨터공학과 최준호
 */
public class MergeSort {
    int[] A = {30, 20, 40, 35, 5, 50, 45, 10, 25, 15};

    /* 사용자로 부터 입력받는 B,C,D 배열 배열 크기 자체를 입력받지 아니함*/
    int[] B;
    int[] C;
    int[] D;

    public static void main(String[] args) throws IOException {

        MergeSort merge = new MergeSort();
        ArrayList<Integer> test = new ArrayList<>();

        /* 테스트 케이스 : 3회 */
        final int T = 3;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // scanner 보다 빠르게 입력처리 하기 위해
        try {
            /* 한번에 테스트 케이스 만큼 문자열 입력받을 생각 */
            for (int i = 0; i < T; i++) {
                if (i ==0) // 테스트 케이스가 달라질 경우 각 행에 MergeSort클래스의 객체를 담는 2차원 배열을 생각해볼수도 있을듯.
                    merge.B = MyArray.yourArr(br.readLine());
                else if(i==1)
                    merge.C = MyArray.yourArr(br.readLine());
                else {
                    merge.D = MyArray.yourArr(br.readLine());
                    test=MyArray.getArray(new StringTokenizer(br.readLine()));
                }
            }
            System.out.println("==입력 받은 배열들==");
            /*MyArray.printArray(merge.B);
            MyArray.printArray(merge.C);
            MyArray.printArray(merge.D);
            System.out.println(test.toString());
            MyArray.printArray(test);*/

        }catch(IOException e){
            System.out.println("Wrong Input");
            e.printStackTrace();
        }

    }
    /* 재귀적 구현 */
    public static void mergeSortRec(){

    }
    /* 비재귀적 구현 */
    public static void mergetSortIter(){

    }
}
