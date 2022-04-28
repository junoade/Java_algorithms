package category.bit.practice;

/**
 * CH04.02 비트 스왑
 * <p>
 * 주어진 정수의 i 번째 비트와 j번째 비트를 스왑하는 코드를 작성하는 방법
 */
import java.io.*;
import java.util.StringTokenizer;

public class Sec02_BitSwap {

    /* step1) 스스로 생각해보기, O(1)*/
    public static long my_way_swap(long x, short i, short j){
        // Shift 연산과 플래그를 활용해서,
        // i에 해당하는 값,
        // j에 해당하는 값을 얻어 오고, swap
        // short left = (short)(x & 1<<i);
        // short right = (short)(x & 1<<j);
        short left = (short)((x>>>i) & 1);
        short right = (short)((x>>>j) & 1);
        if(left != right){ // 서로 다를 때만 swap 하면 됨.
            x ^= ((long) 1 << i); // 이진법이라, swap이 XOR를 의미하게 됨.
            x ^= ((long) 1 << j);
        }
        return x;
    }

    /* 조금 더 간결하게 만들어보자, O(1) */
    public static long swapBits(long x, int i, int j){
        // 먼저 i번째 비트와 j번째 비트가 서로 다른지 확인해야 한다.
        // 서로 같으면 swap할 필요가 없고, 추가적인 기능을 구현하는게 번거롭고 불필요하기 때문
        if(((x>>>i) & 1) != ((x>>>j) & 1)){ // i, j번째 비트값을 LSB로 끌고와서 1이랑 & 해서, 값을 확인
            long bitMask = (1L << i) | (1L << j); // OR 연산으로 합쳐줌
            x ^= bitMask;
        }
        return x;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long x = Long.parseLong(st.nextToken());
        short i = Short.parseShort(st.nextToken());
        short j = Short.parseShort(st.nextToken());

        long start = System.nanoTime();
        System.out.println("비트스왑 테스트 01_ 내 방법 : " + my_way_swap(x, i, j));
        long end = System.nanoTime();
        System.out.println(">>> 수행 시간 : " + (end-start) / Math.pow(10, 9)); // 타입 캐스팅이 빈번하게 되어 있어서 좀 실제롷는 오래 걸리는 듯

        start = System.nanoTime();
        System.out.println("비트스왑 테스트 02_ 교재 : " + swapBits(x, i, j));
        end = System.nanoTime();
        System.out.println(">>> 수행 시간 : " + (end-start) / Math.pow(10, 9));

    }
}
