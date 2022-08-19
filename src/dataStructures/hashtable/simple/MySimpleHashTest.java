package dataStructures.hashtable.simple;

import dataStructures.hashtable.HashTable;

/**
 * --------------------------------------------------------------<br/>
 * <b> 0819 - 간단한 HashTable 자료구조 만들기 </b><br/>
 * --------------------------------------------------------------<br/>
 * <b> 주요 키포인트 </b><br/>
 * - 해시 함수? <br/>
 * <p>
 * - 해시 테이블의 개념은? <br/>
 * 해시함수를 적용한 key와 그 key와 value를 맵핑하는 데이터 자료구조
 * <p>
 * - 해시 테이블 왜 쓰는가? <br/>
 * <p>
 * - 해시 테이블 장단점은? <br/>
 * <p>
 * - 해시 충돌 해결법은?
 * 1. 폐쇄주소방식, Closed Addressing : 해시테이블 + 링크드리스트
 * a. 체이닝,
 * b. 2 방향 체이닝 : 체이닝과 동일하지만, 두 개의 해시함수를 이용, 연결리스트의 길이가 짧은 쪽에 새 키 저장
 * 2. 개방주소방식, Open Addressing : 해시테이블 내에서 다른 빈 공간을 조사
 * a. 선형조사, Linear Probing
 * b. 이차조사, Quadratic Probing
 * c. 랜덤조사, Random Probing
 * d. 이중해싱, Double Hashing
 * <p>
 * 3. 기타 해싱 방법들
 * - 융합 해싱(Coalesced Hashing) : 체이닝 + 개방주소방식
 * - 뻐꾸끼해싱(Cuckoo Hashing) :
 * <p>
 * 4. 재해시와 동적해싱?
 * - 재해시란?
 * - 동적해싱이란?
 * - 확장해싱이란?
 * - 선형해싱이란?
 * <p>
 * --------------------------------------------------------------<br/>
 * <b> 나의 풀이 </b><br/>
 * <br/>
 * --------------------------------------------------------------
 */
public class MySimpleHashTest implements HashTable {

    /* Key가 문자열일 때, 문자열의 앞 글자를 숫자로 변환 후, Mod 연산을 통해 Key에 대한 해시코드, 값, 해시 어드레스를 반환 */
    static class Slot {
        String value;

        Slot(String value) {
            this.value = value;
        }
    }

    Slot[] hashTable;

    MySimpleHashTest(Integer size) {
        this.hashTable = new Slot[size];
    }

    /**
     * 키의 첫번째 ASCII 코드에 대해 해시 테이블의 길이로 mod 연산을 수행
     *
     * @param key
     * @return
     */
    @Override
    public int hashFunc(String key) {
        return (int) (key.charAt(0)) % this.hashTable.length;
    }

    /**
     * 해시테이블에 주어진 키, 값을 갖는 Slot을 할당 <br/>
     * Current Hash Collision Policy - 해시 충돌시, 단순히 value를 덮어 씀
     *
     * @param key
     * @param value
     * @return true
     */
    @Override
    public boolean saveData(String key, String value) {
        int address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            this.hashTable[address].value = value;
        } else {
            this.hashTable[address] = new Slot(value);
        }
        return true;
    }

    /**
     * 해시테이블에서 주어진 키에 대해 O(1)로 조회 후, value를 반환
     *
     * @param key
     * @return
     */
    @Override
    public String getData(String key) {
        int address = this.hashFunc(key);
        if (this.hashTable[address] != null) {
            return this.hashTable[address].value;
        }
        return null;
    }
}