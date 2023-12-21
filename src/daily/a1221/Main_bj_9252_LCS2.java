package daily.a1221;

import java.io.*;
import java.util.*;

public class Main_bj_9252_LCS2 {
    static final int MAX_L = 1001;
    static String[][] D = new String[MAX_L][MAX_L];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String sA = " " + br.readLine();
        String sB = " " + br.readLine();

        char[] A = sA.toCharArray();
        char[] B = sB.toCharArray();

        for (int i = 0; i < MAX_L; i++) {
            for (int j = 0; j < MAX_L; j++) {
                D[i][j] = "";
            }
        }

        for (int i = 1; i < A.length; i++) {
            for (int j = 1; j < B.length; j++) {

                if (A[i] == B[j]) {
                    D[i][j] = D[i - 1][j - 1] + A[i];
                    continue;
                }

                String tempLcs = "";
                if (D[i - 1][j].length() > D[i][j - 1].length()) {
                    tempLcs = D[i - 1][j];
                } else {
                    tempLcs = D[i][j - 1];
                }
                D[i][j] = tempLcs;
            }
        }

        String answer = D[A.length - 1][B.length - 1];
        System.out.println(answer.length());
        if (answer.length() > 0) {
            System.out.println(answer);
        }
    }
}
