package category.search;

import java.util.*;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_순위 검색</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 입력 처리 : info 배열의 문자열 원소에 대해 N : 50,000<br/>
 *  1.2 와일드 카드 '-' 를 포함한 모든 가능한 key 문자열 생성<br/>
 *  1.3 재귀를 이용하여 구현; depth == 검색 필터의 개수일 때  // 최대 108 가지 경우<br/>
 *  1.4 이미 존재하는 key인지 아닌지 검사 후 점수 삽입<br/>
 *  1.5 삽입 완료 후 존재하는 키의 점수 리스트에 대해 오름차순 정렬 수행<br/>
 * -> 전처리 시간 복잡도 O(NP + Q*Nlog N); P: 생성되는 조건의 수, 16 Q: 생성된 리스트의 개수, 108<br/>
 *
 * 2. 쿼리 배열을 순회 하며 maps에서 관리 중인 key인지 확인 후 M : 100,000<br/>
 *  2.1 검색할 key 문자열, score 정수 생성<br/>
 *  2.2 파라메트릭 서치 수행<br/>
 * -> 최악 시간 복잡도 : O(M * log N)<br/>
 *
 * 3. 파라메트릭 서치 수행 O(log N);<br/>
 *  3.1 x 점 이상 받은 사람의 수 = 전체 사람의 수 - x 점 이상인 사람 중 가장 적은 점수를 받은 사람의 위치;<br/>
 *      0부터 시작하므로 -1 은 하지 않음<br/>
 *  i)   start, end 범위를 inclusive하게;<br/>
 *  ii)  반복 조건은 탐색 요소가 1개 보다 많을 때; end - start - 1 > 1 -> start < end;<br/>
 *  iii) 동일한 점수인 경우 가장 왼쪽의 점수의 위치를 반환할 수 있게 구현<br/>
 *  iv)  모든 경우가 [F, F] 인 경우 있을 수도 있어서 탐색 종료 후 한번 더 조건 여부 판단<br/>
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 최대 시간 1561ms / 메모리 300MB <br/>
 * --------------------------------------------------------------
 */
public class P_RankSearch {
    // 모든 조합에 대해 점수 List 관리
    Map<String, List<Integer>> maps;

    // info.length in [1, 50,000] / query.lenth in [1, 100,000]
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length]; // [1, 100,000];

        // 입력 처리
        processInput(info);

        // query 추출 후
        // 해당 query를 키로 갖는 리스트에 대해
        // 정렬 후 파라메트릭 서치 수행
        int idx = 0;
        for(String q : query) {
            String[] temp = q.split(" (and )?");
            int L = temp.length;
            String key = String.join(" ", Arrays.copyOf(temp, L - 1));
            int score = Integer.parseInt(temp[L - 1]);
            // System.out.println(key);
            answer[idx] = search(key, score);

            idx++;
        }


        return answer;
    }

    // 입력에 대해 분리 후
    // 모든 조합 생성 및 점수 리스트 구성
    // 정렬도 미리 수행
    void processInput(String[] info) {
        maps = new HashMap<>(108); // 가지수
        for(String s : info) {
            String[] inputs = s.split(" ");
            rec("", inputs, 0);
        }

        for(List<Integer> list : maps.values()) {
            Collections.sort(list);
        }
    }

    void rec(String s, String[] inputs, int depth) {
        // base case;
        if(depth == inputs.length - 1) { // 점수를 제외한 부분 까지만
            int score = Integer.parseInt(inputs[inputs.length - 1]);
            s = s.trim();
            List<Integer> temp;
            if(maps.containsKey(s)) {
                temp = maps.get(s);
            } else {
                temp = new ArrayList<>();
            }
            temp.add(score);
            maps.put(s, temp);

            return;
        }

        rec(s + inputs[depth] + " ", inputs, depth + 1);
        rec(s + "-" + " ", inputs, depth + 1);
    }

    // 파라메트릭 서치 수행
    // 조건을 만족하는 사람 중 코딩테스트 점수를 x점 이상 받은 사람은 모두 몇 명인가? 를 구해야하는데
    // 1) 모든 사람들의 수
    // 2) x 점 이상인 사람들 중 가장 낮은 점수의 위치를 뺀다
    int search(String key, int target) {
        if(!maps.containsKey(key)) {
            return 0;
        }
        List<Integer> list = maps.get(key);

        // Collections.sort(list); // 매번 정렬하는 것보단 처음 한번에

        int start = 0; // inclusive
        int end = list.size() - 1; // inclusive

        while(start < end) {
            int mid = (start + end) / 2;
            int midValue = list.get(mid);

            if(midValue < target) { // 조건 X
                start = mid + 1;
            } else { // 조건 O // midValue >= target
                end = mid;
            }
        }

        int resultIdx = start;
        // 모든 경우가 [F, F] 인 경우일 수 있음
        if(list.get(resultIdx) < target) {
            return 0;
        } else {
            return list.size() - resultIdx;
        }
    }

    public static void main(String[] args) {
        P_RankSearch test = new P_RankSearch();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(test.solution(info, query)));
    }
}
