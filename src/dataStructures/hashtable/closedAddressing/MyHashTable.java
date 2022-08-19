package dataStructures.hashtable.closedAddressing;

import dataStructures.hashtable.HashTable;

/**
 * Closed Addressing, 체이닝 연습
 */
public class MyHashTable implements HashTable {


    @Override
    public int hashFunc(String key) {
        return 0;
    }

    /**
     * Chaining 방식을 활용해서 해시테이블에 저장하기
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean saveData(String key, String value) {
        return false;
    }

    /**
     * Chaining 방식을 고려하여, Key가 갖는 대한 링크드 리스트 순회 후 반환하기
     * @param key
     * @return
     */
    @Override
    public String getData(String key) {
        return null;
    }
}
