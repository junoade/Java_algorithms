package category.sorting;

import java.util.Arrays;
import java.util.Collections;

public class P_StringRevereseOrder {

    // 오름차순 정렬 후 역순 (내림차순)
    // 0.59ms / 80.8MB
    public String solution(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new StringBuilder(new String(arr)).reverse().toString();
    }

    // 6.46ms / 80MB
    // 너무 많은 스트림 변환
    public String solution1(String s) {
        String[] arr = s.chars()
                .boxed()
                .sorted((i1, i2) -> i2- i1)
                .mapToInt(Integer::intValue)
                .mapToObj(i -> String.valueOf((char)i))
                .toArray(String[] :: new);
        StringBuilder sb = new StringBuilder();
        for(String a : arr) {
            sb.append(a);
        }
        return sb.toString();
    }

    // 5.38ms / 80MB
    public String solution2(String s) {
        String[] arr = s.split("");
        Arrays.sort(arr, Collections.reverseOrder());
        return String.join("", arr);
    }
}
