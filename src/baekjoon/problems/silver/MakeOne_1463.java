package baekjoon.problems.silver;

/**
 * DP 기초, 1로 만들기
 * 백준 1463 번
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class MakeOne_1463 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        System.out.println(solution(X));
    }

    static int solution(int n){
        int[] min_arr = new int[n+1];
        min_arr[1]=0; // N=1

        for(int i=2; i<n+1; i++){
            min_arr[i]=min_arr[i-1]+1; // 일반식
            if(i%3==0){
                min_arr[i]=Math.min(min_arr[i], min_arr[i/3]+1); // 일반식 vs 3 의 배수 규칙
            }
            if(i%2==0){
                min_arr[i]=Math.min(min_arr[i], min_arr[i/2]+1);
            }
        }
        return min_arr[n];
    }

}
