package baekjoon.problems.bronze;

import java.util.Scanner;

public class GuGu1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int num=0;
        num=sc.nextInt();

        for(int i=1; i<=9; i++)
            System.out.println(num+" * "+ i+" = "+num*i);


    }
}
