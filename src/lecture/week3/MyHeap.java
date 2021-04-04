package lecture.week3;
/*
* 컴퓨터알고리즘과 실습_03 주종화교수님
* Week 3
* 2017112095 컴퓨터공학과 최준호
*
* */

public class MyHeap {
    public static void main(String[] args) {
        int[] A = {4, 1, 3, 2, 16, 9, 10,14,8,7};

        System.out.println("1. 힙생성");
        heapBuild(A, 0,A.length);
        MyArray.printArray(A);
        System.out.println("2. 힙소트");
        heapSort(A,A.length);
        MyArray.printArray(A);
    }

    static void makeHeap(int[] a, int root, int lastNode) {
        int parent, left_child, right_child, child, rootValue;
        parent = root;
        rootValue = a[root];
        left_child = 2 * parent + 1; // 왼쪽 child 노드의 인덱스
        right_child = left_child + 1; // 2*parent+2;

        while (left_child <= lastNode) { // 완전이진트리 성질, 왼쪽 노드부터 채운다는
            if(right_child <= lastNode && a[left_child] < a[right_child]){ // right child의 인덱스가 정상 범위이고, 키값 비교
                child=right_child;
            }else{
                child=left_child; //child에 이 때의 인덱스 값을 할당
            }
            if(rootValue < a[child]){ // rootValue가 그러한 child 노드의 키값 보다 작으면 swap,
                a[parent]=a[child]; // 값을 바꿔줌
                parent=child;
                left_child = parent*2+1;
                right_child=left_child+1;
            }else
                break; // 더 할 필요가 없어서 break;
        }
        a[parent]=rootValue;
    }
    static void heapBuild(int[] a, int root, int N){
        for(int i=N/2; i>=0; i--){
            makeHeap(a,i,N-1);
        }
    }
    static void heapSort(int[] a, int N){
        for(int i=N-1; i>0; i--){
            swap(a,0,i);
            makeHeap(a,0,i-1);
        }
    }
    static void swap(int[] a, int first, int second){ // 일반적인 swap메소드
        int temp=a[first];
        a[first]=a[second];
        a[second]=temp;
    }

}
