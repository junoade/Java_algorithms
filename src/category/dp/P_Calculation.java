package category.dp;

import java.util.Arrays;

public class P_Calculation {


    static int N;
    static int M;

    static int[] numbers;
    static String[] ops;
    static int[] mem;

    public int solution(String arr[]) {

        N = arr.length / 2 + 1;
        M = N - 1;

        numbers = new int[N];
        mem = new int[N];
        Arrays.fill(mem, Integer.MIN_VALUE);
        ops = new String[M];

        // 숫자, 연산자 분리
        int opIdx = 0, numIdx = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("+") || arr[i].equals("-")) {
                ops[opIdx++] = arr[i];
            } else {
                numbers[numIdx++] = Integer.parseInt(arr[i]);
            }
        }

        //rec();

        return 0;
    }


    // 재귀의 끝까지 간 다음
    // 연산의 최댓값을 갱신해서
    // 다시 돌아온다.
    void rec(int opIdx, int depth) {

        // base case : 이미 최댓값을 메겼을 때; 갱신 판단
        if(mem[opIdx] != Integer.MIN_VALUE) {

        }


        // base case : 재귀의 끝일 때
        if (depth == M) {
            if (ops[opIdx].equals("+")) {
                mem[opIdx] = numbers[opIdx] + numbers[opIdx + 1];
            } else {
                mem[opIdx] = numbers[opIdx] - numbers[opIdx + 1];
            }

            return;
        }


        // general case :
    }


}
