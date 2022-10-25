package category.queue;

import java.util.*;

/**
 * LRU 알고리즘
 *  - 큐 자료구조를 쓰면 편하다.
 *  1. 이미 참조한 자료(캐시에 저장된 자료)를 참조하는 경우, 해당 자료를 삭제하고 다시 최근에 참조했다고 옮긴다.
 *  2. cache 사이즈가 꽉찬 경우, 제일 오래된 자료(head)를 poll(null 반환 가능성), remove(NoSuchElementException 발생 가능성), dequeue 하고,
 *     city를 tail에 추가한다.
 */
public class CacheHitLRU_Sol {
    public <T> void updateLRU(Queue<T> caches, T city, int cacheSize) {
        if (caches.size() >= cacheSize) {
            caches.poll();
        }
        caches.add(city);
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> caches = new LinkedList<>();

        if (cacheSize == 0) // 빼먹었다.
            return cities.length * 5;

        for (String city : cities) {
            if (caches.contains(city.toUpperCase())) { // O(N)
                // 큐에서 해당 city를 제거한 다음 tail에 추가한다.
                caches.remove(city.toUpperCase());
                caches.add(city.toUpperCase());
                answer += 1;
            } else {
                updateLRU(caches, city.toUpperCase(), cacheSize);
                answer += 5;
            }
        }

        return answer;
    }

}
