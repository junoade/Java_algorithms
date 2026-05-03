package daily.y2026;

import java.util.*;

/**
 * 좌표 압축 개념 적용 - 리트코드 1331
 * > int[] sorted = Arrays.stream(arr).distinct().sorted().toArray(); 를 쓰면 런타임이 두배 정도 차이가 남.
 * Spliterator 셋업 비용, 메서드 체이닝 오버헤드, JIT 최적화 차이 라고 함
 * <a href = "https://leetcode.com/problems/rank-transform-of-an-array/">
 */
public class Leet_RankOfTransformArray {
    public int[] arrayRankTransform(int[] arr) {
        final int N = arr.length;

        // 입력값 방어
        if(N == 0)  return new int[0];

        // sort
        int[] copied = arr.clone();
        Arrays.sort(copied);

        // distinct and store
        Map<Integer, Integer> map = new HashMap<>();
        int rankedIdx = 1;
        map.put(copied[0], rankedIdx++);

        for(int i = 1; i < N; i++) {
            int current = copied[i];
            int before = copied[i - 1];

            if( current != before) {
                map.put(copied[i], rankedIdx++);
            }
        }

        // find
        int[] answer = new int[N];
        for(int i = 0; i < N; i++) {
            answer[i] = map.get(arr[i]);
        }
        return answer;
    }

    public static void main(String[] args) {
        Leet_RankOfTransformArray obj = new Leet_RankOfTransformArray();
        System.out.println(Arrays.toString(obj.arrayRankTransform(new int[]{40,10,20,30})));
    }
}
