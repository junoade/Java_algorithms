package category.recursive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * --------------------------------------------------------------<br/>
 * <b>백준 9095, 1,2,3, 더하기</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 재귀함수를 이용해서 조합의 수 구하기
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/> 최소 단위 1,2,3에 대한 조합의 표현의 수를 바탕으로 더 큰 수들에 대한 조합의 수를 재귀적으로 구함
 * 이는 작은 것을 풀어 큰 것을 푼다는 개념에 부합됨
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  14032 KB / 512MB , 실행시간  128 ms / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class CombinationSum_9095 {

    public static int solution(int n){
        if(n == 1)
            return 1; // 한 가지의 경우의 수가 있다는 뜻
        else if(n == 2)
            return 2; // 두 가지의 경우의 수로 표현할 수 있다는 뜻
        else if(n == 3)
            return 4; // 네 가지의 경우의 수로 표현할 수 있다는 뜻

        return solution(n-1) + solution(n-2) + solution(n-3); // 정수 n을 어떻게든 1,2,3 으로만 표현
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int n = Integer.parseInt(br.readLine());
            System.out.println(solution(n));
        }
    }
}
