package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Password_DupReduce {
    static void solution(String input, int test_case) {
        StringBuilder sb = new StringBuilder(input);
        boolean isDupFound = false;

        do {
            int L = sb.length();
            for (int i = 0; i < L - 1; i++) {
                char leftVal = sb.charAt(i);
                char rightVal = sb.charAt(i + 1);
                if (leftVal == rightVal) {
                    isDupFound = true;
                    sb.delete(i, i + 2); // startIdx Inclusive, endIdx Exclusive;
                    break;
                }
                isDupFound = false;
            }
        } while (isDupFound);

        System.out.printf("#%d %s\n", test_case, sb);
    }

    public static void main(String args[]) throws IOException {
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // int T = Integer.parseInt(br.readLine());
        final int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            /////////////////////////////////////////////////////////////////////////////////////////////
            String[] inputs = br.readLine().split(" ");
            int inputLength = Integer.parseInt(inputs[0]);
            String inputData = inputs[1];
            solution(inputData, test_case);
            /////////////////////////////////////////////////////////////////////////////////////////////\
        }
    }
}
