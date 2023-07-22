package category.dp.basic;

import java.io.*;

public class B_11726_Tile_2x1 {

    static long[] tables;
    static final int MAX_L = 1000;
    static final int MOD = 10_007;


    static void makeTable() {
        tables = new long[MAX_L + 1];
        tables[1] = 1L;
        tables[2] = 2L;
        for(int i = 3; i <= MAX_L; i++) {
            tables[i] = (tables[i-1] + tables[i-2]) % 10_007;
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream(""));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        makeTable();

        int N = Integer.parseInt(br.readLine());
        System.out.println(tables[N] % 10_007);
    }
}
