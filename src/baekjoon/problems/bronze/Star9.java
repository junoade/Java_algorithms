package baekjoon.problems.bronze;

import java.util.Scanner;

public class Star9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N=0;
        N=sc.nextInt();
        for(int i =0; i<2*N-1; i++){
            //
            if(i<N){
                for(int j=0; j<i; j++)
                    System.out.print(" ");
                for(int k=0; k<((2*N-1)-2*i);k++) //(int k=0; k<(9-2*i);k++)
                    System.out.print("*");
            }else{
                for(int j=0; j<(2*N-2)-i; j++) //(int j=0; j<8-i; j++)
                    System.out.print(" ");
                for(int k=0; k<(2*i-(2*N-3));k++)
                    System.out.print("*");
            }
            System.out.println();
            //
        }
    }
}
