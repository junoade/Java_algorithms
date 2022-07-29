package category.simulation.baekjun;

import java.io.*;

/**
 * 백준 음계, 2920
 *
 * ### 내가 푼 방식 : 버블 정렬 처럼, arr[i]와 arr[i+1]를 비교하고, asc, desc 기준에 맞춰 cnt++; 시켜줌
 * 그리고 순서를 판별함.
 * 코드 길이 952B, 시간 128ms
 *
 * ### 강사 풀이
 * - boolean asc, desc를 선언하고, 해당 조건에 따라 mixed 반환하기
 *
 * ### 다른 사람 풀이
 * String asc ="1 2 3 .. 8"; 처럼 미리 정의해놓고,
 * String input = br.readLine(); 이후, input.equals(asc) ? println("asecending") : println("descending") 처럼 작성
 */
public class Harmony_2920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[temp.length];
        for(int i = 0; i< arr.length; i++){
            arr[i] = Integer.parseInt(temp[i]);
        }

        System.out.println(solution(arr));
    }

    public static String solution(int[] arr){
        int cnt = 0;
        String result = "";
        for(int i = 0; i<arr.length-1; i++){
            if(arr[i] > arr[i+1]){
                cnt++;
            }else{
                cnt--;
            }
        }
        if(cnt == arr.length-1)
            result = "descending";
        else if(cnt == -1 * arr.length +1)
            result = "ascending";
        else
            result = "mixed";

        return result;
    }
}
