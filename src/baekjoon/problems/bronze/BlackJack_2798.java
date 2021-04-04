package baekjoon.problems.bronze;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 2798번 브론즈, BruteForce Algorithm
 * 첫째 줄에 카드의 개수 N(3 ≤ N ≤ 100)과 M(10 ≤ M ≤ 300,000)이 주어진다.
 * 둘째 줄에는 카드에 쓰여 있는 수가 주어지며, 이 값은 100,000을 넘지 않는 양의 정수이다.
 * <p>
 * 합이 M을 넘지 않는 카드 3장을 찾을 수 있는 경우만 입력으로 주어진다.
 */
public class BlackJack_2798 {
    public static void main(String[] args) throws IOException {
        String input;
        int N = 0, M = 0;
        int[] arr;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();

            StringTokenizer st = new StringTokenizer(input);
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());

            input=br.readLine();
            arr=convertTointArr(input.split(" "));
            System.out.println(solution(N,M,arr));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    static int solution(int N, int M,int[] a){
        int[] best_arr=new int[3];
        int sum=0;//maxNum과 비교,
        int max=0;
        for(int i=0; i<N; i++){
            for(int j=i+1; j<N; j++){
                for(int k=j+1; k<N; k++){
                    sum=a[i]+a[j]+a[k];
                    if(sum>max && sum<=M){
                        max=sum;
                        /*best_arr[0]=a[i];
                        best_arr[1]=a[j];
                        best_arr[2]=a[k];*/
                    }

                }
            }
        }
        /*printArr(best_arr);*/
        return max;
    }
    static int[] convertTointArr(String[] str){
        int[] temp = new int[str.length];
        for(int i=0; i<str.length; i++)
            temp[i]=Integer.parseInt(str[i]);
        return temp;
    }
    static void printArr(int[] a){
        for(int i : a)
            System.out.print(i+" ");
        System.out.println();
    }
}
