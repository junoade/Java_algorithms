package category.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SE_FindMostFreqNumber {

    static final int N = 1000;

    static int solution(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            int value = 0;
            if (map.containsKey(num)) {
                value = map.get(num);
            }
            map.put(num, value + 1);
        }

        Integer[] keys = map.keySet().toArray(new Integer[0]);
        // 정렬
        Arrays.sort(keys, (k1, k2) -> {
            int value1 = map.get(k1);
            int value2 = map.get(k2);

            // 내림차순 정렬
            if (value1 != value2) {
                return Integer.compare(value2, value1);
            }
            return Integer.compare(k2, k1);
        });

        return keys[0];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int tc = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.out.printf("#%d %d\n", tc, solution(arr));
        }
    }
}