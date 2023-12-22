package daily.a1221;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_bj_9251_LCS {
    static final int MAX_L = 1001;
    static int[][] D = new int[MAX_L][MAX_L];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sA = " " + br.readLine();
        String sB = " " + br.readLine();

        char[] A = sA.toCharArray();
        char[] B = sB.toCharArray();

        for (int i = 0; i < MAX_L; i++) {
            for (int j = 0; j < MAX_L; j++) {
                D[i][j] = 0;
            }
        }

        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {

                if (A[i] == B[j]) {
                    D[i][j] = D[i - 1][j - 1] + 1;
                    continue;
                }

                D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
            }
        }

        int answer = D[A.length - 1][B.length - 1];
        System.out.println(answer);
    }
}
