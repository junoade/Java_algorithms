package baekjoon.problems.silver.testcase;

import baekjoon.problems.silver.Josephus_1158;
import junit.framework.TestCase;

public class Test_Josephus_1158 extends TestCase {

    public void testTimeLimit(){
        long start = System.nanoTime();
        int N=5000;
        int k =1;
        /*for(; k<N; k++){
            Josephus_01.solution(N,k);
        }*/
        Josephus_1158.solution(N,k);
        long end = System.nanoTime();
        System.out.println((end-start)/1000000000.0);
    }
}
