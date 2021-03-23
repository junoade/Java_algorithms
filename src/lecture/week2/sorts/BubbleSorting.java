package lecture.week2.sorts;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 컴알골 실습2
 * 문제2. 버블 정렬 함수를 비재귀 재귀 형식으로 모두 구현하고 각각을 이용해 배열N을 재배열 시켜보시오.
 * <p>
 * 2017112095 컴퓨터공학과 최준호
 */
public class BubbleSorting {
    public static void main(String[] args) throws IOException {
        PrintWriter wr_iter;
        PrintWriter wr_rec;

        BubbleSorting bs = new BubbleSorting();

        String filePath1 = "C:\\Users\\ajcho\\Desktop\\output\\bubble_sort\\Bubble_time_result_iter.txt";
        String filePath2 = "C:\\Users\\ajcho\\Desktop\\output\\bubble_sort\\Bubble_time_result_rec.txt";
        wr_iter = MyArray.openPrintWriter(filePath1);
        wr_rec = MyArray.openPrintWriter(filePath2);

        long beforeTime = 0, afterTime = 0;
        double resultTime = 0.0;
        int execute = 1;
        System.out.println("2017112095 컴퓨터공학과 최준호_ Bubble Sort");
        while (execute < 5) {

            int N = (int) Math.pow(10, execute); // 각 시행마다 배열의 사이즈를 달리하도록 10^1 , 10^2, 10^3, 10^4
            int[] arr_nonR = MyArray.getArray(N);
            int[] arr_Rec = MyArray.deepCopyArray(arr_nonR); // 그냥 대입 연산자 사용하면 얕은 복사가 됨.

            /* Iterative Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.currentTimeMillis();
            bs.nonRecursive(arr_nonR, N);
            afterTime = System.currentTimeMillis();
            resultTime = (afterTime - beforeTime) / 1000.0; //ms
            wr_iter.printf("%d %d %f\n", execute, N, resultTime); // 수행 횟수 배열크기 실행시간
            wr_iter.flush();
            System.out.printf("%d회차 %-3s %d %f\n", execute,"비재귀", N, resultTime); //콘솔창 출력

            /*Recursive Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.currentTimeMillis();
            bs.Recursive(arr_Rec, N);
            afterTime = System.currentTimeMillis();
            resultTime = (afterTime - beforeTime) / 1000.0;
            //MyArray.writeTimeResult(filePath2,execute,resultTime);
            wr_rec.printf("%d %d %f\n",execute, N, resultTime);
            wr_rec.flush();
            System.out.printf("%d회차 %-3s %d %f\n", execute," 재귀", N, resultTime); //콘솔창 출력

            /*콘솔창에서 정렬 확인용 */
            MyArray.printArray(arr_nonR);
            MyArray.printArray(arr_Rec);

            /* 정렬 결과 파일 출력*/
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\bubble_sort\\Bubble_Iterative"+execute+".txt",arr_nonR);
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\bubble_sort\\Bubble_Recursive"+execute+".txt", arr_Rec);

            execute++;
        }
        wr_iter.close();
        wr_rec.close();
    }

    public void nonRecursive(int[] arr, int N) {
        int temp = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (arr[j] < arr[j + 1]) { // swap //내림차순 정렬
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void Recursive(int[] arr, int n) {

        int temp = 0;
        if (n == 1)  //base 케이스는? 배열의 크기가 1일때,
            return;

        for (int i = 0; i < n - 1; i++) {
            if (arr[i] < arr[i + 1]) { //swap //내림차순 정렬
                temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        Recursive(arr, n - 1);

    }

}
