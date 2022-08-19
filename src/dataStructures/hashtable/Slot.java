package dataStructures.hashtable;

/**
 * 해시 테이블을 구성하는 Slot에 대한 자료구조
 */
public class Slot {
    String key;
    String value;

    Slot(String key, String value){
        this.key = key;
        this.value = value;
    }
}
