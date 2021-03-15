package lecture.week1.GreedyAndBrute00;

import java.util.Scanner;

/*
*  2017112095 컴퓨터공학과 최준호
*  누적 평균을 구하는 프로그램
*
* */
public class Assignment1  {
    public static void main(String[] args)  {
        Scanner sc = new Scanner(System.in); // Stream 을 이용하면 실행시간을 단축시킬수있다
        Assignment1 assign = new Assignment1();

        String input="";

        System.out.print("배열을 입력해주세요 :");
        input = sc.nextLine(); // 문자열 전체를 받음

        assign.solution(input);

    }

    public void solution(String str)  {
       /* ArrayList<Integer> arr = new ArrayList<>();*/
        String[] temp;
        int sum=0;  // X[0] + X[1] +...X[i] 의 합을 저장하는 변수
        double result=0.0; // 결과값을 출력할 변수
        /*int result=0; // 출력할 결과값*/
        temp=str.split(" "); // 문자열에서 공백을 기준으로 분리하여 String 배열에 넣어줌 입력받은 문자열의 숫자의 갯수에 맞춰 생성될것

        System.out.println("Avr 출력");
        for(int i =0; i<temp.length; i++){
            sum += Integer.parseInt(temp[i]);
            result = sum / (i+1.0);
            System.out.println("Avr["+i+"]: " + result);
        }
    }
}
