package dataStructures.hashtable.closedAddressing;

import dataStructures.hashtable.HashTable;

/**
 * Closed Addressing, 체이닝 연습
 */
public class MyHashTable implements HashTable {
    Slot[] hashTable;

    static class Slot {
        String key;
        String value;
        Slot next;

        Slot(String key, String value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    MyHashTable(int initialSize) {
        hashTable = new Slot[initialSize];
    }

    @Override
    public int hashFunc(String key) {
        return (int) (key.charAt(0)) % this.hashTable.length;
    }

    /**
     * Chaining 방식을 활용해서 해시테이블에 저장하기
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public boolean saveData(String key, String value) {
        int address = hashFunc(key);

        /* 이미 동일한 key에 대한 슬롯이 있는 경우 */
        if (this.hashTable[address] != null) {
            Slot cursor = this.hashTable[address];
            while (cursor.next != null) {
                cursor = cursor.next;
            }
            cursor.next = new Slot(key, value);

        } else {
            this.hashTable[address] = new Slot(key, value);
        }

        return true;
    }

    /**
     * Chaining 방식을 고려하여, Key가 갖는 대한 링크드 리스트 순회 후 반환하기
     *
     * @param key
     * @return
     */
    @Override
    public String getData(String key) {
        int address = this.hashFunc(key);
        StringBuilder sb = new StringBuilder();

        if (this.hashTable[address] != null) {
            Slot cursor = this.hashTable[address];
            do {
                sb.append(cursor.value).append(" ");
                cursor = cursor.next;
            } while (cursor != null);
        }
        return sb.toString();
    }
}
