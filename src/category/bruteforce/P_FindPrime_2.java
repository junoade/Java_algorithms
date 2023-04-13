package category.bruteforce;

import category.bruteforce.P_FindPrime;

import java.util.HashSet;
import java.util.Set;

public class P_FindPrime_2 {
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
        recursive(N, 1, 0);
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

        // base case : 콜러 함수에 존재하던 반복문을 없애줌
        if(r > n) {
            return;
        }

        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;

            visited[i] = true;
            pairs[depth] = numbers.charAt(i);
            recursive(n, r, depth+1);

            visited[i] = false;
            pairs[depth] = '\0';
            recursive(n, r + 1, depth); // 콜러 함수에 존재하던 반복문을 없애줌
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
