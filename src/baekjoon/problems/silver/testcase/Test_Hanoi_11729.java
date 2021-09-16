package baekjoon.problems.silver.testcase;

import baekjoon.problems.silver.Hannoi11729;
import junit.framework.TestCase;

public class Test_Hanoi_11729 extends TestCase {

    public void testName() {
        StringBuilder sb = new StringBuilder();
        long start = System.nanoTime();
        int N = 1;
        while (N != 20) {
            Hannoi11729.hanoi(N, 1, 2, 3);
            N++;
        }
        long end = System.nanoTime();
        double elasped_time = (end - start) / 1000000000.0;
        System.out.println("종료 시간 : " + elasped_time + "sec");
        assertTrue(elasped_time <= 1.0);
    }

}
