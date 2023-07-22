package category.dp.basic;

import java.io.*;

public class B_15988_CountOneTwoThree {

    static long[] tables;
    static final int MAX_L = 1_000_000;
    static final int MOD = 1_000_000_009;

    static void init() {
        tables = new long[MAX_L + 1];
        tables[1] = 1;
        tables[2] = 2;
        tables[3] = 4;

        for(int i = 4; i <= MAX_L; i++) {
            tables[i] = (tables[i - 1] + tables[i - 2] + tables[i - 3]) % MOD;
        }
    }


    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream(""));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init();

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(tables[n]);
        }

    }
}
