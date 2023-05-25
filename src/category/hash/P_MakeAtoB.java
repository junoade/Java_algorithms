package category.hash;

import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b> 프로그래머스 A로 B만들기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 주어진 문자열의 문자의 위치를 바꿔 after 문자열과 같게 만들 수 있는가 판별<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 해시맵을 이용해 문자(key) - 그 문자의 갯수(value)의 일치 여부 판단<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간 2.67ms<br/>
 * --------------------------------------------------------------
 */
public class P_MakeAtoB {
    public int solution(String before, String after) {
        Map<Character, Integer> src = convert(before);
        Map<Character, Integer> target = convert(after);

        // 문자의 갯수 일치 여부 판단
        for(Map.Entry<Character, Integer> entry : src.entrySet()) {
            if(!target.containsKey(entry.getKey())) {
                return 0;
            }

            if((int)target.get(entry.getKey()) != entry.getValue()) {
                return 0;
            }
        }

        return 1;
    }

    Map<Character, Integer> convert(String s) {
        Map<Character, Integer> result = new HashMap<>();

        for(char c : s.toCharArray()) {
            if(result.containsKey(c)) {
                Integer temp = result.get(c);
                result.put(c, temp + 1);
            } else {
                result.put(c, 1);
            }
        }

        return result;
    }
}
