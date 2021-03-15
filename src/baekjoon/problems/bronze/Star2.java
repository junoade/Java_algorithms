package baekjoon.problems.bronze;

import java.util.Scanner;

public class Star2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num=0;
        num=sc.nextInt();
        for(int i =0; i<num; i++){
            //space
            for(int j=num-1; j>i; j-- ) //int j=num-i-1; j>0; j--  // j=num-1; j>i
                System.out.print(" ");
            for(int k=0; k<=i; k++)
                System.out.print("*");
            System.out.println();
        }
    }
}
