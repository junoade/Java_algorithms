package category.math.prime;

import java.io.*;

public class BOJ_2023_MagicalPrime {

    // 접근 1
    // N = 8 이 최대
    // [1, 10^N - 1] 까지 소수 리스트 만들기?
    // [1, 99,999,999]
    // 소수 판별하면서 소수 리스트 채워?
    // 또 N 자리 체크해??
    // 시간 초과, 메모리 초과

    // 접근 2
    // 왼쪽부터 자릿수를 늘려간다
    // 일의 자리 소수 {2, 3, 5, 7}를 바탕으로
    // 홀수 {2, 3, 5, 7, 9}를 바탕으로 10의 자리 값을 만들고 소수인지 체크
    // N자리까지 반복
    static final int[] firstPrime = {2, 3, 5, 7};
    static final int[] odds = {1, 3, 5, 7, 9};
    static int N;
    static StringBuilder sb;

    // static int count = 0;

    // 가능한 최대 조합
    // 4 * 5^7 = 312,500
    // 그러나 10의 자리 수를 만드는 경우에 대한 4 * 5 경우와 이에 대한 소수 판별 시간복잡도 O(sqrt(N)) 수행 후
    // 남은 소수의 개수 k 만큼에 대해서만 재귀 깊이가 깊어짐
    static void solution() {
        for (int p : firstPrime) {
            rec(p, 1);
        }
    }

    static void rec(int prev, int digit) {
        if(digit == N) {
            sb.append(prev).append("\n");
            return;
        }

        // 10의 자리를 늘려줌
        prev *= (int)Math.pow(10, 1);
        for (int i = 0; i < odds.length; i++) {
            // 자릿수 증가 후 홀수 더함
            int value = prev + odds[i];
            // 소수 확인
            if(isPrime(value)) {
                // 재귀 호출
                // count++;
                rec(value, digit + 1);
            }
        }
    }

    // O(sqrt(N))
    static boolean isPrime(int n) {
        if(n < 2){
            return false;
        }
        for(int i = 2; i<=Math.sqrt(n); i++){
            if(n%i == 0){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        /*Scanner sc = new Scanner(System.in);
        N = sc.nextInt();*/
        sb = new StringBuilder();
        solution();
        System.out.print(sb);
        // System.out.println(count);
        // sc.close();
        br.close();
    }
}
