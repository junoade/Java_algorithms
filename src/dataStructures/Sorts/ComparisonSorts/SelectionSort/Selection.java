package dataStructures.Sorts.ComparisonSorts.SelectionSort;

/**
 * Selection Sort is..
 * The selection sort algorithm sorts an array by repeatedly finding the minimum element (considering ascending order) 
 * from unsorted part and putting it at the beginning.
 * The algorithm maintains two subarrays in a given array.
 * 
 * 아직 정렬되지 않은 부분의 원소들 중에서 최솟값을 '선택'하고 
 * 정렬된 부분의 오른쪽 원소(오름차순정렬시)와 교환하는 간단한 정렬 알고리즘
 */
public class Selection {
    public static void sort(Comparable[] a ){
        int N=a.length;

        for(int i=0; i<N; i++){
            int min=i; // 탐색용 최솟값 인덱스,
            for(int j=i+1; j<N; j++){ // j=i+1 !!
               /* if(a[j]<a[min]) min=j;*/
                if(isLess(a[j],a[min])) // a[j] < a[min] 일 때 true
                    j=min;
            }//최솟값을 가르키는 인덱스가 결정됨
            swap(a, i,min); // 정렬되지 않은 쪽의 min과 i 인덱스의 배열값을 swap
        }
    }

    public static boolean isLess(Comparable i, Comparable j){
        return (i.compareTo(j) <0); // i.compareTo(j)의 결과값  i<j 면 -1 i=j 면 0 i>j 면 1
    }
    public static void swap(Comparable[] a, int i, int j){
        Comparable temp= a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
