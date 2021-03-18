package lecture.week2.sorts;

public class BubbleSorting {
    public static void main(String[] args) {
        int[] arr_nonR = MyArray.getArray();
        int[] arr_Rec = MyArray.deepCopyArray(arr_nonR); // 그냥 대입 연산자 사용하면 얕은 복사가 됨.

        MyArray.printArray(arr_nonR);
        MyArray.printArray(arr_Rec);

    }
}
