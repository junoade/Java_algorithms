package lecture.week3;

import java.util.ArrayList;
import java.util.StringTokenizer;

class MyArray { // 접근 제한자는 default로 같은 패키지 내에서만 접근 하도록

    /* 사용자의 입력을 받으면 문자열을 분리하여 int[] 배열로 리턴하는 static 메소드*/
    static int[] yourArr(String str) {
        String[] temp = str.split(" "); //사용자가 입력한 문자열의 공백을 기준으로 문자열 배열을 만들고,
        int[] yourArr = new int[temp.length]; // 그 문자열 배열 크기만큼 int[] 배열의 크기를 할당해준다음

        /*for (int i : yourArr)
            i = Integer.parseInt(temp[i]);*/
        for (int i = 0; i < temp.length; i++) {
            yourArr[i] = Integer.parseInt(temp[i]);
        }
        // 그 배열의 원소들로 앞서 만든 문자열 배열들의 값들을 int형으로 바꿔 저장해줌

        return yourArr;
    }

    /*배열을 입력받아 정해진 format으로 출력하는 static 메소드 */
    static void printArray(int[] a) {
        for (int i : a)
            System.out.print(i + " ");
        System.out.println();
    }

    public static void printSubArray(int[] arr, int l, int r) {

        /*System.out.print("[");
        for (int i = l; i < r; i++) {
            if (i != r-1) // (1,0,1,0,1,0) 이런 포맷으로 출력하고 싶어서
                System.out.print(arr[i] + " ");
            else
                System.out.println(arr[i] + "]");
        }*/
        for (int i = l; i <= r; i++)
            System.out.print(arr[i] + " ");
        System.out.println();

    }

    static void printArray(ArrayList<Integer> a) { //메소드 오버로딩
        /*for(Integer i : a)
            System.out.print(a.get(i)+" "); // 아 이러면 i 숫자가 가지는 value을 인덱스에 해당하는 값을 출력하려 하겠구나 */
        for (int i = 0; i < a.size(); i++)
            System.out.print(a.get(i) + " ");
        System.out.println();
    }

    /* 입력 받은 문자열을 Integer타입의 ArrayList 로 반환하는 static 메소드*/
    static ArrayList<Integer> getArray(StringTokenizer st) {
        ArrayList<Integer> temp = new ArrayList<>();
        while (st.hasMoreTokens())
            temp.add(Integer.parseInt(st.nextToken()));

        return temp;
    }
    /*non-Recursive 방법으로 배열 하고 동일한 배열에 대해 Recursive 방법을 사용하도록 깊은 복사를 하는 static 메소드 구현*/
    static int[] deepCopyArray(int[] origin) {
        if (origin == null)
            return null;
        int[] result = new int[origin.length]; //새 배열공간을 메모리에 할당하고,
        result = origin.clone();

        /* 클래스의 객체를 deep copy할 땐, 인스턴스 멤버의 참조변수 역시 새로운 주소값을 할당해줘야함. */
        /* example.
        * public Circle deepCopy(){
        *   Object obj = null;
        *   try{
        *      obj = super.clone();
        *   }catch(CloneNotSupportException e){}
        *   Circle c = (Circle)obj;
        *   c.p = new Point(this.p.x, this.p.y); // 예를 들어, Circle 클래스의 인스턴스 멤버로 Point p; 가 선언되어있다면,
        *   return c;
        * }
        * */
        return result;
    }


}
