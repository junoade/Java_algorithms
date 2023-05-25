package category.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_중복된 문자 제거 LV0</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 어떻게 중복된 문자를 제거할 것인가?<br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 해쉬셋을 이용해서 충돌이 안나는 요소만 따로 출력 버퍼에 넣어줌<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> clear <br/>
 * --------------------------------------------------------------
 */
public class P_RemoveDupChars {
    public String solution(String my_string) {
        Set<Character> sets = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (char c : my_string.toCharArray()) {
            if (!sets.contains(c)) {
                sets.add(c);
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
