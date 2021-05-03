package baekjoon.problems.bronze.class1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Divder1008 {
    public static void main(String[] args) {
        String input;
        int N = 0, M = 0;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            input = br.readLine();

            StringTokenizer st = new StringTokenizer(input);
            N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());

            System.out.println((double)N/M);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
