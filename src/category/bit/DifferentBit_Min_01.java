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
public class DifferentBit_Min_01 {
    /* 1차 아이디어, x 값보다 큰 값들을 순회하며, 서로 다른 비트 자리수 세기. 1~2개일 때 stop*/
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i = 0; i<numbers.length; i++){
            answer[i] = calculate(numbers[i]);
        }

        return answer;
    }

    public static long calculate(long x){
        int cnt = 0;
        long y = x;
        long result = 0L;
        while(true){
            /* XOR 연산 부터, */
            y++;
            result = x ^ y;
            cnt = count(result);
            if(cnt >= 1 && cnt <=2)
                break;
        }
        return y;
    }

    /* 1 개수 세기*/
    public static int count(long x){
        int result = 0;
        while(x != 0){
            x &= (x-1);
            result++;
        }
        return result;
    }
}
