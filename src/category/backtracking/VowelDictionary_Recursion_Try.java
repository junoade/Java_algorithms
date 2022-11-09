package category.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


public class VowelDictionary_Recursion_Try {

    StringBuilder sb;
    HashMap<String, Integer> dict; // 문자열을 키로 갖게
    final ArrayList<Character> symbols = new ArrayList<>(Arrays.asList('A', 'E', 'I', 'O', 'U'));

    public void rec_answer(StringBuilder sb) {
        // base case
        // sb.toString()과 "UUUUU"이 같을 때 탐색 종료
        if (sb.toString().equals("UUUUU")) {
            dict.put("UUUUU", dict.size() + 1);
        }

        // dict에 이미 존재하는 문자라면?
        // 맨 뒤의 문자가 U가 아니면, 마지막 문자를 다음 symbol로 바꿔준다.
        // 맨 뒤의 문자가 U라면, 마지막 문자를 제거해준다.
        if (dict.containsKey(sb.toString())) {
            char lastChar = sb.charAt(sb.length() - 1);
            int lastIdx = sb.length() - 1;
            if (lastChar == 'U') {
                sb.deleteCharAt(lastIdx);
            } else {
                int nextIdx = symbols.indexOf(lastChar) + 1;
                sb.deleteCharAt(sb.length() - 1);
                sb.append(symbols.get(nextIdx));
            }
            rec_answer(sb);
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
            rec_answer(sb);
        }
    }

    public int solution(String s) {
        dict = new HashMap<>();
        sb = new StringBuilder().append(symbols.get(0));
        rec_answer(sb);
        return dict.get(s);
    }

    public static void main(String[] args) {
        VowelDictionary vowelDictionary = new VowelDictionary();
        System.out.println(vowelDictionary.solution("UUUUO"));
    }
}
