package category.string;

import java.io.*;

public class B_Palindrome_1213 {

    // 중복 방지용
    static int L;
    static int[] records;

    static final String sorryWord = "I'm Sorry Hansoo";

    static String solution(String input) {
        L = input.length();

        char[] arr = input.toCharArray();
        records = new int[26];

        if (!isValid(arr)) {
            return sorryWord;
        } else {
            return getPalindrome();
        }
    }

    // 모든 문자는 대문자
    // O(N), N = 50
    static boolean isValid(char[] a) {
        for (char c : a) {
            int idx = c - 'A';
            records[idx]++;
        }

        // 모든 문자의 개수가 짝수개 거나
        // 모든 문자의 개수 중 홀수가 하나 이거나
        // 짝수개의 문자는 항상 괜찮으므로 별도 체크 X
        int oddCount = 0;
        for (int value : records) {

            // 홀수개 문자는 하나만!
            // 그외엔 짝수개 문자여야함
            if (value % 2 == 1) {
                oddCount++;
            }

            if (oddCount > 1) {
                return false;
            }


        }
        return true;
    }

    static String getPalindrome() {
        // 짝수개 문자
        // 짝수개 문자 + 홀수개 문자 1 + 짝수개 문자
        // 사전 순으로 가장 맨 앞에서 부터
        StringBuilder left = new StringBuilder();
        int oddLastIdx = -1;

        for (int i = 0; i < records.length; i++) {
            if (records[i] == 0) {
                continue;
            }
            char symbol = (char) ('A' + i);

            // 짝수개 반만큼 추가해줌
            int half = records[i] / 2;
            boolean isOdd = records[i] % 2 == 1;
            for (; half > 0; half--) {
                left.append(symbol);
                records[i]--;
            }

            // 홀수개 문자인 경우
            if (isOdd) {
                oddLastIdx = i;
            }

        }

        if (oddLastIdx == -1) {
            return left.toString() + left.reverse().toString();
        } else {
            return left.toString() + (char) ('A' + oddLastIdx) + left.reverse().toString();
        }
    }

    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream(""));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }
}
