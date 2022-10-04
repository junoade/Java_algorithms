package category.bruteforce;

import java.io.*;
import java.util.*;

class MaxManhatton_ {
    public static void solution(int[] points) {
        int max = 0;
        int L = points.length;

        int i = 0;
        for (int j = i + 1; j < L; j++) {
            int a = points[i], b = points[j], c = 0, d = 0;
            if( j < 3){
                c = points[L - 1 - j];
                d = points[L - 1 - i];
            } else {
                c = points[1];
                d = points[2];
            }
            int result = 0;
            for (int h = 0; h < 2; h++) {
                for (int k = 0; k < 2; k++) {
                    result = Math.abs(a - c) + Math.abs(b - d);
                    if (result >= max)
                        max = result;
                    // swap 해서 한번 더
                    int temp = c;
                    c = d;
                    d = temp;
                }

                int temp = a;
                a = b;
                b = temp;
            }
        }

        System.out.println(max);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] points = new int[4];
        for (int i = 0; i < 4; i++) {
            points[i] = Integer.parseInt(st.nextToken());
        }

        solution(points);
    }
}
