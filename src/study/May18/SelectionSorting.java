package study.May18;

public class SelectionSorting {

    public void nonRecursive(int[] arr, int N) { // 비재귀 선택정렬 알고리즘 구현 // 내림차순 정렬하기
        int min= 0, temp = 0;
        for (int i = 0; i < N - 1; i++) {
            min = i; // 최솟값을 갖는 인덱스 i를 지정
            for (int j = i + 1; j < N; j++) {
                if (arr[j] > arr[min]) { //현재 index j가 갖는 값이 arr[max]보다 더 큰 경우, // 내림차순
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
            if (arr[j] > arr[min]) {
                min = j;
            }
        }
        //swap
        temp=arr[i];
        arr[i]=arr[min];
        arr[min]=temp;

        if(i+1<n) // 배열의 길이보다 최댓값 인덱스가 작을때,
            Recursive(arr,i+1,n);

    }
}
