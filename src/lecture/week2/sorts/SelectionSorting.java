package lecture.week2.sorts;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 컴퓨터알고리즘과실습
 * 실습2 - 선택정렬 함수를 비재귀 재귀 형식으로 모두 구현하고 각각을 이용해 배열N을 재배열 시켜보시오
 * 2017112095 컴퓨터공학과 최준호
 */
public class SelectionSorting {
    public static void main(String[] args) throws IOException {
        PrintWriter wr_iter;
        PrintWriter wr_rec;

        SelectionSorting ss = new SelectionSorting();

        String filePath1 = "C:\\Users\\ajcho\\Desktop\\output\\selection_sort\\Selection_result_iter.txt";
        String filePath2 = "C:\\Users\\ajcho\\Desktop\\output\\selection_sort\\Selection_time_result_rec.txt";
        wr_iter = MyArray.openPrintWriter(filePath1);
        wr_rec = MyArray.openPrintWriter(filePath2);

        long beforeTime, afterTime;
        double resultTime;
        int execute = 1;
        while (execute < 5) {

            int N = (int) Math.pow(10, execute); // 각 시행마다 배열의 사이즈를 달리하도록 10^1 , 10^2, 10^3, 10^4
            int[] arr_nonR = MyArray.getArray(N);
            int[] arr_Rec = MyArray.deepCopyArray(arr_nonR); // 그냥 대입 연산자 사용하면 얕은 복사가 됨.

            /* Iterative Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.currentTimeMillis();
            ss.nonRecursive(arr_nonR, N); //정렬 알고리즘 구현 메소드 호출
            afterTime = System.currentTimeMillis();
            resultTime = (afterTime - beforeTime) / 1000.0; //ms
            wr_iter.printf("%d %d %f\n", execute, N, resultTime); // 수행 횟수 배열크기 실행시간
            wr_iter.flush();

            /*Recursive Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.currentTimeMillis();
            ss.Recursive(arr_Rec,0,N); //정렬 알고리즘 구현 메소드 호출
            afterTime = System.currentTimeMillis();
            resultTime = (afterTime - beforeTime) / 1000.0;
            //MyArray.writeTimeResult(filePath2,execute,resultTime);
            wr_rec.printf("%d %d %f\n", execute, N, resultTime);
            wr_rec.flush();

            /*콘솔창에서 정렬 확인용 (데이터건수가 많아지면 콘솔창 출력만 한세월일듯..*/
            MyArray.printArray(arr_nonR);
            MyArray.printArray(arr_Rec);

            /* 정렬 결과 파일 출력*/
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\selection_sort\\Selection_Iterative" + execute + ".txt", arr_nonR);
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\selection_sort\\Selection_Recursive" + execute + ".txt", arr_Rec);

            execute++;
        }
        wr_iter.close();
        wr_rec.close();
    }

    public void nonRecursive(int[] arr, int N) { // 비재귀 선택정렬 알고리즘 구현
        int min = 0, temp = 0;
        for (int i = 0; i < N - 1; i++) {
            min = i; // 최솟값을 갖는 인덱스 i를 지정
            for (int j = i + 1; j < N; j++) {
                if (arr[j] < arr[min]) { //현재 index j가 갖는 값이 arr[min]보다 더 작은 경우,
                    min = j; // 인덱스 min을 j로 바꿔줌
                }
            }
            //그다음 swap
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    public void Recursive(int[] arr, int i, int n) {
        int min = i, temp = 0;
        for (int j = i + 1; j < n; j++) {
            if (arr[j] < arr[min]) {
                min = j;
            }
        }
        //swap
        temp=arr[i];
        arr[i]=arr[min];
        arr[min]=temp;

        if(i+1<n)
            Recursive(arr,i+1,n);

    }
}
