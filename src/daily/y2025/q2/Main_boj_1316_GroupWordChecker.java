package daily.y2025.q2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_boj_1316_GroupWordChecker {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            if(check(s)){
                answer++;
            }
        }
        System.out.println(answer);
    }

    static boolean check(String s) {
        char[] chars = s.toCharArray();
        Set<Character> words = new HashSet<>();
        words.add(chars[0]);

        for (int i = 1; i < chars.length; i++) {

            if (chars[i] != chars[i-1] && words.contains(chars[i])) {
                return false;
            }

            words.add(chars[i]);
        }

        return true;
    }
}
