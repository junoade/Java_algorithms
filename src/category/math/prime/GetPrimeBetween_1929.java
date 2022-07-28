package category.math.prime;

import java.io.*;
import java.util.StringTokenizer;

/**
 * --------------------------------------------------------------
 * 백준 1929, 1<=m<=n<=1,000,000 사이의 소수 찾기
 * 유형 - 수학, 소수 판별, 조합
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
 * - 2초내에 O(sqrt(N)) 이면 충분히 1,0000,000 가지의 연산이 가능할 것으로 판단
 *
 * 채점 : 1.212ms
 */
public class GetPrimeBetween_1929 {

    /**
     *
     * @param m
     * @param n
     */
    public static void solution(int m, int n){
        for(int i = m; i<=n; i++){
            if(isPrime(i))
                System.out.println(i);
        }
    }

    /* 시간 복잡도 : O(sqrt(N))*/
    public static boolean isPrime(int n){
        if(n == 1){
            return false;
        }else{
            for(int i = 2; i<=Math.sqrt(n); i++){
                if(n%i == 0){
                    return false;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /* 입력 m과 n, 1 ~ 1,000,000 */
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        solution(m,n);

    }
}
