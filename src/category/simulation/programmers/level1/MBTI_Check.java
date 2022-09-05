package category.simulation.programmers.level1;

import java.util.HashMap;

/**
 * --------------------------------------------------------------<br/>
 * <b>성격 유형 검사 2022 카카오 인턴쉽, Programmers</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/> 주어진 문제를 이해하고, 적절하게 구현하기
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * HashMap에 성격 유형과 그 점수를 저장하고(put) 갱신하는(replace) 하는 방식으로 구현<br/>
 * HashMap 클래스의 get메소드는 특정 키가 없을 때 NullPointerException을 반환한다는 것을 주의
 * -> 이 경우를 대비하고자 어떤 사람들은 미리 전부 16가지 성격유형에 대해 초기화를 했음.
 * --------------------------------------------------------------<br/>
 * <b> 채점 25/25 </b><br/>
 * <p> 메모리  / 256MB , 실행시간  / 1000ms<br/>
 * --------------------------------------------------------------
 */
public class MBTI_Check {
    public static HashMap<String, Integer> map;

    public static String solution(String[] survey, int[] choices) {
        /* 최종 O(N) 탐색 후에서야 최종 결정 가능 */
        map = new HashMap<>();
        int idx = 0;
        for (String s : survey) {
            String type1 = s.substring(0, 1); // 또는 String.valueOf(s.charAt());
            String type2 = s.substring(1, 2);
            // 이미 map에 등록된 성격유형인가
            int preScore = 0;
            int newScore = 0;
            if (choices[idx] < 4 && choices[idx] > 0) {
                newScore = Math.abs(choices[idx] - 4);
                if (map.containsKey(type1)) {
                    preScore = map.get(type1);
                    map.replace(type1, preScore + newScore);
                } else {
                    map.put(type1, newScore);
                }
            } else {
                newScore = Math.abs(choices[idx] - 4);
                if (map.containsKey(type2)) {
                    preScore = map.get(type2);
                    map.replace(type2, preScore + newScore);
                } else {
                    map.put(type2, newScore);
                }
            }
            idx++;
        }
        // 최종 결과 확인
        return decideRT() + decideCF() + decideJM() + decideAN();
    }

    public static String decideRT() {
        if (!map.containsKey("R") && !map.containsKey("T"))
            return "R";
        else if(!map.containsKey("R"))
            return "T";
        else if(!map.containsKey("T"))
            return "R";
        else{
            return map.get("R") >= map.get("T") ? "R" : "T";
        }
    }

    public static String decideCF() {
        if (!map.containsKey("C") && !map.containsKey("F"))
            return "C";
        else if(!map.containsKey("C"))
            return "F";
        else if(!map.containsKey("F"))
            return "C";
        else{
            return map.get("C") >= map.get("F") ? "C" : "F";
        }
    }

    public static String decideJM() {
        if (!map.containsKey("J") && !map.containsKey("M"))
            return "J";
        else if(!map.containsKey("J"))
            return "M";
        else if(!map.containsKey("M"))
            return "J";
        else{
            return map.get("J") >= map.get("M") ? "J" : "M";
        }
    }

    public static String decideAN() {
        if (!map.containsKey("A") && !map.containsKey("N"))
            return "A";
        else if(!map.containsKey("A"))
            return "M";
        else if(!map.containsKey("N"))
            return "A";
        else{
            return map.get("A") >= map.get("N") ? "A" : "N";
        }
    }
}
