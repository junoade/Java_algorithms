package category.simulation.samsung;

import java.util.Scanner;
import java.io.FileInputStream;

/**
 * 삼성 D1~D2 간단한 369 문제 
 * 자바 1.8의 stream 기능을 이용해서, count() 메소드를 구현하여
 * 보다 간편하게 결과 도출
 */
public class Simple369 {

    public static void main(String args[]) throws Exception {
        
        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/
        StringBuilder answer = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            String temp = String.valueOf(test_case);
            int count = 0;
            if (temp.contains("3") || temp.contains("6") || temp.contains("9")) {
                count += count(temp, '3');
                count += count(temp, '6');
                count += count(temp, '9');
                for (int i = 0; i < count; i++)
                    answer.append("-");
            } else {
                // 잘못 생각함 30 31 32라면, --- 인줄
                /*
                int idx = answer.length();
                if (idx != 0 && answer.charAt(idx - 1) == '-') {
                    answer += " ";
                }*/
                answer.append(temp);
                /////////////////////////////////////////////////////////////////////////////////////////////
            }
            answer.append(" ");
        }
        System.out.println(answer.toString().trim());
    }

    /* 자바 1.8 */
    public static long count(String str, char ch){
        return str.chars()
                .filter(c -> c == ch)
                .count();
    }
}
