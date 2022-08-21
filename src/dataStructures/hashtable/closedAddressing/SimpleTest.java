package dataStructures.hashtable.closedAddressing;

public class SimpleTest {
    public static void main(String[] args) {
        MyHashTable hashTable = new MyHashTable(20);
        hashTable.saveData("J", "Junho Choi");
        System.out.println(hashTable.getData("J"));
        hashTable.saveData("J", "James Choi");
        hashTable.saveData("J", "Jenny Choi");
        hashTable.saveData("H", "Henry Choi");
        hashTable.saveData("D", "Diana Choi");
        System.out.println(hashTable.getData("J"));
        System.out.println(hashTable.getData("H"));
        hashTable.saveData("H", "Hunho Choi");
        System.out.println(hashTable.getData("H"));
    }
}
