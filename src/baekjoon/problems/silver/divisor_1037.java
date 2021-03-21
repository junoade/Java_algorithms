package baekjoon.problems.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class divisor_1037 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        int mid= arr.length/2;
        long result=0;
        if(arr.length%2 != 0) { // mid 가 홀수 일 때
            result = (long) arr[mid] *arr[mid];
        }else{
            result = (long)arr[mid-1]*arr[mid];
        }

        System.out.println(result);
        br.close();
    }
}
