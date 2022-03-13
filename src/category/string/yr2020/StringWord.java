/*
package string.yr2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

*/
/**
 * 2021 카카오 채용 연계형 인턴쉽
 * 숫자 문자열과 영단어 1차 풀이 0918
 *//*

public class StringWord {

    public static HashMap<Integer, String> map = new HashMap<>() {{
        put(0, "zero");
        put(1, "one");
        put(2, "two");
        put(3, "three");
        put(4, "four");
        put(5, "five");
        put(6, "six");
        put(7, "seven");
        put(8, "eight");
        put(9, "nine");
    }};
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        solution(input);
        System.out.println(sb.toString());
    }

    public static void solution(String s) {
        */
/*StringTokenizer st = new StringTokenizer(s); 구분자가 없어 아니라 이 방법 패스*//*

        String cursor = "";
        String number = "";
        int i = 0;
        while (i < s.length()) {
            cursor = s.substring(i, i + 1);
            i++;
            if (isNumber(cursor)) {
                sb.append(cursor);
            } else {
                number += cursor;
            }

            if(map.containsValue(number)){
                sb.append(getKey(map, number));
                number = "";
            }
        }

    }

    public static boolean isNumber(String s) {
        try {
            return Integer.parseInt(s) >= 0 && Integer.parseInt(s) < 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (K key : map.keySet()) {
            if (value.equals(map.get(key))) {
                return key;
            }
        }
        return null;
    }
}
*/
