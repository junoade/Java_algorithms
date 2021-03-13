package lecture.trying.week1;

import java.util.Scanner;

/**
 * 컴알골 실습 1주차 문제2_ 2번
 * 2017112095 컴퓨터공학과 최준호
 */
public class Assignment2_1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Assignment2_1 assign = new Assignment2_1();
        int M;
        String input;
        System.out.println("2017112095 컴퓨터공학과 최준호 문제2-2");
        System.out.println("M과 c를 입력해주세요. 입력 예 M (c0,c1,c2,...,cd-1) ");
        //M과 c=(c0,c1,c2,c3,...cd) 입력받음
        M=sc.nextInt();
        sc.nextLine(); // 한번 버퍼를 비워줌

        input = sc.nextLine();
        input = input.substring(input.indexOf("(")+1, input.indexOf(")")); // 이후 괄호 분리됨

        /*System.out.println("input : " +input);
*/
        System.out.println("k 배열의 합 : " + assign.solution(M,input));

    }

    public int solution(int M, String input){
        String[] c = input.split(","); // , 를 기준으로 분리하여 문자열의 배열을 입력만큼 만들고 해당 값으로 초기화함.
        int[] k = new int[c.length];
        for(int i=0; i<c.length; i++){
            k[i]=M/Integer.parseInt(c[i]);
            M=M-Integer.parseInt(c[i])*k[i];
        }
        Assignment2_1.printArray(k);
        return Assignment2_1.sumOfArray(k); // int 형 배열의 합산에 대한 스태틱 메소드의 리턴값을 리턴
    }

    public static int sumOfArray(int[] arr){
        int result=0;
        for (int j : arr)
            result += j;
        return result;
    }
    public static void printArray(int[] arr){
        System.out.print("k=(");
        for(int i=0; i<arr.length; i++){
            if(i!=arr.length-1) // (1,0,1,0,1,0) 이런 포맷으로 출력하고 싶어서
                System.out.print(arr[i]+",");
            else
                System.out.println(arr[i]+")");
        }
    }
}
