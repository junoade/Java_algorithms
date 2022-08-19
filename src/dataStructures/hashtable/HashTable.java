package dataStructures.hashtable;

public interface HashTable {
    int hashFunc(String key);
    boolean saveData(String key, String value);
    String getData(String key);
}
