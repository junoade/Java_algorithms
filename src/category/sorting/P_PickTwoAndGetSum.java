package category.sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P_PickTwoAndGetSum {
    public int[] solution(int[] numbers) {
        // x + y의 결과가 중복될 수도 있음
        // 요소가 중복되진 않게 반환
        Set<Integer> set = new HashSet<>();
        final int L = numbers.length;

        // n_C_2 간단하게 구현
        for(int i = 0; i < L - 1; i++) {
            for(int j = i + 1; j < L; j++) {
                int sum = numbers[i] + numbers[j];
                set.add(sum);
            }
        }

        int[] result = set.stream().mapToInt(Integer::intValue).toArray();
        Arrays.sort(result);
        return result;
    }
}
