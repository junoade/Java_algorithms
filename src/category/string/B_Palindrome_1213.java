package category.string;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B_Palindrome_1213 {

    // 중복 방지용
    static Set<String> set;
    static int L;
    static boolean[] visited;

    static final String sorryWord = "I'm Sorry Hansoo";

    static String solution(String input) {
        set = new HashSet<>();
        L = input.length();

        char[] arr = input.toCharArray();
        visited = new boolean[L];

        rec(arr, 0, new StringBuilder());

        // 정렬 후 제일 앞에 있는 것 반환
        // 없는 경우엔 다른 문자열 반환

        String[] answers = set.toArray(String[]::new);
        Arrays.sort(answers);

        if (answers.length == 0) {
            return sorryWord;
        } else {
            return answers[0];
        }
    }

    // 순열 구현
    static void rec(char[] arr, int depth, StringBuilder sb) {
        // base case
        // then, isPalindrome, add
        if (depth == L) {
            if (isPalindrome(sb.toString())) {
                set.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < L; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            sb.append(arr[i]);

            rec(arr, depth + 1, sb);

            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }

    }

    static boolean isPalindrome(String input) {
        final int length = input.length();
        for (int i = 0; i < length / 2; i++) {
            char left = input.charAt(i);
            char right = input.charAt(length - i - 1);
            if (left != right) {
                return false;
            }
        }
        return true;
    }



    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream(""));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        System.out.println(solution(input));
    }
}
