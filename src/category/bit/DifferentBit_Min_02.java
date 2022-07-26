package category.bit;

/**
 * @topic 프로그래머스 월간 챌린지 시즌2, 2개 이하로 다른 비트
 *
 * @idea
 * 1. 주어진 값 x와 y=x부터 시작하는 y를 둔다.
 * 2. y값을 증가시키고, x와 XOR 연산한다. (x^y)
 * 3. 서로 다른 비트 수를 세서 1~2 범위인지 체크한다
 * 4. y값을 증가시켜 반복한다. 3번을 만족할때 까지.
 *
 * @problem
 * 무작정 y값을 1씩 증가시키니, 시간초과로 올쏠하지 못하였음.
 * -> 64 bit 범위를 이용해서 한정시켜야겠다.
 *
 */
public class DifferentBit_Min_02 {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i = 0; i<numbers.length; i++){
            answer[i] = calculate(numbers[i]);
        }
        return answer;
    }

    /** 2차 아이디어 : x보다 큰 수 y에 대해, x^y 연산을 하면, 서로 다른 비트가 모두 1이되는 결과가 나온다.
     * 서로 다른 비트 중 1~2개 다른 수들 중 제일 작은 수를 찾고 있으므로,
     * 2 만큼 SHR해주면, 그런 조건을 만족하면서, y의 마지막 1을 기준으로 오른쪽으로 이동하게 되어, y보다는 작은 값을 더해,
     * 조건을 만족하면서 x보다 큰  y값을 구할 수 있게 된다.
     */
    public static long calculate(long x){
        long y = x;
        y++;
        y += (x^y) >>2;
        return y;
    }
}
