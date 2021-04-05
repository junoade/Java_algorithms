package lecture.week4;

public class MyArray {
    final static int Weight=10000;
    final static int N=1000;

    static int[] getRandomArray() {
        /* 주어진 문제에서 1000개의 랜덤한 양의 정수 생성*/
        int[] temp = new int[N];
        for (int i = 0; i < N; i++)
            temp[i] = (int) (Math.random() * Weight) + 1;

        return temp;
    }

    static int[] deepCopy(int[] origin){
        if(origin == null)
            return null;

        int[] temp = new int[origin.length];
        temp=origin.clone();
        return temp;
    }
    static void printArr(int[] origin){
        for(int i:origin)
            System.out.print(i+" ");
        System.out.println();
    }

}
