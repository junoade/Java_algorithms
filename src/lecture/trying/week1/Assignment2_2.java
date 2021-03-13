package lecture.trying.week1;

import java.util.Scanner;

public class Assignment2_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M;
        String input;
        System.out.println("2017112095 컴퓨터공학과 최준호 문제2-2");
        System.out.println("M과 c를 입력해주세요. 입력 예 M (c0,c1,c2,...,cd-1) ");
        //M과 c=(c0,c1,c2,c3,...cd) 입력받음
        M=sc.nextInt();
        sc.nextLine(); // 한번 버퍼를 비워줌

        input = sc.nextLine();
        input = input.substring(input.indexOf("(")+1, input.indexOf(")")); // 이후 괄호 분리됨


    }
    public int solution(int M, String input){
        int[] c = convertInt(input.split(",")); // 동전 화폐단위 문자열 배열을 int형 배열로 변환해줌
        int d= c.length;
        int[] k = new int[d];

        int smallestNumberOfCoins = Integer.MAX_VALUE; // int형의 최대값을 넣어줌
        int valueOfCoins=0;

        /*for(int i=0; i<d; i++){ // for each ( k1,k2,...,kd )
            for(int j=0; j<d; j++){
                k[i]=M/Integer.parseInt(c[j]);
                M=M-Integer.parseInt(c[j])*k[j];
            }
        }*/
        return 0;
    }

    /* 매번 Integer.parseInt 호출하면 성능이 안좋을것 같아서 문자열 배열 c를 한번에 int[] 로 바꿔놓기 위한 스태틱 메소드 */
    public static int[] convertInt(String[] str){
        int[] result = new int[str.length];
        for(int i=0; i<str.length; i++){
            result[i]=Integer.parseInt(str[i]);
        }
        return result;
    }
}
