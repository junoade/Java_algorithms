package lecture.week1.GreedyAndBrute00;

/*
 * 주어진 과제를 수행함에 있어서
 * static 메소드들을 재활용하기 위해 만든
 * 클래스
 * 2017112095 컴퓨터공학과 최준호
 * */

public class HelpGreedy {




    /* 유저로 부터 입력받은 문자열에서 문자열의 시작을 의미하는 ( 와 끝을 의미하는 ) 을 제거해주는 static 메소드 */
    public static String trimInput(String input) {
        return input.substring(input.indexOf("(") + 1, input.indexOf(")"));
    }

    /* 매번 Integer.parseInt 호출하면 성능이 안좋을것 같아서 문자열 배열 c를 한번에 int[] 로 바꿔놓기 위한 스태틱 메소드 */
    public static int[] convertIntArr(String[] str) {
        int[] result = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            result[i] = Integer.parseInt(str[i]);
        }
        return result;
    }

    /* int[] 타입의 요소들의 합을 리턴하는 static 메소드 */
    public static int sumOfArray(int[] arr) {
        int result = 0;
        for (int j : arr)
            result += j;
        return result;
    }

    /* int[] 타입의 배열을 인자로 받고 그 요소들을 특정한 포맷으로 출력해주는 static 메소드 */
    public static void printArray(int[] arr) {
        System.out.print("k=(");
        for (int i = 0; i < arr.length; i++) {
            if (i != arr.length - 1) // (1,0,1,0,1,0) 이런 포맷으로 출력하고 싶어서
                System.out.print(arr[i] + ",");
            else
                System.out.println(arr[i] + ")");
        }
    }

}
