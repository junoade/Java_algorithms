package daily.y2026;

import java.util.*;

public class Leet_GreatestNumberOfCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        final int N = candies.length;

        List<Boolean> answer = new ArrayList<>(N);
        int max = Integer.MIN_VALUE;
        for (int candy : candies) {
            max = Math.max(max, candy);
        }

        for(int candy : candies) {
            answer.add(candy + extraCandies >= max);
        }

        return answer;
    }
}
