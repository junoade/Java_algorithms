package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class B_SumOfSubSeq_1182 {

    static int[] arr;
    static int[] temp;
    static int N, M;
    static int count;

    static void solution(int[] arr) {
        int L = arr.length;
        count = 0;

        for(int i = 1; i <= L; i++) {
            temp = new int[i];
            rec(i, 0, 0);
        }

        System.out.println(count);
    }

    // 재귀로 부분 수열의 합을 구한다
    static void rec(int r, int depth, int startIdx) {
        if(depth == r) {
            System.out.println(Arrays.toString(temp));
            int sum = getSum();
            if(M == sum) {
                count++;
            }
            return;
        }

        for(int i = startIdx; i < arr.length; i++) {
            temp[depth] = arr[i];
            rec(r, depth + 1, i + 1);
        }
    }

    private static int getSum() {
        return IntStream.of(temp).sum();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] inputs = getInputsArr(br);
        N = inputs[0]; M = inputs[1];
        arr = getInputsArr(br);
        solution(arr);
    }

    private static int[] getInputsArr(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
