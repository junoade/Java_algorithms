package category.hash;

import java.util.HashMap;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_완주하지 못한 선수</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 엔트리 key(이름) - value(동명이인의 수)를 갖는 Map 구조 생성<br/>
 * 마라톤 참여 선수를 순회하며 조건을 만족하지 않을 때 이름 반환
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class P_FindUncompletedPlayer2 {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> players = new HashMap<>();
        for(String s : completion) {
            int value = 0;
            if(players.containsKey(s)) {
                value = players.get(s) + 1;
            }
            players.put(s, value);
        }

        for(String s : participant) {
            // 완주자 목록에 있는 사람
            // 동명이인 처리 완료
            if(players.containsKey(s) && players.get(s) >= 0) {
                int value = players.get(s) - 1;
                players.put(s, value);
            } else {
                return s;
            }
        }
        return "";
    }

    public static void main(String[] args) {
        P_FindUncompletedPlayer2 test = new P_FindUncompletedPlayer2();
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println(test.solution(participant, completion));
    }
}
