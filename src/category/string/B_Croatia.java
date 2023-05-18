package category.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Croatia {

    static String[] dict = {
            "c=",
            "c-",
            "dz=",
            "d-",
            "lj",
            "nj",
            "s=",
            "z="
    };
    static void solution(String s) {
        int count = 0;
        for (int i = 0; i < dict.length; i++) {
            if (s.contains(dict[i])) {
                s = s.replaceFirst(dict[i], " ");
                count++;
                i = -1;
            }
        }

        for (char c : s.toCharArray()) {
            if (c != ' ') {
                count++;
            }
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        solution(input);
    }
}
