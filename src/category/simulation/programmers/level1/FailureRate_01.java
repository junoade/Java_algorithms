package category.simulation.programmers.level1;


/**
 * <title>프로그래머스, 실패율</title>
 * <a>https://programmers.co.kr/learn/courses/30/lessons/42889</a>
 *
 * @topic 구현, 최대 최소, 정렬
 * @date 2022.05.06
 * <p>
 * N : 전체 스테이지 개수
 * int[] stages : 유저들이 도전중인 스테이지 번호의 배열
 * ex) [2, 1, 2, 6, 2, 4, 3, 3] 2번 스테이지 도전하는 사람 2명, 5번까지 클리어하여 6번을 도전하는 사람 1명 등
 * @idea 1) (x, x+1, x-1, x+2, x-4) 라고 하면, x-5까지는 기본적으로 클리어한 사람들로 구성됨.  x-5 클리어한 사람은 x-4까지 진출한 사람을 비교하여 구할 수 있음.
 * 2) Map 자료구조의 키, 값 구조로, 키에는 스테이지, 값에는 도전자 수의 자료구조로 저장
 * 3) Map를 순회하며, 현재의 키값과, 현재의 키값의 다음 큰 키값 간의 도전자 수의 차이를 계산
 * ex) {1: 10, 2: 8, 3: 5} 라면
 * stage 1의 실패율은 2/10. 키 2의 value 8과 key 2 보다 작은 key 1의 value의 차이를 구함. 실패율은 2/8
 * stage 2의 실패율은 키 3의 value 5와 key 2의 value와의 차이를 구함  실패율은 3/8
 * stage 3의 실패율은 N에 따라 결정됨
 * <p>
 * 4) 키값을 별도의 List로 저장 후,
 * 3) 역순 정렬 한 다음 반환
 * @problems [4, 4, 4, 4] 같은 경우에 모두 {4: 4} 로 저장되어버림.
 * -> {1:0, 2:0, 3:0, 4:4 } 이런식으로 되게?
 */

import java.util.*;
import java.io.*;

public class FailureRate_01 {
    public static int[] solution(int N, int[] stages) {
        Map<Integer, Integer> map = new HashMap<>();

        /* step1) 자료 구조 저장 단계 */
        for (int i = 0; i < stages.length; i++) {
            /* 중복된 스테이지 번호(key)라면, 도전자의 수(value) 를 증가 시켜줌 */
            if (map.containsKey(stages[i])) {
                map.put(stages[i], 1);
            } else if (map.containsKey(stages[i]) && map.get(stages[i]) >= 1) {
                int update_val = map.get(stages[i]) + 1;
                map.replace(stages[i], update_val); // 내부적으로 시간복잡도, 공간복잡도는?
            } else {
                map.put(i, 0);
            }
        }

        /* step2) 실패율 계산 단계 */
        /* HashMap 사용하는 경우, map.keySet().toArray() 키에 대해 인덱싱 */
        Object[] keys = map.keySet().toArray();
        Map<Integer, Double> temp = new HashMap<>();

        int cur_no = stages.length;
        for (int i = 0; i < keys.length; i++) {
            // int fail_no = Math.abs(map.get(keys[i]) - map.get(keys[i + 1]));
            int still_trying_no = map.get(keys[i]); // 아직 자신의 스테이지를 통과하지 못 한자
            if ((int) keys[i] == N + 1) {
                still_trying_no = 0;
                keys[i] = (int) keys[i] - 1; // N+1 스테이지는 올클리어 스테이지, N으로 reduce시켜줌
            }

            double failure_rate = (double) still_trying_no / cur_no;
            cur_no -= still_trying_no;
            temp.put((Integer) keys[i], failure_rate);
        }

        /* step3) value(double failure_rate) 기준으로 entryList 를 정렬 */
        List<Map.Entry<Integer, Double>> entryList = new LinkedList<>(temp.entrySet());
        // entryList.sort(Map.Entry.comparingByValue());
        entryList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        ArrayList<Integer> result = new ArrayList<>();
        for (Map.Entry<Integer, Double> integerDoubleEntry : entryList)
            result.add(integerDoubleEntry.getKey());

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] stages = new int[st.countTokens()]; // N을 뺌

        int i = 0;
        while (st.hasMoreTokens()) {
            stages[i] = Integer.parseInt(st.nextToken());
            i++;
        }

        int[] result = solution(N, stages);
        for (int num : result) {
            System.out.println(num);
        }
    }
}
