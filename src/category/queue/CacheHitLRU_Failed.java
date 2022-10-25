package category.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LRU 와 LFU 헷갈리지 말자
 */
public class CacheHitLRU_Failed {

    static int least_idx = 0;

    static class Entry {
        String value;

        // int hit;
        Entry(String value) {
            this.value = value;
        }
    }

    // O(N)
    // 가장 오래된 idx(제일 왼쪽)을 반환
    public void updateLRU(Queue<Entry> caches, String city, int cacheSize) {
        if (caches.size() != cacheSize) {
            caches.add(new Entry(city.toUpperCase()));
        } else {
            caches.poll();
            caches.add(new Entry(city.toUpperCase()));
        }
    }

    // O(N)
    public Entry containsCity(Queue<Entry> caches, String city) {
        Entry entry = null;
        for (Entry e : caches) {
            if (e.value.equals(city.toUpperCase())) {
                entry = e;
            }
        }
        return entry;
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<Entry> caches = new LinkedList<>();

        if (cacheSize == 0) // 빼먹었다.
            return cities.length * 5;

        for (String city : cities) {
            Entry findEntry = containsCity(caches, city);
            if (findEntry != null) {
                answer += 1;
            } else {
                updateLRU(caches, city, cacheSize);
                answer += 5;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        CacheHitLRU_Failed c = new CacheHitLRU_Failed();
        int cacheSize = 3;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(c.solution(cacheSize, cities));
    }
}
