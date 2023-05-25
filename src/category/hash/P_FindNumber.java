package category.hash;

import java.util.HashSet;
import java.util.Set;

public class P_FindNumber {
    public int solution(int[] numbers) {
        Set<Integer> sets = new HashSet<>();
        // sets에 배열 numbers의 원소를 삽입
        for(int num : numbers) {
            sets.add(num);
        }

        int result = 0;
        for(int n = 0; n < 10; n++) {
            if (!sets.contains(n)) {
                result += n;
            }
        }

        return result;
    }
}
