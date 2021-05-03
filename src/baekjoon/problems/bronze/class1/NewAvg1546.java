package baekjoon.problems.bronze.class1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NewAvg1546 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //String[] numbers=br.readLine().split(" ");
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        /*int[] arr=new int[N];*/
        /*int max=arr[0];*/
        int max = -1;
        double sum = 0.0;
        /*for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(numbers[i]);
        }*/
        /*for(int i=0; i<N; i++){
            if(arr[i]>max)
                max=arr[i];
            sum+=arr[i];
        }*/
        for(int i =0; i<N; i++){
            int value = Integer.parseInt(st.nextToken());
            if(value > max)
                max= value;
            sum+=value;
        }
        /*double avg = (sum/max)*100/N;*/

        System.out.println((sum/max)*100/N);

    }
}
