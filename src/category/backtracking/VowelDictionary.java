package category.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b> 모음 사전, 프로그래머스 </b><br/>
 * Lv2. 완전탐색 연습
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 1. StringBuilder를 사용해서 문자열을 효율적으로 늘렸다 줄였다
 * while(){
 * 2. Map 자료구조를 이용해서 이미 존재하는 문자열인지 탐색 O(1)
 * 3. 이미 존재하는 문자열일 때,
 * 3-1. 마지막 문자가 U라면 제거해주고, U가 아니라면 A->E->I->O->U 순으로 늘어나게 해줌
 * 4. 새로운 문자열일 때,
 * 4-1. Map 자료구조에 넣어주고, 문자열 길이가 5 미만일 때, sb.append('A')를 해준다
 * }
 * 5. 마지막 문자열 추가해줌
 * --------------------------------------------------------------<br/>
 * <b> 채점 40/40 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class VowelDictionary {

    StringBuilder sb;
    HashMap<String, Integer> dict; // 문자열을 키로 갖게
    final ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    public void makeDictionary(StringBuilder sb) {
        while (!sb.toString().equals("UUUUU")) {
            // sb.length() < 5이고, dict에 이미 존재하는 문자라면?
            // 맨 뒤의 문자가 U가 아니면, 마지막 문자의 다음 symbol로 바꿔준다.
            // 맨 뒤의 문자가 U라면, 마지막 문자를 제거해준다.
            if (dict.containsKey(sb.toString())) {
                int lastIdx = sb.length() - 1;
                char lastChar = sb.charAt(lastIdx);
                if (lastChar == 'U') {
                    sb.deleteCharAt(lastIdx);
                } else {
                    int nextSymbolIdx = symbols.indexOf(lastChar) + 1;
                    sb.deleteCharAt(lastIdx);
                    sb.append(symbols.get(nextSymbolIdx));
                }
            }
            // dict에 존재하지 않는 문자라면? 현재 dict에 없는 새로운 문자
            // dict에 추가한다.
            // sb.length() < 5일 때, symbols의 0번째 문자인 A를 더해준다.
            // 만약 중복되면 앞의 로직에 따라 A->E->I->O->U 로 바뀐다.
            else {
                dict.put(sb.toString(), dict.size() + 1);
                if (sb.length() < 5) {
                    sb.append(symbols.get(0));
                }
            }
        }
        dict.put("UUUUU", dict.size() + 1);
    }

    public int solution(String s) {
        dict = new HashMap<>();
        sb = new StringBuilder().append(symbols.get(0));
        makeDictionary(sb);
        return dict.get(s);
    }

    public static void main(String[] args) {
        VowelDictionary vowelDictionary = new VowelDictionary();
        System.out.println(vowelDictionary.solution("UUUUO"));
    }
}
