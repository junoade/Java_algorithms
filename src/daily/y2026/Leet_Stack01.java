package daily.y2026;

import java.util.*;


public class Leet_Stack01 {
    private static final String PUSH = "Push";
    private static final String POP = "Pop";

    /**
     * <a href = "https://leetcode.com/problems/build-an-array-with-stack-operations/description/?envType=problem-list-v2&envId=dsa-linear-shoal-stack">
     * 문제 링크</a>
     * @param target
     * @param n
     * @return
     */
    public List<String> buildArray(int[] target, int n) {
        List<String> answer = new ArrayList<>();

        int cursor = 1;
        for(int t: target) {
            if(cursor == t) {
                answer.add(PUSH);
                cursor++;
            } else {
                while(cursor < t) {
                    answer.add(PUSH);
                    answer.add(POP);
                    cursor++;
                }
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        Leet_Stack01 leet = new Leet_Stack01();
        leet.buildArray(new int[]{1, 3}, 3);
    }
}
