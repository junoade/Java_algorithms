package baekjoon.problems.silver.testcase;

import baekjoon.problems.silver.Surprising_String_1972;
import junit.framework.TestCase;

public class Test_Surprising_String_1972 extends TestCase {

    public void testTimeLimit() {
        int maxString = 80;
        char[] arr = new char[maxString];

        for (int T = 0; T < 101; T++) {
            for (int i = 0; i < maxString; i++) {
                arr[i] = (char) (Math.random() * 25 + 65);
            }
        /*
        Random rand = new Random();
        for(int i=0; i<maxString; i++) {
           arr[i] += rand.nextInt(25)+65;
        }*/
            String testLine = new String(arr);
            Surprising_String_1972.solution(testLine);
        }
    }
}
