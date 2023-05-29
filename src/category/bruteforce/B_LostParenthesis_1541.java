package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_LostParenthesis_1541 {

    static void solution(String s) {
        String[] exprs = s.split("-");

        int sum = 0;
        for(int i = 0; i < exprs.length; i++) {
            int num = 0;

            if(exprs[i].length() != 1) {
                for(String digit : exprs[i].split("\\+")) {
                    num += Integer.parseInt(digit);
                }
            } else {
                num = Integer.parseInt(exprs[i]);
            }

            if(i == 0) {
                sum = num;
            } else {
                sum -= num;
            }
        }

        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        solution(s);
    }
}
