package lecture.week2.sorts;

/**
 * 과제를 수행함에 있어서
 */
public class MyArray {
    final static int N=10;
    final static int weight =1000000;
    static int[] arr = new int[N];

    static int[] getArray(){
        for(int i=0; i<N; i++){
            arr[i]=(int)(Math.random()*weight)+1;
        }
        /*for(int i: arr)
            i=(int)(Math.random()*weight)+1; 값은 직접 바꿀수 없어*/
        return arr;
    }
    static void printArray(int[] a){
        for(int i: a)
            System.out.print(i+" ");
        System.out.println();
    }
    static int[] deepCopyArray(int[] origin){
        if(origin == null)
            return null;
        int[] result = new int[origin.length]; //새 배열공간을 메모리에 할당하고,
        result = origin.clone();
        return result;
    }
}
