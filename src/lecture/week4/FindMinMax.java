package lecture.week4;

/**
 * 컴퓨터알고리즘과 실습
 * 과제4 최소 최댓값 동시 찾기 문제.
 * 2017112095 컴퓨터공학과 최준호
 */
public class FindMinMax {
    int[] A;


    public static void main(String[] args) {
        FindMinMax fmm = new FindMinMax();
        fmm.A = MyArray.getRandomArray(); // 1~10000범위의 1000개의 랜덤한 양의 정수 생성.
        /*fmm.B = MyArray.deepCopy(fmm.A);// 앞서 만든 A 배열에 대해 깊은 복사를 함.*/

        System.out.println("2017112095 컴퓨터 공학과 최준호");
        System.out.println("주어진 랜덤 배열은");
        MyArray.printArr(fmm.A);
        System.out.printf("문제1-1 minimum 함수결과 %d  maximun 함수결과 %d\n", findMin(fmm.A), findMax(fmm.A));
        findMinMax(fmm.A);


    }

    /*문제1-1*/
    static int findMin(int[] arr) {
        int N = arr.length;
        int i, minValue;
        minValue = arr[0];// minValue의 초기화,
        for (i = 0; i < N; i++) {
            if (minValue > arr[i]) // minValue보다 더 작은 배열의 원소가 있다면
                minValue = arr[i]; // 해당 원소의 value값으로 재할당.
        }
        return minValue;
    }

    static int findMax(int[] arr) {
        int N = arr.length;
        int i, maxValue;
        maxValue = arr[0];
        for (i = 0; i < N; i++) {
            if (maxValue < arr[i]) // maxValue보다 더 큰 배열의 원소가 있다면,
                maxValue = arr[i];  // 해당 원소의 value값으로 재할당.
        }
        return maxValue;
    }

    /*문제1-2*/
    static void findMinMax(int[] arr) {
        int N = arr.length;
        int minValue = arr[0], maxValue = arr[1]; //

        int small, large;
        for (int i = 1; i < N - 1; i += 2) { // N-1/2 번
            /* 각 단계에서 작은 값 큰값을 찾음.*/
            if (arr[i] < arr[i + 1]) {
                small = arr[i];
                large = arr[i + 1];
            } else {
                small = arr[i + 1];
                large = arr[i];
            }
            /* 연산 이후, 현재 최솟값보다 더 작다면, 현재 최댓값보다 더 크다면 */
            if (small < minValue)
                minValue = small;
            if (large > maxValue)
                maxValue = large;
        }
        System.out.printf("문제1-2 동시에 찾은 최솟값 %d 최댓값 %d\n", minValue, maxValue);

    }


}
