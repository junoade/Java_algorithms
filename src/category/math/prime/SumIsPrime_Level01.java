package category.math.prime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * --------------------------------------------------------------
 * 프로그래머스_Level1_소수 만들기
 * 유형 - 수학, 소수 판별, 조합
 * 출처, Summer/Winter Coding 2018
 * --------------------------------------------------------------
 * 주요 키포인트
 * -> 조합 또는 BruteForce 이용해서 서로 다른 세 수 x,y,z의 합 구하기
 * -> 소수 판별 알고리즘 작성
 *  1. 간단하게 주어진 수 n과 늘어나는 i 의 나머지가 == 0 이 되는지 판별 하기 -> O(N)
 *  2. sqrt(n) 조건 이용해서 풀기
 *  3. 메모제이션 기법
 *  4. 에라토네스의 체
 *
 * --------------------------------------------------------------
 * 나의 풀이
 * - 시간 제한이 없어서, BruteForce, 2번 방식으로 품
 * - 시간 제한 빡센 문제도 접하고 싶다.
 *
 * 채점 : 25/25
 */
public class SumIsPrime_Level01 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] temp = br.readLine().split(" ");
        int[] arr = new int[temp.length];
        for(int i = 0; i<arr.length; i++){
            arr[i] = Integer.parseInt(temp[i]);
        }
        System.out.println(solution(arr));
    }

    public static int solution(int[] nums) {
        int answer = 0;
        int L = nums.length;

        /* Brute Force 조합 */
        for(int i = 0; i < L; i++){
            for(int j = i+1; j < L; j++){
                for(int k = j+1; k < L; k++){
                    int temp = nums[i] + nums[j] + nums[k]; // 여기서 합 구해주기
                    if(isPrime(temp))
                        answer++;
                }
            }
        }
        return answer;
    }

    /* 소수 판별 알고리즘 O(sqrt(n))*/
    public static boolean isPrime(int n){
        if(n == 1){
            return false;
        }else{
            for(int i = 2; i<= Math.sqrt(n); i++){
                if(n % i== 0){
                    return false;
                }
            }
            return true;
        }
    }

}
