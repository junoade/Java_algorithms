package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class OperatorInjection_14888 {

    static String[] symbols = {"", "+", "-", "*", "/"};
    static int[] numbers, ops, record; // record 현재까지 사용한 op 정보 저장를 위해
    static int MAX, MIN;
    static int N;

    static void recFunc(int opIdx) {
        /* base case : 모든 연산자를 배정한 경우 */
        if (opIdx == record.length) {
            // 현재까지 배정한 연산자를 바탕으로 최종 결과 반환
            int result = update();
            MAX = Math.max(MAX, result);
            MIN = Math.min(MIN, result);
        } else { /* opIdx에 연산자를 배정해야하는 경우 */
            // for 문으로 ops의 값에 따라 symbols 연산자를 고르고
            for (int curIdx = 0; curIdx < ops.length; curIdx++) {
                if (ops[curIdx] > 0) {
                    // 현재 스텝에서 사용했음을 명시
                    ops[curIdx]--;
                    record[opIdx] = curIdx + 1;
                    // 다음 스텝으로 이동
                    recFunc(opIdx + 2);
                    // 현재 연산자를 선택하고 재귀의 끝까지 갔다가 다시 돌아온 경우, 현재 스텝을 초기화 해줌
                    ops[curIdx]++;
                    record[opIdx] = 0;
                }
            }
        }
    }

    static int update() {
        int result = numbers[0];
        for (int idx = 1; idx < record.length; idx += 2) { // 짝수는 값, 홀수는 연산자 인덱스
            if (validOpIdx(record[idx])) {
                result = calculate(record[idx], result, record[idx + 1]);
            }
        }
        return result;
    }

    static int calculate(int opIdx, int leftOperand, int rightOperand) {
        String op = symbols[opIdx];
        int result = 0;
        switch (op) {
            case "+":
                result = leftOperand + rightOperand;
                break;
            case "-":
                result = leftOperand - rightOperand;
                break;
            case "*":
                result = leftOperand * rightOperand;
                break;
            case "/":
                result = leftOperand / rightOperand; // int 형의 나눗셈 연산 결과와 같음
                break;
        }
        return result;
    }

    static boolean validOpIdx(int idx) {
        return idx > 0 && idx < symbols.length;
    }


    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // numbers = new int[N]; // opRecord = new int[N-1];
        String numbersInput = br.readLine();
        String opsInput = br.readLine();
        numbers = Arrays.stream(numbersInput.split(" ")).mapToInt(Integer::parseInt).toArray();
        ops = Arrays.stream(opsInput.split(" ")).mapToInt(Integer::parseInt).toArray();
        initRecord();

        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;
    }

    static void initRecord() {
        record = new int[2 * N - 1];
        for (int i = 0; i < numbers.length; i++) {
            record[2 * i] = numbers[i];
        }
    }


    public static void main(String[] args) throws IOException {
        input();
        recFunc(1);
        System.out.println(MAX);
        System.out.println(MIN);
    }
}
