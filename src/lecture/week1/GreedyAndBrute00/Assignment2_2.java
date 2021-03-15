package lecture.week1.GreedyAndBrute00;

import java.util.Scanner;

/*
 * 컴퓨터알고리즘과실습 _ 주종화교수님
 * 문제2-2 GreedyBetterChange 프로그래밍언어로 구현
 * 2017112095 컴퓨터공학과 최준호
 * */

public class Assignment2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Assignment2_2 assign = new Assignment2_2();
        int M;
        String input;
        long beforeTime =0, afterTime=0;

        System.out.println("2017112095 컴퓨터공학과 최준호 문제2-2");
        System.out.println("M과 c를 입력해주세요. 입력 예 M (c0,c1,c2,...,cd-1) ");
        //M과 c=(c0,c1,c2,c3,...cd) 입력받음
        M = sc.nextInt();
        sc.nextLine(); // 한번 버퍼를 비워줌

        input = sc.nextLine();
        beforeTime = System.currentTimeMillis();

        input = input.substring(input.indexOf("(") + 1, input.indexOf(")")); // 이후 괄호 분리됨

        /*System.out.println("input : " +input);
         */
        System.out.println("k 배열과 합");
        HelpGreedy.printArray(assign.solution(M,input));
        System.out.println(HelpGreedy.sumOfArray(assign.solution(M,input)));

        afterTime = System.currentTimeMillis();
        System.out.println("소요 시간 : " + (afterTime-beforeTime)/1000.0+"ms");
    }

    public int[] solution(int M, String input) {
        String[] c = input.split(","); // , 를 기준으로 분리하여 문자열의 배열을 입력만큼 만들고 해당 값으로 초기화함.
        int[] k = new int[c.length];
        for (int i = 0; i < c.length; i++) {
            k[i] = M / Integer.parseInt(c[i]);
            M = M - Integer.parseInt(c[i]) * k[i];
        }
        /*HelpGreedy.printArray(k);*/
        return k; // int[] 타입의 배열을 리턴하도록 수정
    }

    /*public static int sumOfArray(int[] arr);
    public static void printArray(int[] arr); */

}
