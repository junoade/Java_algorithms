package category.bruteforce;

import java.util.HashSet;
import java.util.Set;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 소수찾기 LV2 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 입력으로 전달된 문자열에 있는 숫자들의 모든 순서쌍을 순열로 완전 탐색<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 재귀로 순열 구현<br/>
 * 중복된 숫자 값을 갖을 수 있으므로, Set 리스트에 판별된 소수들을 넣고<br/>
 * set.size() 반환<br/>
 *
 * 주의할 것은,
 * 0인 값이 처리해야하고
 * Integer.parseInt("01"); 하면 1로 변환됨 확인
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  최대 78MB , 실행시간  최대 5.92ms <br/>
 * --------------------------------------------------------------
 */
public class P_FindPrime {
    Set<Integer> set;
    String numbers;
    char[] pairs;
    boolean[] visited;

    public int solution(String numbers) {
        final int N = numbers.length();
        set = new HashSet<>();
        this.numbers = numbers;
        visited = new boolean[N];

        // 시간 복잡도 n!/(n-1)! + n!/(n-2)! + ... + n!/0!
        for(int r = 1; r <= N; r++) {
            pairs = new char[r];
            recursive(N, r, 0);
        }

        return set.size();
    }

    // 모든 경우에 대해 탐색한다
    // 현재 문제의 정의에 따라서 중복이 발생할 수 있다.
    // 이를 간편하게 처리하기 위해 set 자료구조에 넣어주도록 한다.
    // ex1) "01" = "1", "1" = "01"
    // 재귀 함수의 구조는 순열 구현과 유사하게 작성한다.
    // ex2) "12" != "21" 이므로
    void recursive(int n, int r, int depth) {
        // base case : 다 뽑은 경우
        if(depth == r) {
            int value = Integer.parseInt(new String(pairs));
            System.out.println(value);
            if(isPrime(value)) {
                set.add(value);
            }
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            pairs[depth] = numbers.charAt(i);
            recursive(n, r, depth+1);

            visited[i] = false;
            pairs[depth] = '\0';
        }
    }

    private boolean isPrime(int n) {
        if(n == 1) {
            return false;
        }

        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P_FindPrime p = new P_FindPrime();
        System.out.println(p.solution("17"));
    }
}
