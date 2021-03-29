package lecture.week2.sorts;

import java.io.IOException;
import java.io.PrintWriter;

public class QuickSorting {
    public static void main(String[] args) throws IOException {
        PrintWriter wr_iter;
        PrintWriter wr_rec;

        QuickSorting qs = new QuickSorting();


        String filePath1 = "C:\\Users\\ajcho\\Desktop\\output\\quick_sort\\Quick_result_iter.txt";
        String filePath2 = "C:\\Users\\ajcho\\Desktop\\output\\quick_sort\\Quick_time_result_rec.txt";
        wr_iter = MyArray.openPrintWriter(filePath1);
        wr_rec = MyArray.openPrintWriter(filePath2);

        long beforeTime, afterTime;
        double resultTime;
        int execute = 1;

        System.out.println("2017112095 컴퓨터공학과 최준호_ QuickSort");
        while (execute < 5) {

            int N = (int) Math.pow(10, execute); // 각 시행마다 배열의 사이즈를 달리하도록 10^1 , 10^2, 10^3, 10^4
            int[] arr_nonR = MyArray.getArray(N);
            int[] arr_Rec = MyArray.deepCopyArray(arr_nonR); // 그냥 대입 연산자 사용하면 얕은 복사가 됨.

            /* Iterative Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.nanoTime();
            qs.nonRecursiveQuickSort(arr_nonR, 0,N-1); //정렬 알고리즘 구현 메소드 호출
            afterTime = System.nanoTime();
            resultTime = (afterTime - beforeTime) / 1000000000.0; //ms
            wr_iter.printf("%d %d %f\n", execute, N, resultTime); // 수행 횟수 배열크기 실행시간
            wr_iter.flush();
            System.out.printf("%d회차 %-3s %d %f\n", execute,"비재귀", N, resultTime); //콘솔창 출력

            /*Recursive Bubble Sort 시간 측정 및 호출 */
            beforeTime = System.nanoTime();
            qs.RecursiveQuickSort(arr_Rec,0,N-1); //정렬 알고리즘 구현 메소드 호출
            afterTime = System.nanoTime();
            resultTime = (afterTime - beforeTime) / 1000000000.0;
            //MyArray.writeTimeResult(filePath2,execute,resultTime);
            wr_rec.printf("%d %d %f\n", execute, N, resultTime);
            wr_rec.flush();
            System.out.printf("%d회차 %-3s %d %f\n", execute," 재귀", N, resultTime); //콘솔창 출력

            /*콘솔창에서 정렬 확인용 */
            MyArray.printArray(arr_nonR);
            MyArray.printArray(arr_Rec);

            /* 정렬 결과 파일 출력*/
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\quick_sort\\Quick_Iterative" + execute + ".txt", arr_nonR);
            MyArray.printOutput("C:\\Users\\ajcho\\Desktop\\output\\quick_sort\\Quick_Recursive" + execute + ".txt", arr_Rec);

            execute++;
        }
        wr_iter.close();
        wr_rec.close();
    }

    /* partition 부분 동일, */
    public void nonRecursiveQuickSort(int[] arr, int from, int to) {
        int[] stack = new int[to-from+1]; //배열 크기 N인 사이즈 할당
        int top = -1;
        stack[++top] = from; // stack[0]=0
        stack[++top] = to; //stack[1]=to;

        while(top>=0){
            to = stack[top--];//
            from = stack[top--];
            //pivot 설정, 맨끝값으로
            int pivot = partition(arr,from,to);

            if(pivot -1 > from){ // 좌측 partition
                stack[++top] = from;
                stack[++top] = pivot-1;
            }

            //우측 partiton
            if(pivot+1<to){
                stack[++top] = pivot+1;
                stack[++top] = to;
            }
        }

    }

    /* 핵심 : Pivot을 잡고 왼쪽은 Pivot 보다 작은 값들, 오른쪽은 Pivot 보다 큰 값들에 대해 재귀적으로 풀이*/
    public void RecursiveQuickSort(int[] arr, int from, int to) {

        if (from >= to) // index named from 가 to 보다 큰 경우 이미 정렬 완료.
            return;

        int pivot = partition(arr, from, to);

        /* recursive 하게,*/
        //pivot의 좌측
        RecursiveQuickSort(arr, from, pivot-1);
        RecursiveQuickSort(arr, pivot+1, to);

    }

    public static int partition(int[] arr, int from, int to) {

        int pivot = arr[to]; //배열의 끝 부분의 키값을 pivot으로 잡는다.
        int counter = from; // swap이 일어난 자리를 저장하는 counter

        /* partition 부분 */
        for (int i = from; i < to; i++) {
            if (arr[i] > pivot) {
                swap(arr, i, counter);
                counter++;
            }

        }
        /* partition 이후 */
        swap(arr, to, counter); // 끝 부분과 counter가 가르키는 키값을 swap
        return counter;
    }

    public static void swap(int[] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }
}
