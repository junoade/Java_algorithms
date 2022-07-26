package samsung.swtest_july;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Test02 {

    static void String2Char(char[] buf, String str) {
        for (int k = 0; k < str.length(); ++k)
            buf[k] = str.charAt(k);
        buf[str.length()] = '\0';
    }

    public static void main(String[] args) throws IOException {
        UserSolution userSolution = new UserSolution();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        userSolution.init();
        char[] gender = new char[7];
        String2Char(gender, "male");
        userSolution.add(100, 1, gender, 100);
        userSolution.add(50, 1, gender, 100);
        userSolution.add(20, 1, gender, 100);
        userSolution.add(10, 1, gender, 50);
        userSolution.add(30, 1, gender, 50);

    }
}
