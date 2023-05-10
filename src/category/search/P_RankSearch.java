package category.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --------------------------------------------------------------<br/>
 * <b>P_순위 검색</b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * <br/>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * 1. 입력 처리 : 객체 배열 생성<br/>
 * 2. 객체 배열 정렬 : O(N log N), N in [1, 50,000]
 * 3. 각 쿼리를 순회 M : [1, 100,000]
 *  3-1. 쿼리 문자열로부터 조건, 점수를 분리 O(L) : 배열의 길이 만큼 순회될 수 있음
 *  3-2. 이진 탐색 수행 O(log N) 후 점수가 들어갈 자리의 idx를 찾음
 *  3-3. 해당 idx 이후부터 만족하는 객체의 개수를 O(N)으로 센다 [1, 50,000]
 *  -> 최종 시간 복잡도 O(MN) 시간초과
 * --------------------------------------------------------------<br/>
 * <b> 채점 </b><br/>
 * <p> 정확도 13.3 점 / 효율성 0점<br/>
 * --------------------------------------------------------------
 */
public class P_RankSearch {
    static class Person implements Comparable<Person> {
        String language;
        String part;
        String experience;
        String favorite;
        int score;

        Person(String[] inputs) {
            if (inputs.length < 5) {
                return;
            }

            this.language = inputs[0];
            this.part = inputs[1];
            this.experience = inputs[2];
            this.favorite = inputs[3];
            this.score = Integer.parseInt(inputs[4]);
        }

        Person(int score) {
            this.score = score;
        }

        @Override
        public int compareTo(Person p) {
            return Integer.compare(this.score, p.score);
        }

        @Override
        public String toString() {
            return String.format("Person{%s, %s, %s, %s, %d}", language, part, experience, favorite, score);
        }
    }

    // 인스턴스 변수
    Person[] arr;

    // N : [1, 50,000], M : [1, 100,000]
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length]; // M: [1, 100,000];

        // 입력 처리
        arr = processInput(info);

        // 입력 정렬 (점수 순으로 일단)
        Arrays.sort(arr);

        // 해당 점수 범위에 대해 이진 탐색 수행
        // 그 후 idx 이후에 대해 값을 만족하는지 탐색
        // O(M * N) 시간 초과
        int idx = 0;
        for (String q : query) {
            String[] inputs = processQuery(q);
            // System.out.println(Arrays.toString(inputs));

            // 조건에 부합하는지 판별
            answer[idx++] = getCount(inputs);
        }

        return answer;
    }

    // 입력 처리
    Person[] processInput(String[] info) {
        Person[] arr = new Person[info.length];

        int idx = 0;
        for (String s : info) {
            String[] inputs = s.split(" ");
            arr[idx++] = new Person(inputs);
        }

        // 확인
        // System.out.println(Arrays.toString(arr));
        return arr;
    }

    // query 처리
    String[] processQuery(String query) {
        String[] inputs = query.split(" |and");
        List<String> list = new ArrayList<>();
        for (String s : inputs) {
            if (!s.equals("")) {
                list.add(s);
            }
        }

        return list.stream().toArray(String[]::new);
    }

    // binarySearch 수행 후
    // idx로 부터 끝까지 탐색 O(log N + N) = O(N)
    int getCount(String[] inputs) {
        int start = 0;
        int end = arr.length;
        int score = Integer.parseInt(inputs[4]);
        int count = 0;

        // binarySearch(Object[] a, Object key)
        int idx = Arrays.binarySearch(arr, new Person(score));
        if (idx < 0) {
            idx = Math.abs(idx) - 1;
        }

        for (; idx < end; idx++) {
            if (checkCond(arr[idx], inputs)) {
                count++;
            }
        }

        return count;
    }

    boolean checkCond(Person p, String[] inputs) {
        // 서로 일치 하지 않거나(and) "-"이지도 않은 경우
        if (!inputs[0].equals(p.language) && !inputs[0].equals("-")) {
            return false;
        }

        if ( !inputs[1].equals(p.part) && !inputs[1].equals("-")) {
            return false;
        }

        if (!inputs[2].equals(p.experience) && !inputs[2].equals("-")) {
            return false;
        }

        if (!inputs[3].equals(p.favorite) && !inputs[3].equals("-")) {
            return false;
        }

        return Integer.parseInt(inputs[4]) <= p.score;
    }

    public static void main(String[] args) {
        P_RankSearch test = new P_RankSearch();
        String[] info = {"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"};
        String[] query = {"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"};
        System.out.println(Arrays.toString(test.solution(info, query)));
    }
}
