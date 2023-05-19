package category.bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_Director_1436 {

    static int N;

    static void solution() {
        long number = 666;
        int cnt = 1;
        while(cnt < N) {
            number++;
            if(String.valueOf(number).contains("666")){
                cnt++;
            }
        }

        System.out.println(number);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        solution();
    }
}
