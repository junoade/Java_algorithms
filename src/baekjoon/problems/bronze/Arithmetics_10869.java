package baekjoon.problems.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Arithmetics_10869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        String[] numbers;
        numbers=input.split(" ");
        int[] realNum=new int[numbers.length];
        int A,B;

        for(int i=0; i<numbers.length; i++)
            realNum[i]=Integer.parseInt(numbers[i]);
        A=realNum[0];
        B=realNum[1];

        System.out.println(A+B);
        System.out.println(A-B);
        System.out.println(A*B);
        System.out.println(A/B);
        System.out.println(A%B);

    }
}
