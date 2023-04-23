package category.sorting;

import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>프로그래머스 메뉴 리뉴얼</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * 조합, 각 문자열을 오름차순 정렬할 것 주의<br/>
 * 주문 후보군의 사이즈, 최대값의 개수 제한 주의<br/>
 * map.isEmpty()일 때, Collections.max(map.values()); 하면 NoSuchElement 예외 발생 가능 주의 <br/>
 * 문제 세부 조건들 주의<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 메모리 90MB , 최대 실행시간 8.97ms <br/>
 * --------------------------------------------------------------
 */
public class P_MenuRenewal {
    private Map<String, Integer> map;

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        for (int c : course) { // [2, 10]
            map = new HashMap<>();
            for (String order : orders) { // [2,20]
                System.out.printf("[DEBUG] {c : %d, order : %s}\n", c, order);
                if (c > order.length()) {
                    continue;
                }
                rec_comb(c, order, 0, new StringBuilder());
            }

            // map.isEmpty()면 Collections.max()에서 NoSuchElement 예외 발생 가능
            if (map.size() < 2) {
                continue;
            }
            // 구해진 map에 대해서 max값을 갖는 Entry를 찾기
            // 적어도 2번 이상 주문된 상품에 대해 탐색하도록
            int max = Collections.max(map.values());
            if (max == 1) {
                continue;
            }
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max) {
                    result.add(entry.getKey());
                }
            }
        }

        // ArrayList 정렬 후 반환
        System.out.println(result);
        Collections.sort(result);
        System.out.println(result);
        return result.toArray(String[]::new);
    }

    // 조합을 이용해 map 구성
    // XY <-> YX의 경우 YX를 사전순 정렬해서 XY가 증가하도록
    // params : 주문, 시작 위치, 지금까지 쌓인 문자열
    private void rec_comb(int depth, String order, int start, StringBuilder sb) {
        // base case
        if (depth == sb.length()) {
            // 만약 map에 있는 요소라면
            // value를 update
            // XY <-> YX 에 대해 XY도 추가, YX도 추가
            String origin = sb.toString();
            char[] temp = origin.toCharArray();
            Arrays.sort(temp);
            String cursor = new String(temp);

            System.out.printf("[DEBUG] {cursor : %s} \n", cursor);

            if (map.containsKey(cursor)) {
                int cursorValue = map.get(cursor) + 1;
                map.put(cursor, cursorValue);
            } else { // 아니면 추가
                map.put(cursor, 1);
            }
            return;
        }

        for (int i = start; i < order.length(); i++) {
            sb.append(order.charAt(i));
            rec_comb(depth, order, i + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        P_MenuRenewal test = new P_MenuRenewal();
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] orders2 = {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
        int[] course2 = {2, 3, 5};

        String[] orders3 = {"XYZ", "XWY", "WXA"};
        int[] course3 = {2, 3, 4};
        //test.solution(orders, course);
        test.solution(orders3, course3);
    }
}
